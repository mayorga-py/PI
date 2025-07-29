package com.example.proyectnewro

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
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

class FormGasMed : AppCompatActivity() {

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_form_gas_med)

        val opciones = resources.getStringArray(R.array.opciones_bomba)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, opciones)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val preBanIzz = findViewById<EditText>(R.id.PreBanIz)
        val preBanDerr = findViewById<EditText>(R.id.PreBanDer)
        val sPextGaa = findViewById<Spinner>(R.id.spinnerFunEXGases)

        val layoutCambioBancada = findViewById<LinearLayout>(R.id.layoutCambioBancada)
        val layoutRevisionFugas = findViewById<LinearLayout>(R.id.layoutRevisionFugas)
        val spinnerCambioBancada = findViewById<Spinner>(R.id.spinnerCambioBancada)
        val spinnerRevisionFugas = findViewById<Spinner>(R.id.spinnerRevisionFugas)


        val preTanqAiMedd = findViewById<TextView>(R.id.PreTanqAiMed)
        val sPfunComp1 = findViewById<Spinner>(R.id.spinnerFunComp1)
        val sPfunComp2 = findViewById<Spinner>(R.id.spinnerFunComp2)
        val sPRevSeca1 = findViewById<Spinner>(R.id.spinnerSecadora1)
        val sPRevSeca2 = findViewById<Spinner>(R.id.spinnerSecadora2)
        val preTanVac1 = findViewById<EditText>(R.id.PreTanVac)
        val sPFunBomVac1 = findViewById<Spinner>(R.id.spinnerFunBombVacio1)
        val sPFunBomVac2 = findViewById<Spinner>(R.id.spinnerFunBombVacio2)
        val sPEncBombVacDeEvacGasAne = findViewById<Spinner>(R.id.spinnerEncBombVacDeEvacGasAne)
        val etHallazgosGasMed = findViewById<EditText>(R.id.etHaGM)
        val enviarExcel1 = findViewById<Button>(R.id.sendEx1)

        //variables boton qr
        val btnScanQrr1 = findViewById<Button>(R.id.QRPreBanIz)
        val btnScanQrr3 = findViewById<Button>(R.id.QRCBextGa)
        val btnScanQrr4 = findViewById<Button>(R.id.QRPreTanqAiMed)
        val btnScanQrr9 = findViewById<Button>(R.id.QRPreTanVac)
        val btnScanQrr12 = findViewById<Button>(R.id.QREncBombVacDeEvacGasAne)

        var btnQR1: String?
        var btnQR3: String?
        var btnQR4: String?
        var btnQR9: String?
        var btnQR12: String?

        //validaciones de presion de bancadas Izq y Der
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                // Obtener valores de los EditTexts
                val presionIzquierda = preBanIzz.text.toString().trim()
                val presionDerecha = preBanDerr.text.toString().trim()

                val esCeroIzquierda =
                    presionIzquierda.isNotEmpty() && presionIzquierda.toDoubleOrNull() == 0.0
                val esCeroDerecha =
                    presionDerecha.isNotEmpty() && presionDerecha.toDoubleOrNull() == 0.0

                // Mostrar u ocultar los campos según las condiciones
                if (esCeroIzquierda || esCeroDerecha) {
                    layoutCambioBancada.visibility = View.VISIBLE
                    layoutRevisionFugas.visibility = View.VISIBLE
                } else {
                    layoutCambioBancada.visibility = View.GONE
                    layoutRevisionFugas.visibility = View.GONE
                }
            }
        }

        // Asignar el TextWatcher a los EditTexts
        preBanIzz.addTextChangedListener(textWatcher)
        preBanDerr.addTextChangedListener(textWatcher)
        //validaciones de menu desplegable reaccion de presion de bancadas
        spinnerCambioBancada.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val seleccion = parent?.getItemAtPosition(position).toString()
                if (seleccion == "Ok") {
                    mostrarAlertaExito(
                        "TODO CORRECTO"
                    )
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        spinnerRevisionFugas.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val seleccion = parent?.getItemAtPosition(position).toString()
                if (seleccion == "NO") {
                    mostrarAlertaPersonalizada(
                        "REVISAR LA CAUSA Y REGISTRARLO EN HALLAZGOS"
                    )
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        //campo presion bancada derecha
        preBanIzz.isEnabled = true
        //qr
        val qrLauncher = registerForActivityResult(ScanContract()) { result ->
            if (result.contents != null) {
                btnQR1 = result.contents // Obtenemos el texto escaneado
                if (btnQR1 == "NS-QR-MTTO-D-016") {//cambiar codigo de 001 A D-016
                    preBanIzz.isEnabled = true // Habilitar el campo si el código es correcto
                    preBanDerr.isEnabled = true
                    Toast.makeText(this, "Código del Área: $btnQR1", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Error: Código QR incorrecto", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "No se escaneó ningún código QR", Toast.LENGTH_SHORT).show()
            }
        }
        //iniciar QR
        btnScanQrr1.setOnClickListener {
            val options = ScanOptions()
            options.setPrompt("Escanea el código QR")
            options.setBeepEnabled(true)
            options.setBarcodeImageEnabled(true)
            qrLauncher.launch(options) // Inicia el escaneo
        }
        //validaciones
        preBanIzz.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val value = preBanIzz.text.toString().toDoubleOrNull()
                if (value != null) {
                    if (value < 100) {
                        mostrarAlertaPersonalizada(
                            "La Presion bancada izquierda está por fuera del rango. Realiza lo siguiente:\n\n" +
                                    "REALIZAR CAMBIO EN BANCADA DE OXIGENO \n REVISAR FUGAS EN TANQUES DE OXIGENO."
                        )
                    }
                }
            }
        }

        //campo presion bancada derecha
        preBanDerr.isEnabled = true
        preBanDerr.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val value = preBanDerr.text.toString().toDoubleOrNull()
                if (value != null) {
                    if (value < 100) {
                        mostrarAlertaPersonalizada(
                            "La Presion bancada derecha está por fuera del rango. Realiza lo siguiente:\n\n" +
                                    "REALIZAR CAMBIO EN BANCADA DE OXIGENO \n REVISAR FUGAS EN TANQUES DE OXIGENO."
                        )
                    }
                }
            }
        }

//campo funcionamiento extractor de gases
        sPextGaa.isEnabled = true
        //qr
        val qrLauncher1 = registerForActivityResult(ScanContract()) { result ->
            if (result.contents != null) {
                btnQR3 = result.contents
                if (btnQR3 == "NS-QR-MTTO-D-017") {
                    sPextGaa.isEnabled = true
                    Toast.makeText(this, "Código del Área: $btnQR3", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Error: Código QR incorrecto", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "No se escaneó ningún código QR", Toast.LENGTH_SHORT).show()
            }
        }
        //iniciar qr
        btnScanQrr3.setOnClickListener {

                val options = ScanOptions()
                options.setPrompt("Escanea el código QR")
                options.setBeepEnabled(true)
                options.setBarcodeImageEnabled(true)
                qrLauncher1.launch(options) // Inicia el escaneo
        }
        //validaciones
        sPextGaa.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "NO") {
                    mostrarAlertaPersonalizada(
                        "REVISAR SUMINISTRO ELÉCTRICO DE EXTRACTOR DE GASES\n\nREVISAR PASTILLA DE EXTRACTOR DE GASES EN CUARTO DE MAQUINAS\""
                    )
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        //campo presion tanque aire medicinal
        preTanqAiMedd.isEnabled =true
        sPfunComp1.isEnabled = true
        sPfunComp2.isEnabled = true
        sPRevSeca1.isEnabled = true
        sPRevSeca2.isEnabled = true
        //qr
        val qrLauncher2 = registerForActivityResult(ScanContract()) { result ->
            if (result.contents != null) {
                btnQR4 = result.contents
                if (btnQR4 == "NS-QR-MTTO-D-018") {
                    preTanqAiMedd.isEnabled = true
                    sPfunComp1.isEnabled = true
                    sPfunComp2.isEnabled = true
                    sPRevSeca1.isEnabled = true
                    sPRevSeca2.isEnabled = true
                    Toast.makeText(this, "Código del Área: $btnQR4", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Error: Código QR incorrecto", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "No se escaneó ningún código QR", Toast.LENGTH_SHORT).show()
            }
        }
        //iniciar qr
        btnScanQrr4.setOnClickListener {
            val options = ScanOptions()
            options.setPrompt("Escanea el código QR")
            options.setBeepEnabled(true)
            options.setBarcodeImageEnabled(true)
            qrLauncher2.launch(options) // Inicia el escaneo
        }
        //validaciones
        preTanqAiMedd.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus){
                val value = preTanqAiMedd.text.toString().toDoubleOrNull()
                if(value != null){
                    if (value < 100) {
                        mostrarAlertaPersonalizada(
                            "La Presion bancada izquierda está por fuera del rango. Realiza lo siguiente:\n\n" +
                                    "REVISAR FUNCIONAMIENTO DE COMPRESORES DE AIRE MEDICINAL."
                        )
                    }
                }
            }
        }

        //menu desplegable compresora1
        sPfunComp1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
    override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
        val seleccion = parent.getItemAtPosition(position).toString()
        if (seleccion == "NO") {
            mostrarAlertaPersonalizada(
                "REVISAR SUMINISTRO ELÉCTRICO DE COMPRESOR Y TABLERO DE CONTROL"
            )
        }
    }
    override fun onNothingSelected(parent: AdapterView<*>) {}
}
        //menu desplegable compresora2
        sPfunComp2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "NO") {
                    mostrarAlertaPersonalizada(
                        "REVISAR SUMINISTRO ELÉCTRICO DE COMPRESOR Y TABLERO DE CONTROL"
                    )
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        //menu deplegable secadora1
        sPRevSeca1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "NO") {
                    mostrarAlertaPersonalizada(
                        "REVISAR SUMINISTRO ELÉCTRICO DE SECADORA Y TABLERO DE CONTROL"
                    )
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        //menu desplegable secadora2
        sPRevSeca2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "NO") {
                    mostrarAlertaPersonalizada(
                        "REVISAR SUMINISTRO ELÉCTRICO DE SECADORA Y TABLERO DE CONTROL"
                    )
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }


        //campo presion de tanque de vacio
        preTanVac1.isEnabled = true
        sPFunBomVac1.isEnabled = true
        sPFunBomVac2.isEnabled = true
        //qr
        val qrLauncher3 = registerForActivityResult(ScanContract()) { result ->
            if (result.contents != null) {
                btnQR9 = result.contents
                if (btnQR9 == "NS-QR-MTTO-D-019") {
                    preTanVac1.isEnabled = true
                    sPFunBomVac1.isEnabled = true
                    sPFunBomVac2.isEnabled = true

                    Toast.makeText(this, "Código del Área: $btnQR9", Toast.LENGTH_SHORT).show()
                } else {

                    Toast.makeText(this, "Error: Código QR incorrecto", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "No se escaneó ningún código QR", Toast.LENGTH_SHORT).show()
            }
        }
        //iniciar qr
        btnScanQrr9.setOnClickListener {
            val options = ScanOptions()
            options.setPrompt("Escanea el código QR")
            options.setBeepEnabled(true)
            options.setBarcodeImageEnabled(true)
            qrLauncher3.launch(options) // Inicia el escaneo
        }
        //validaciones
        preTanVac1.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val value = preTanVac1.text.toString().toDoubleOrNull()
                if (value != null) {
                    if (value > -85 || value < -50) {
                        mostrarAlertaPersonalizada(
                            "La Presioón está por fuera del rango. Realiza lo siguiente:\n\n" +
                                    "REALIZAR REVISIÓN DE FUNCIONAMIENTO TE BOMBAS DE VACIO"
                        )
                    }
                }
            }
        }

        //menu desplegable bomba de vacio 1
        sPFunBomVac1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "NO") {
                    mostrarAlertaPersonalizada(
                        "REVISAR SUMINISTRO ELÉCTRICO DE BOMBAS DE VACIO \nTABLERO DE CONTROL"
                    )
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        //menu desplegable bomba de vacio 2}
        sPFunBomVac2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "NO") {
                    mostrarAlertaPersonalizada(
                        "REVISAR SUMINISTRO ELÉCTRICO DE BOMBAS DE VACIO \nTABLERO DE CONTROL"
                    )
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        sPEncBombVacDeEvacGasAne.isEnabled = true
        //qr
        val qrLauncher4 = registerForActivityResult(ScanContract()) { result ->
            if (result.contents != null) {
                btnQR12 = result.contents
                if (btnQR12 == "NS-QR-MTTO-D-020") {
                    sPEncBombVacDeEvacGasAne.isEnabled = true

                    Toast.makeText(this, "Código del Área: $btnQR12", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Error: Código QR incorrecto", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "No se escaneó ningún código QR", Toast.LENGTH_SHORT).show()
            }
        }
        //iniciar qr
        btnScanQrr12.setOnClickListener {
            val options = ScanOptions()
            options.setPrompt("Escanea el código QR")
            options.setBeepEnabled(true)
            options.setBarcodeImageEnabled(true)
            qrLauncher4.launch(options) // Inicia el escaneo
        }
        //validaciones
        sPEncBombVacDeEvacGasAne.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "NO") {
                    mostrarAlertaPersonalizada(
                        "REVISAR SUMINISTRO ELÉCTRICO DE BOMBAS DE VACIO \nTABLERO DE CONTROL"
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

        enviarExcel1.setOnClickListener {
            val datosFormulario1 = listOf(
                preBanIzz.text.toString(),
                preBanDerr.text.toString(),
                sPextGaa.selectedItem.toString(),
                spinnerCambioBancada.selectedItem.toString(),
                spinnerRevisionFugas.selectedItem.toString(),
                preTanqAiMedd.text.toString(),
                sPfunComp1.selectedItem.toString(),
                sPfunComp2.selectedItem.toString(),
                sPRevSeca1.selectedItem.toString(),
                sPRevSeca2.selectedItem.toString(),
                preTanVac1.text.toString(),
                sPFunBomVac1.selectedItem.toString(),
                sPFunBomVac2.selectedItem.toString(),
                sPEncBombVacDeEvacGasAne.selectedItem.toString(),
                etHallazgosGasMed.text.toString(),
                SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault()).format(Date())
            )

            manejarExcel(
                context = this, fileName = "RecorridoMantenimiento.xlsx",
                sheetName = "RecorridoGasesMedicinales",
                datos = datosFormulario1,
                headers = listOf(
                    "Presión bancada izquierda",
                    "Presión bancada Derecha",
                    "Funcionamiento extractor de gases",
                    "Cambio de bancada",
                    "Revisión fugas bancada",
                    "Presión de tanque de aire medicinal",
                    "Fun. compresora 1 de aire medicinal",
                    "Fun. compresora 2 de aire medicinal",
                    "Revisión de secadora 1 aire medicinal",
                    "Revisión de secadora 2 aire medicinal",
                    "Presión de tanque de vacío",
                    "Funcionamiento bomba de vacío 1",
                    "Funcionamiento bomba de vacío 2",
                    "Encendido de bombas de vacío de evacuación de gases anestésicos",
                    "Hallazgos",
                    "Fecha y hora"

                )
            )

            val sharedPreferences = getSharedPreferences("StatusPref", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("statusGasMed", "completed")
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