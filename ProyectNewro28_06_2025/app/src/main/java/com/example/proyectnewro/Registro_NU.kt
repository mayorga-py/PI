package com.example.proyectnewro

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Registro_NU : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registro_nu)

        val nombre = findViewById<EditText>(R.id.nombreEditText)
        val correo = findViewById<EditText>(R.id.emailEditText)
        val contra = findViewById<EditText>(R.id.passwordEditText)
        val boton = findViewById<Button>(R.id.btnRegistrar)

        boton.setOnClickListener {
            val nombreStr = nombre.text.toString()
            val correoStr = correo.text.toString()
            val contraStr = contra.text.toString()

            if (nombreStr.isNotEmpty() && correoStr.isNotEmpty() && contraStr.isNotEmpty()) {
                Toast.makeText(this, "Usuario $nombreStr registrado", Toast.LENGTH_SHORT).show()
                // Aquí puedes ir a la lógica de guardado
            } else {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
