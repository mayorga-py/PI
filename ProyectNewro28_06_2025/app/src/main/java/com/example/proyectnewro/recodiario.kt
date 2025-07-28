package com.example.proyectnewro

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class recodiario : AppCompatActivity() {

    private val statusKeys = arrayOf(
        "statusAP", "statusAgCa", "statusGasMed", "statusElectric", "statusAA", "statusCI"
    )
    private val imageCompletedIds = arrayOf(
        R.id.realizado1,
        R.id.realizado2,
        R.id.realizado3,
        R.id.realizado4,
        R.id.realizado5,
        R.id.realizado6
    )
    private val imagePendingIds = arrayOf(
        R.id.pendiente1,
        R.id.pendiente2,
        R.id.pendiente3,
        R.id.pendiente4,
        R.id.pendiente5,
        R.id.pendiente6
    )

    private lateinit var imageViewsCompleted: Array<ImageView>
    private lateinit var imageViewsPending: Array<ImageView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recodiario)

        val buttons = arrayOf(
            findViewById<LinearLayout>(R.id.buttonAP),
            findViewById<LinearLayout>(R.id.buttonAgCa),
            findViewById<LinearLayout>(R.id.buttonGM),
            findViewById<LinearLayout>(R.id.buttonElec),
            findViewById<LinearLayout>(R.id.buttonAA),
            findViewById<LinearLayout>(R.id.buttonCI)
        )

        // Inicializar las listas de ImageViews
        imageViewsCompleted = imageCompletedIds.map { findViewById<ImageView>(it) }.toTypedArray()
        imageViewsPending = imagePendingIds.map { findViewById<ImageView>(it) }.toTypedArray()

        // Cargar los estados desde SharedPreferences
        for (i in statusKeys.indices) {
            loadStatus(statusKeys[i], imageViewsCompleted[i], imageViewsPending[i])
        }

        // Asignar eventos a los botones
        buttons.forEachIndexed { index, button ->
            button.setOnClickListener {
                toggleStatus(statusKeys[index], imageViewsCompleted[index], imageViewsPending[index], buttons[index])
                startFormActivity(index)
            }
        }

        // 🔹 Inicializar y configurar el botón "Finalizar recorrido"
        val buttonFin = findViewById<Button>(R.id.buttonFin)
        buttonFin.setOnClickListener {
            val sharedPreferences = getSharedPreferences("StatusPref", MODE_PRIVATE)
            val editor = sharedPreferences.edit()

            // Marcar todas las imágenes como "pendiente"
            for (key in statusKeys) {
                editor.putString(key, "pending")
            }
            editor.apply()

            // Actualizar la UI para reflejar el cambio
            for (i in statusKeys.indices) {
                imageViewsCompleted[i].visibility = View.GONE
                imageViewsPending[i].visibility = View.VISIBLE
            }

            // Cerrar la aplicación
            finishAffinity() // Cierra todas las actividades y la app
        }
    }

    // Recargar estados al regresar a la pantalla
    override fun onResume() {
        super.onResume()
        for (i in statusKeys.indices) {
            loadStatus(statusKeys[i], imageViewsCompleted[i], imageViewsPending[i])
        }
    }

    // Función para cargar el estado de las imágenes desde SharedPreferences
    private fun loadStatus(key: String, imageViewCompleted: ImageView, imageViewPending: ImageView) {
        val sharedPreferences = getSharedPreferences("StatusPref", MODE_PRIVATE)
        val status = sharedPreferences.getString(key, "pending") // 🔹 Por defecto "pending"

        if (status == "completed") {
            imageViewCompleted.visibility = View.VISIBLE
            imageViewPending.visibility = View.GONE
        } else {
            imageViewCompleted.visibility = View.GONE
            imageViewPending.visibility = View.VISIBLE
        }
    }

    // Función para alternar estado y guardar en SharedPreferences
    @Suppress("UNUSED_PARAMETER")
    private fun toggleStatus(key: String, imageViewCompleted: ImageView, imageViewsPending: ImageView, button: View) {
        val sharedPreferences = getSharedPreferences("StatusPref", MODE_PRIVATE)
        val currentStatus = sharedPreferences.getString(key, "pending")

        if (currentStatus == "pending") {
            button.isEnabled = false
        }
    }


    // Función para abrir la actividad correspondiente
    private fun startFormActivity(index: Int) {
        val formClasses = arrayOf(
            FormAP::class.java,
            FormAgCa::class.java,
            FormGasMed::class.java,
            FormEletrico::class.java,
            FormAireAcondi::class.java,
            FormContraIncen::class.java
        )
        val intent = Intent(this, formClasses[index])
        startActivity(intent)
    }
}
