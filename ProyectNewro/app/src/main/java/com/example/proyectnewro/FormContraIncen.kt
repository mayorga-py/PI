package com.example.proyectnewro

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class FormContraIncen : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_form_contra_incen)

        val spirevpanCI = findViewById<Spinner>(R.id.spRevPanel)
        val btnrevpanCI = findViewById<Button>(R.id.btRevPanel)
        val etCI = findViewById<EditText>(R.id.etAlarmas)
        val spirevtabCI = findViewById<Spinner>(R.id.spRevsionTab)
        val etHallazgosContraIncen = findViewById<EditText>(R.id.etHaCI)

        val enviarExcelCI = findViewById<Button>(R.id.btsendCI)

        var qrCodeTextCI1 : String ?

        spirevpanCI.isEnabled = false
        spirevpanCI.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "NO") {
                    val alertDialog = AlertDialog.Builder(this@FormContraIncen)
                        .setTitle("Advertencia Revision Panel de Alarmas")
                        .setMessage("Realiza lo siguiente: \nVERIFICAR EL ÁREA ALARMADA Y POSTERIORMENTE RESTAURAR SISTEMA")
                        .setPositiveButton("Aceptar") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                    alertDialog.show()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        val qrLauncherCI1 = registerForActivityResult(ScanContract()){ result ->
            if(result.contents != null){
                qrCodeTextCI1 = result.contents
                if(qrCodeTextCI1 == "NS-QR-MTTO-D-087"){
                    spirevpanCI.isEnabled = true
                    Toast.makeText(this, "Codigo del Area: $qrCodeTextCI1", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "Error: Código QR incorrecto", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "No se escaneó ningún código QR", Toast.LENGTH_SHORT).show()
            }
        }

        btnrevpanCI.setOnClickListener {
            val options1 = ScanOptions()
            options1.setPrompt("Escanea el codigo QR")
            options1.setBeepEnabled(true)
            options1.setBarcodeImageEnabled(true)
            qrLauncherCI1.launch(options1)
        }

        etCI.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val value = etCI.text.toString().toDoubleOrNull()
                if (value != null) {
                    if (value < 1 || value > 2) {
                        // Mostrar advertencia si el valor está fuera del rango
                        val alertDialog = AlertDialog.Builder(this@FormContraIncen)
                            .setTitle("Advertencia ALARMAS EN PANEL DE ALARMAS")
                            .setMessage(
                                "checar con ing chava"
                            )
                            .setPositiveButton("Aceptar") { dialog, _ ->
                                dialog.dismiss()
                            }
                            .create()
                        alertDialog.show()
                    }
                }
            }
        }

        spirevtabCI.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "NO") {
                    val alertDialog = AlertDialog.Builder(this@FormContraIncen)
                        .setTitle("Advertencia REVISIÓN TABLERO BOMBAS CONTRA INCENDIOS\n")
                        .setMessage("Realiza lo siguiente: \nVERIFICAR FUNCIONAMIENTO DE BOMBA DE DIESEL Y RESTAURAR SISTEMA")
                        .setPositiveButton("Aceptar") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                    alertDialog.show()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        ///////////////////////////////////BOTON ENVIAR/////////////////////////////////////////////
        fun manejarExcel(context: Context, fileName: String, sheetName: String, datos: List<String>, headers: List<String>) {
            // Ruta absoluta al directorio de Descargas
            val downloadsFolder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            val folder = File(downloadsFolder, "RecorridosMantenimiento")
            if (!folder.exists()) folder.mkdirs() // Crear carpeta si no existe

            val file = File(folder, fileName)

            try {
                val workbook: Workbook
                val sheet: Sheet

                // Cargar o crear el archivo Excel
                workbook = if (file.exists()) {
                    FileInputStream(file).use { inputStream ->
                        XSSFWorkbook(inputStream) // Cargar archivo existente
                    }
                } else {
                    XSSFWorkbook() // Crear un nuevo archivo
                }

                // Obtener o crear la hoja
                sheet = workbook.getSheet(sheetName) ?: workbook.createSheet(sheetName)

                // Agregar encabezados si la hoja está vacía
                if (sheet.physicalNumberOfRows == 0) {
                    val headerRow = sheet.createRow(0)
                    headers.forEachIndexed { index, header ->
                        headerRow.createCell(index).setCellValue(header)
                    }
                }

                // Agregar nueva fila de datos
                val newRow = sheet.createRow(sheet.lastRowNum + 1)
                datos.forEachIndexed { index, dato ->
                    newRow.createCell(index).setCellValue(dato)
                }

                // Guardar el archivo Excel
                FileOutputStream(file).use { outputStream ->
                    workbook.write(outputStream)
                }

                workbook.close()

                Toast.makeText(context, "Datos guardados en: ${file.absolutePath}", Toast.LENGTH_LONG).show()
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(context, "Error al guardar los datos: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }


        enviarExcelCI.setOnClickListener {
            val datosFormularioCI = listOf(
                spirevpanCI.selectedItem.toString(),
                etCI.text.toString(),
                spirevtabCI.selectedItem.toString(),
                etHallazgosContraIncen.text.toString(),
                SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault()).format(Date())
            )

            manejarExcel(context = this, fileName = "RecorridoMantenimiento.xlsx",
                sheetName = "RecorridoContraIncendios", datos = datosFormularioCI,
                headers = listOf(
                    "REVISIÓN PANEL DE ALARMAS" ,
                    "ALARMAS EN PANEL DE ALARMAS",
                    "REVISIÓN TABLERO BOMBAS CONTRA INCENDIOS",
                    "Hallazgos",
                    "Fecha y hora"
                ))

            // 🔹 Ahora actualizamos el estado a "completed"
            val sharedPreferences = getSharedPreferences("StatusPref", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("statusCI", "completed")  // 🔹 Usa la clave correspondiente a este formulario
            editor.apply()

            // 🔹 Regresar a la pantalla principal
            val intent = Intent(this, recodiario::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}