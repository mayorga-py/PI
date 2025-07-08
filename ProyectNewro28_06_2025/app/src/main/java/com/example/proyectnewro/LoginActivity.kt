package com.example.proyectnewro
import LoginRequest
import LoginResponse
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        val email = findViewById<EditText>(R.id.emailEditText)
        val password = findViewById<EditText>(R.id.passwordEditText)
        val loginBtn = findViewById<Button>(R.id.loginButton)

        loginBtn.setOnClickListener {
            val correo = email.text.toString()
            val contra = password.text.toString()

            if (correo.isNotEmpty() && contra.isNotEmpty()) {
                val request = LoginRequest(correo, contra)
                val call = RetrofitClient.instance.login(request)

                call.enqueue(object : Callback<LoginResponse> {
                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                    ) {
                        if (response.isSuccessful && response.body()?.success == true) {
                            val user = response.body()!!.user!!
                            Toast.makeText(
                                this@LoginActivity,
                                "Bienvenido, ${user.nombre}",
                                Toast.LENGTH_SHORT
                            ).show()

                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(
                                this@LoginActivity,
                                "Credenciales incorrectas",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        Toast.makeText(
                            this@LoginActivity,
                            "Error de red: ${t.message}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                })
            } else {
                Toast.makeText(this, "Llena todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
