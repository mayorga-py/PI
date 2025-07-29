package com.example.proyectnewro

import android.annotation.SuppressLint

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.journeyapps.barcodescanner.ScanContract //scan qr
import com.journeyapps.barcodescanner.ScanOptions  //scan qr
import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

class FormAgCa : AppCompatActivity() {
    private lateinit var spinnerFunCaldera1: Spinner
    private lateinit var spinnerFunCaldera2: Spinner
    private lateinit var spinnerFunbomba1: Spinner
    private lateinit var spinnerFunbomba2: Spinner
    private lateinit var spinnerFunRAC: Spinner

    private fun mostrarAlertaPersonalizada(mensaje: String) {
        val dialogView = layoutInflater.inflate(R.layout.alerta_error, null)
        val alertDialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .create()

        alertDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        val txtMensaje = dialogView.findViewById<TextView>(R.id.etalertasp)
        val btnAceptar = dialogView.findViewById<Button>(R.id.btnAceptar)

        txtMensaje.text = mensaje

        btnAceptar.setOnClickListener {
            alertDialog.dismiss()
        }

        alertDialog.show()
    }

    private fun mostrarAlertaExito(mensaje: String) {
        val dialogView = layoutInflater.inflate(R.layout.alerta_exito, null)
        val alertDialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .create()

        alertDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        val txtMensaje = dialogView.findViewById<TextView>(R.id.mensajeExito)
        val btnAceptar = dialogView.findViewById<Button>(R.id.btnAceptarExito)

        txtMensaje.text = mensaje

        btnAceptar.setOnClickListener {
            alertDialog.dismiss()
        }

        alertDialog.show()
    }


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_form_ag_ca)

        //variables a utilizar del formulario
        val preGasLp1 = findViewById<EditText>(R.id.preGas1)
        spinnerFunCaldera1 = findViewById(R.id.FunCaldera1)
        val preTanq1 = findViewById<EditText>(R.id.preTanqAC1)
        val tempTanq1 = findViewById<EditText>(R.id.tempTanqAC1)
        val preGasLp2 = findViewById<EditText>(R.id.preGas2)
        spinnerFunCaldera2 = findViewById(R.id.FunCaldera2)
        val preTanq2 = findViewById<EditText>(R.id.preTanqAC2)
        val tempTanq2 = findViewById<EditText>(R.id.tempTanqAC2)
        spinnerFunbomba1 = findViewById(R.id.FunbombFlu1_2)
        spinnerFunbomba2 = findViewById(R.id.FunbombFlu2_2)
        spinnerFunRAC=findViewById(R.id.FunbombRAC_2)
        val etHallazgosAgCa = findViewById<EditText>(R.id.etHaAC)
        val enviarexcel = findViewById<Button>(R.id.sendEx)

        //variables boton qr
        val btnScanQR1 = findViewById<Button>(R.id.btnQRpreGas1)
        val btnScanQR2 = findViewById<Button>(R.id.btnQRpreTanqAC1)

        val btnScanQR4 = findViewById<Button>(R.id.btnQRpreGas2)
        val btnScanQR5 = findViewById<Button>(R.id.btnQRpreTanqAC2)

        val btnScanQR7 = findViewById<Button>(R.id.btnQRfunBomFlu1_2)
        val btnScanQR8 = findViewById<Button>(R.id.btnQRfunBomRAC)

        var qrCodeText11: String?
        var qrCodeText22: String?
        var qrCodeText44: String?
        var qrCodeText55: String?
        var qrCodeText77: String?
        var qrCodeText88: String?

        //campo presion gas lp 1
        preGasLp1.isEnabled = true
        //iniciar qr lector y obtener escaneo
        val qrLauncher = registerForActivityResult(ScanContract()){ result ->
            if (result.contents != null){
                qrCodeText11 = result.contents //obtenemos

                if (qrCodeText11 == "NS-QR-MTTO-D-011"){
                    preGasLp1.isEnabled = true //habilitamos
                    spinnerFunCaldera1.isEnabled = true
                    Toast.makeText(this, "Codigo del Area: $qrCodeText11" , Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "Error: Código QR incorrecto", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "No se escaneó ningún código QR" , Toast.LENGTH_SHORT).show()
            }
        }
        // iniciar lector con boton
        btnScanQR1.setOnClickListener {
            val options = ScanOptions()
            options.setPrompt("Escanea el codigo QR")
            options.setBeepEnabled(true)
            options.setBarcodeImageEnabled(true)
            qrLauncher.launch(options)
        }
        // validaciones
        preGasLp1.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val value = preGasLp1.text.toString().toDoubleOrNull()
                if (value != null){
                    if (value < 6.0 || value > 8.5) {
                        mostrarAlertaPersonalizada(
                            "La presion de gas L.P. está por fuera del rango. Realiza lo siguiente:\n\n" +
                                    "REVISIÓN DE SUMINISTRO DE GAS LP Ó \nREVISIÓN DE REGULADOR DE SEGUNDA ETAPA."
                        )
                    }
                }
            }
        }

        val opciones = resources.getStringArray(R.array.opciones_bomba)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, opciones)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerFunCaldera1.adapter = adapter


//campo funcion caldera 1
        spinnerFunCaldera1.isEnabled= true
        spinnerFunCaldera1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "NO") {
                    mostrarAlertaPersonalizada(
                        "REVISA QUE TODO ESTE FUNCIONANDO CORRECTAMENTE"
                    )
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }


        //campo presion tanque 1 agua caliente
        preTanq1.isEnabled = true
        //iniciar lector y obtener escaneo
        val qrLauncher1 = registerForActivityResult(ScanContract()){result ->
            if (result.contents != null){
                qrCodeText22 = result.contents
                if (qrCodeText22 == "NS-QR-MTTO-D-012"){
                    preTanq1.isEnabled = true
                    tempTanq1.isEnabled = true
                    Toast.makeText(this, "Codigo del Area: $qrCodeText22", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "Error: Código QR incorrecto", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "No se escaneó ningún código QR", Toast.LENGTH_SHORT).show()
            }
        }
        //iniciar con el boton
        btnScanQR2.setOnClickListener {
            val options1 = ScanOptions()
            options1.setPrompt("Escanea el codigo QR")
            options1.setBeepEnabled(true)
            options1.setBarcodeImageEnabled(true)
            qrLauncher1.launch(options1)
        }
        //validaciones
        preTanq1.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus){
                val value = preTanq1.text.toString().toDoubleOrNull()
                if (value != null){
                    if (value < 4 || value > 7) {
                        mostrarAlertaPersonalizada(
                            "La presion de tanque 1 A.C. está por fuera del rango. Realiza lo siguiente:\n\n" +
                                    "REALIZAR REVISIÓN DE BOMBAS DE AGUA POTABLE\n."
                        )
                    }
                }
            }
        }

        // campo temperatura tanque 1
        tempTanq1.isEnabled = true
        tempTanq1.setOnFocusChangeListener { _, hasFocus ->
            if(!hasFocus){
                val value = tempTanq1.text.toString().toDoubleOrNull()
                if (value!= null){
                    if (value < 35) {
                        mostrarAlertaPersonalizada(
                            "La temperatura del tanque 1  está por fuera del rango. Realiza lo siguiente. \n\n"+
                                    "REVISAR FUNCIONAMIENTO DE CALDERAS\n"
                        )
                    }
                }
            }
        }

        //campo presion gas lp 2
        preGasLp2.isEnabled = true
        spinnerFunCaldera2.isEnabled = true
        val qrLauncher3 = registerForActivityResult(ScanContract()){result ->
            if (result.contents != null ){
                qrCodeText44 = result.contents
                if (qrCodeText44 == "NS-QR-MTTO-D-013"){
                    preGasLp2.isEnabled = true
                    spinnerFunCaldera2.isEnabled = true
                    Toast.makeText(this,"Código del Area: $qrCodeText44", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "Error: Código QR incorrecto", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this,"No se escaneó ningún código QR", Toast.LENGTH_SHORT).show()
            }
        }
        btnScanQR4.setOnClickListener {
            val options3 = ScanOptions()
            options3.setPrompt("Escanea el codigo QR")
            options3.setBeepEnabled(true)
            options3.setBarcodeImageEnabled(true)
            qrLauncher3.launch(options3)
        }
        preGasLp2.setOnFocusChangeListener { _, hasFocus ->
            if(!hasFocus){
                val value = preGasLp2.text.toString().toDoubleOrNull()
                if (value!= null){
                    if (value < 4 || value > 7) {
                        mostrarAlertaPersonalizada(
                            "La presion de gas L.P. está por fuera del rango. Realiza lo siguiente:\n\n" +
                                    "REVISIÓN DE SUMINISTRO DE GAS LP Ó \n REVISIÓN DE REGULADOR DE SEGUNDA ETAPA."
                        )
                    }
                }
            }
        }

//campo funcion caldera 2
        spinnerFunCaldera2.isEnabled= true
        spinnerFunCaldera2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "NO") {
                    mostrarAlertaPersonalizada(
                        "REVISA QUE TODO ESTE FUNCIONANDO CORRECTAMENTE"
                    )
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        //campo presion tanque 2 agua caliente
        preTanq2.isEnabled = true
        val qrLauncher4 = registerForActivityResult(ScanContract()){result ->
            if (result.contents != null ){
                qrCodeText55 = result.contents
                if (qrCodeText55 == "NS-QR-MTTO-D-014"){
                    preTanq2.isEnabled = true
                    tempTanq2.isEnabled = true
                    Toast.makeText(this,"Código del Area: $qrCodeText55", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "Error: Código QR incorrecto", Toast.LENGTH_SHORT).show()
                }

            } else {
                Toast.makeText(this,"No se escaneó ningún código QR", Toast.LENGTH_SHORT).show()
            }
        }
        btnScanQR5.setOnClickListener {
            val options4 = ScanOptions()
            options4.setPrompt("Escanea el codigo QR")
            options4.setBeepEnabled(true)
            options4.setBarcodeImageEnabled(true)
            qrLauncher4.launch(options4)
        }
        preTanq2.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val value = preTanq2.text.toString().toDoubleOrNull()
                if (value != null) {
                    if (value < 4 || value > 7) {
                        mostrarAlertaPersonalizada(
                            "La presion de tanque 2 A.C. está por fuera del rango. Realiza lo siguiente:\n\n" +
                                    "REALIZAR REVISIÓN DE BOMBAS DE AGUA POTABLE\n."
                        )
                    }
                }
            }
        }

        //campo tamperatura tanque 2
        tempTanq2.isEnabled = true
        tempTanq2.setOnFocusChangeListener { _, hasFocus ->
            if(!hasFocus){
                val value = tempTanq2.text.toString().toDoubleOrNull()
                if (value!= null){
                    if (value < 35) {
                        mostrarAlertaPersonalizada(
                            "La temperatura del tanque 2  está por fuera del rango. Realiza lo siguiente. \n\n"+
                                    "REVISAR FUNCIONAMIENTO DE CALDERAS\n"
                        )
                    }
                }
            }
        }

        //funcionamiento bomba flujo 1 y 2
        spinnerFunbomba1.isEnabled=true
        spinnerFunbomba2.isEnabled=true

        val qrLauncher6 = registerForActivityResult(ScanContract()){result ->
            if (result.contents != null ){
                qrCodeText77 = result.contents
                if (qrCodeText77 == "NS-QR-MTTO-D-015"){
                    spinnerFunbomba1.isEnabled=true
                    spinnerFunbomba2.isEnabled=true
                    Toast.makeText(this,"Código del Area: $qrCodeText77", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "Error: Código QR incorrecto", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this,"No se escaneó ningún código QR", Toast.LENGTH_SHORT).show()
            }
        }
        btnScanQR7.setOnClickListener {
            val options6 = ScanOptions()
            options6.setPrompt("Escanea el codigo QR")
            options6.setBeepEnabled(true)
            options6.setBarcodeImageEnabled(true)
            qrLauncher6.launch(options6)
        }

        spinnerFunbomba1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "NO") {
                    mostrarAlertaPersonalizada(
                        "REVISA QUE TODO ESTE FUNCIONANDO CORRECTAMENTE"
                    )
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        spinnerFunbomba2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "NO") {
                    mostrarAlertaPersonalizada(
                        "REVISA QUE TODO ESTE FUNCIONANDO CORRECTAMENTE"
                    )
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        //fumcionamiento bomba rac
        spinnerFunRAC.isEnabled = true
        val qrLauncher7 = registerForActivityResult(ScanContract()){result ->
            if (result.contents != null ){
                qrCodeText88 = result.contents
                if (qrCodeText88 == "NS-QR-MTTO-D-015"){
                    spinnerFunRAC.isEnabled = true
                    Toast.makeText(this,"Código del Area: $qrCodeText88", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "Error: Código QR incorrecto", Toast.LENGTH_SHORT).show()
                }

            } else {
                Toast.makeText(this,"No se escaneó ningún código QR", Toast.LENGTH_SHORT).show()
            }
        }
        btnScanQR8.setOnClickListener {
            val options7 = ScanOptions()
            options7.setPrompt("Escanea el codigo QR")
            options7.setBeepEnabled(true)
            options7.setBarcodeImageEnabled(true)
            qrLauncher7.launch(options7)
        }

        spinnerFunRAC.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "NO") {
                    mostrarAlertaPersonalizada(
                        "REVISA QUE TODO ESTE FUNCIONANDO CORRECTAMENTE"
                    )
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
                mostrarAlertaExito("Datos guardados correctamente en:\n\n${file.absolutePath}")
            } catch (e: Exception) {
                e.printStackTrace()
                mostrarAlertaPersonalizada("Error al guardar los datos:\n\n${e.message}")
            }
        }

        enviarexcel.setOnClickListener {
            val datosFormularioo = listOf(
                preGasLp1.text.toString(),
                spinnerFunCaldera1.selectedItem.toString(),
                preTanq1.text.toString(),
                tempTanq1.text.toString(),
                preGasLp2.text.toString(),
                spinnerFunCaldera2.selectedItem.toString(),
                preTanq2.text.toString(),
                tempTanq2.text.toString(),
                spinnerFunbomba1.selectedItem.toString(),
                spinnerFunbomba2.selectedItem.toString(),
                spinnerFunRAC.selectedItem.toString(),
                etHallazgosAgCa.text.toString(),
                SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault()).format(Date())
            )

            manejarExcel(
                context = this,
                fileName = "RecorridoMantenimiento.xlsx", // Siempre el mismo archivo
                sheetName = "RecorridoAguaCaliente", // Nombre de la hoja específica
                datos = datosFormularioo,
                headers = listOf(
                    "Presión gas L.P. 1",
                    "Función caldera 1",
                    "Presión tanque A.C. 1",
                    "Temperatura tanque A.C. 1",
                    "Presión gas L.P. 2",
                    "Función caldera 2",
                    "Presión tanque A.C. 2",
                    "Temperatura tanque A.C. 2",
                    "Función bomba de flujo 1",
                    "Función bomba de flujo 2",
                    "Función bomba R.A.C.",
                    "Hallazgos",
                    "Fecha y Hora"
                )
            )

            val sharedPreferences = getSharedPreferences("StatusPref", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("statusAgCa", "completed")  // 🔹 Usa la clave correspondiente a este formulario
            editor.apply()

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