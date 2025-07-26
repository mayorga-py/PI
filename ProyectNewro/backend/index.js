const express = require('express');
const cors = require('cors');
const pool = require('./db');

const app = express();
const PORT = process.env.PORT || 3000;

app.use(cors());
app.use(express.json());

// Prueba del servidor
app.get('/', (req, res) => {
  res.send('API funcionando correctamente');
});

// Login seguro usando pgcrypto
app.post('/login', async (req, res) => {
  const { correo, password } = req.body;

  try {
    const result = await pool.query(
      'SELECT nombre, correo, is_admin FROM usuarios WHERE correo = $1 AND password = crypt($2, password)',
      [correo, password]
    );

    if (result.rows.length > 0) {
      res.json({ success: true, user: result.rows[0] });
    } else {
      res.status(401).json({ success: false, message: 'Credenciales incorrectas' });
    }
  } catch (err) {
    console.error('Error en login:', err);
    res.status(500).json({ success: false, message: 'Error en el servidor' });
  }
});

// Registro (opcional, para pruebas)
app.post('/register', async (req, res) => {
  const { nombre, correo, password, is_admin } = req.body;

  try {
    await pool.query(
      'INSERT INTO usuarios (nombre, correo, password, is_admin) VALUES ($1, $2, crypt($3, gen_salt(\'bf\')), $4)',
      [nombre, correo, password, is_admin]
    );
    res.json({ success: true, message: 'Usuario registrado correctamente' });
  } catch (err) {
    console.error('Error en registro:', err);
    res.status(500).json({ success: false, message: 'Error al registrar usuario' });
  }
});

app.listen(PORT, () => {
  console.log(`Servidor corriendo en http://localhost:${PORT}`);
});
