package com.example.proyectnewro

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView

import androidx.appcompat.app.AppCompatActivity


class recodiario : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")

    private lateinit var imageViewCompleted: ImageView
    private lateinit var imageViewPending: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recodiario)

        val buttonAP = findViewById<Button>(R.id.buttonAP)
        imageViewCompleted = findViewById(R.id.realizado)
        imageViewPending = findViewById(R.id.pendiente)

        // Cargar el estado desde SharedPreferences
        loadStatus()

        buttonAP.setOnClickListener {
            // Obtener el estado actual
            val sharedPreferences = getSharedPreferences("StatusPref", MODE_PRIVATE)
            val currentStatus = sharedPreferences.getString("status", "pending")

            // Alternar el estado entre "completed" y "pending"
            val newStatus = if (currentStatus == "pending") "completed" else "pending"

            // Cambiar el estado
            updateStatus(newStatus)

            // Redireccionar a la nueva actividad FormAP
            val intent = Intent(this,FormAP::class.java)
            startActivity(intent)
        }
    }

    // Declarar las funciones aquí, fuera de onCreate()
    private fun loadStatus() {
        val sharedPreferences = getSharedPreferences("StatusPref", MODE_PRIVATE)
        val status = sharedPreferences.getString("status", "pending")

        when (status) {
            "completed" -> {
                imageViewCompleted.visibility = View.VISIBLE
                imageViewPending.visibility = View.GONE
            }
            "pending" -> {
                imageViewPending.visibility = View.VISIBLE
                imageViewCompleted.visibility = View.GONE
            }
        }
    }

    private fun updateStatus(newStatus: String) {
        val sharedPreferences = getSharedPreferences("StatusPref", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("status", newStatus)
        editor.apply()

        // Actualizar la UI
        loadStatus()
    }
}