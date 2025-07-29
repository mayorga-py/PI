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
import android.widget.TextView
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.proyectnewro.databinding.ActivityMainBinding

import com.journeyapps.barcodescanner.ScanContract //scan qr
import com.journeyapps.barcodescanner.ScanOptions  //scan qr

import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

class FormAP : AppCompatActivity() {

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

    private lateinit var binding: ActivityMainBinding
    private lateinit var spinnerBomba1: Spinner
    private lateinit var spinnerBomba2: Spinner
    private lateinit var spinnerBomba1Osmosis: Spinner
    private lateinit var spinnerBomba2Osmosis: Spinner
    private lateinit var spinnerBomba1AF: Spinner
    private lateinit var spinnerBomba2AF: Spinner
    private lateinit var spinnerBomba1AP: Spinner
    private lateinit var spinnerBomba2AP: Spinner

    @SuppressLint("MissingInflatedId", "SimpleDateFormat", "CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(R.layout.activity_form_ap)



        val porWC = findViewById<EditText>(R.id.NivelWC)
        val porAPlu = findViewById<EditText>(R.id.NivelAPlu)
        val preAWC = findViewById<EditText>(R.id.PresionAWC)
        spinnerBomba1 = findViewById(R.id.spinnerFunAguaTrat)
        spinnerBomba2 = findViewById(R.id.spinnerFunAguaTrat2)
        val porCarAC = findViewById<EditText>(R.id.NivelCarAC)
        val cRLAgua = findViewById<EditText>(R.id.CRLCarAC)
        val porAOs = findViewById<EditText>(R.id.NivelAOs)
        val preAOs = findViewById<EditText>(R.id.PresionAOs)
        spinnerBomba1Osmosis = findViewById(R.id.spinnerBomba1Osmosis)
        spinnerBomba2Osmosis = findViewById(R.id.spinnerBomba2Osmosis)
        val porSisAF = findViewById<EditText>(R.id.NivelSisAF)
        val preLinAF = findViewById<EditText>(R.id.PresionLAF)
        spinnerBomba1AF = findViewById(R.id.spinnerBomba1AF)
        spinnerBomba2AF = findViewById(R.id.spinnerBomba2AF)
        val pH = findViewById<EditText>(R.id.PHaguafil)
        val preAP = findViewById<EditText>(R.id.PresionAP)
        spinnerBomba1AP = findViewById(R.id.spinnerBomba1AP)
        spinnerBomba2AP = findViewById(R.id.spinnerBomba2AP)
        val etHallazgosAp = findViewById<EditText>(R.id.etHaAP)
        val btSend = findViewById<Button>(R.id.btSend) //boton enviar
        //botones de scaner qr
        val btnScanQR1 = findViewById<Button>(R.id.btnScanQrNWC)
        val btnScanQR2 = findViewById<Button>(R.id.btnScanQrNCAPlu)
        val btnScanQR3 = findViewById<Button>(R.id.btnScanQrPLAWC)
        val btnScanQR4 = findViewById<Button>(R.id.btnScanQrNCAC)
        val btnScanQR5 = findViewById<Button>(R.id.btnScanQrNAOs)
        val btnScanQR6 = findViewById<Button>(R.id.btnScanQrPAOs)
        val btnScanQR7 = findViewById<Button>(R.id.btnScanQrNCAF)
        val btnScanQR8 = findViewById<Button>(R.id.btnScanQrPLAF)
        val btnScanQR9 = findViewById<Button>(R.id.btnScanQrpH)
        val btnScanQR10 = findViewById<Button>(R.id.btnScanQrpreAP)

        var qrCodeText1: String?
        var qrCodeText2: String?
        var qrCodeText3: String?
        var qrCodeText4: String?
        var qrCodeText5: String?
        var qrCodeText6: String?
        var qrCodeText7: String?
        var qrCodeText8: String?
        var qrCodeText9: String?
        var qrCodeText10: String?

// campo de porcentaje de wc
        porWC.isEnabled = true // Deshabilitar el campo por defecto
// Iniciar el escáner y obtener el resultado
        val qrLauncher = registerForActivityResult(ScanContract()) { result ->
            if (result.contents != null) {
                qrCodeText1 = result.contents // Obtenemos el texto escaneado
                if (qrCodeText1 == "NS-QR-MTTO-D-001") {
                    porWC.isEnabled = true // Habilitar el campo si el código es correcto
                    Toast.makeText(this, "Código del Área: $qrCodeText1", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Error: Código QR incorrecto", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "No se escaneó ningún código QR", Toast.LENGTH_SHORT).show()
            }
        }
// Abrir escáner
        btnScanQR1.setOnClickListener {
            val options = ScanOptions()
            options.setPrompt("Escanea el código QR")
            options.setBeepEnabled(true)
            options.setBarcodeImageEnabled(true)
            qrLauncher.launch(options) // Inicia el escaneo
        }
// Validaciones
        porWC.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val value = porWC.text.toString().toDoubleOrNull()
                if (value != null) {
                    if (value < 50 || value > 100) {
                        mostrarAlertaPersonalizada(
                            "El porcentaje de agua está por fuera del rango. Realiza lo siguiente:\n\n" +
                                    "ABRIR LLAVE DE LLENADO DE CISTERNA Ó\nREALIZAR TRASVASE DE AGUA PLUVIAL."
                        )
                    }
                }
            }
        }

// campo Agua pluvial
        porAPlu.isEnabled = true
        //obtener el resultado
        val qrLauncher1 = registerForActivityResult(ScanContract()) { result ->
            if (result.contents != null) {
                qrCodeText2 = result.contents
                if (qrCodeText2 == "NS-QR-MTTO-D-002") {
                    porAPlu.isEnabled = true
                    Toast.makeText(this, "Codigo del Area: $qrCodeText2", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Error: Código QR incorrecto", Toast.LENGTH_SHORT).show()
                }
            }

        }
        //abrir scaner
        btnScanQR2.setOnClickListener {
            val options1 = ScanOptions()
            options1.setPrompt("Escanea el código QR")
            options1.setBeepEnabled(true)
            options1.setBarcodeImageEnabled(true)
            qrLauncher1.launch(options1)
        }
        // validaciones
        porAPlu.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val value = porAPlu.text.toString().toDoubleOrNull()
                if (value != null) {
                    if (value < 20 || value > 70) {
                        mostrarAlertaPersonalizada(
                            "El porcentaje de agua pluvial está por fuera del rango.\nRealiza lo siguiente:\n\n" +
                                    "REALIZAR TRASVASE DE AGUA"
                        )
                    }
                }
            }
        }
// campo presion Agua WC
        preAWC.isEnabled = true
        //
        val qrLauncher2 = registerForActivityResult(ScanContract()) { result ->
            if (result.contents != null) {
                qrCodeText3 = result.contents
                if (qrCodeText3 == "NS-QR-MTTO-D-003") {
                    preAWC.isEnabled = true
                    spinnerBomba1.isEnabled = true
                    spinnerBomba2.isEnabled = true
                    Toast.makeText(this, "Codigo del Area: $qrCodeText3", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Error: Código QR incorrecto", Toast.LENGTH_SHORT).show()
                }
            }
        }
        //
        btnScanQR3.setOnClickListener {
            val options2 = ScanOptions()
            options2.setPrompt("Escanea el codigo QR")
            options2.setBeepEnabled(true)
            options2.setBarcodeImageEnabled(true)
            qrLauncher2.launch(options2)
        }
        //
        preAWC.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val value = preAWC.text.toString().toDoubleOrNull()
                if (value != null) {
                    if (value < 4 || value > 6) {
                        mostrarAlertaPersonalizada(
                            "La presión ingresada esta por fuera del rango permitido.\nRealiza lo siguiente:\n\n" +
                                    "REVISAR SUMINISTRO ELÉCRICO E INTERRUPTORES INDIVIDUALES DE TABLEREO BOMBAS \n" +
                                    "PURGAR BOMBAS \nREVISAR FUGAS EN SELLO MECÁNICO Y PICHANCHA"
                        )
                    }
                }
            }
        }


        val opciones = resources.getStringArray(R.array.opciones_bomba)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, opciones)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerBomba1.adapter = adapter
        spinnerBomba2.adapter = adapter

//funcionamiento bomba 1 agua trat
        spinnerBomba1.isEnabled = true
        spinnerBomba1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "NO") {
                    mostrarAlertaPersonalizada(
                        "REVISA EL INTERRUPTOR INDIVIDUAL DE BOMBA \nPURGA LA BOMBA \nREVISA FUGAS EN SELLO MÉCANICO Y PICHANCHA."
                    )
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

//funcionamiento bomba 2 agua trat
        spinnerBomba2.isEnabled = true
        spinnerBomba2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "NO") {
                    mostrarAlertaPersonalizada(
                        "REVISA EL INTERRUPTOR INDIVIDUAL DE BOMBA \nPURGA LA BOMBA \nREVISA FUGAS EN SELLO MÉCANICO Y PICHANCHA."
                    )
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

// campo nivel carcamo agua cruda
        porCarAC.isEnabled = true
        //
        val qrLauncher3 = registerForActivityResult(ScanContract()) { result ->
            if (result.contents != null) {
                qrCodeText4 = result.contents
                if (qrCodeText4 == "NS-QR-MTTO-D-004") {
                    porCarAC.isEnabled = true
                    cRLAgua.isEnabled = true
                    Toast.makeText(this, "Codigo del Area: $qrCodeText4", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Error: Código QR incorrecto", Toast.LENGTH_SHORT).show()
                }
            }
        }
        //
        btnScanQR4.setOnClickListener {
            val options3 = ScanOptions()
            options3.setPrompt("Escanea el codigo QR")
            options3.setBeepEnabled(true)
            options3.setBarcodeImageEnabled(true)
            qrLauncher3.launch(options3)
        }
        //
        porCarAC.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val value = porCarAC.text.toString().toDoubleOrNull()
                if (value != null) {
                    if (value < 90 || value > 100) {
                        mostrarAlertaPersonalizada(
                            "El porcentaje ingresado esta por fuera del rango permitido.\nRealiza lo siguiente:\n\n" +
                                    "REVISAR NIVELES DE CISTERNAS DE AGUA CRUDA Y\n" +
                                    "SUMINISTRO DE CEA"
                        )
                    }
                }
            }
        }
// CRL de agua cruda (ppm)
        cRLAgua.isEnabled = true
        // utiliza el QR de carcamo de agua
        cRLAgua.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val value = cRLAgua.text.toString().toDoubleOrNull()
                if (value != null) {
                    if (value < 0.2 || value > 1.5) {
                        mostrarAlertaPersonalizada(
                            "El registro ingresado esta por fuera del rango permitido.\nRealiza lo siguiente:\n\n" +
                                    "SI ES MENOR A .20 COLOCAR PASTILLA DE CLORO EN CARCAMO Y REVISAR CLORO EN CISTERNAS DE AGUA CRUDA\n" +
                                    "SI ES MAYOR A 1.5 MEDIR CLORO DE AGUA FILTRADA"
                        )
                    }
                }
            }
        }
// nivel agua de osmosis
        porAOs.isEnabled = true
        //
        val qrLauncher4 = registerForActivityResult(ScanContract()) { result ->
            if (result.contents != null) {
                qrCodeText5 = result.contents
                if (qrCodeText5 == "NS-QR-MTTO-D-005") {
                    porAOs.isEnabled = true
                    Toast.makeText(this, "Codigo del Area: $qrCodeText5", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Error: Código QR incorrecto", Toast.LENGTH_SHORT).show()
                }

            }
        }
        //
        btnScanQR5.setOnClickListener {
            val options4 = ScanOptions()
            options4.setPrompt("Escanea el codigo QR")
            options4.setBeepEnabled(true)
            options4.setBarcodeImageEnabled(true)
            qrLauncher4.launch(options4)
        }
        //
        porAOs.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val value = porAOs.text.toString().toDoubleOrNull()
                if (value != null) {
                    if (value < 50 || value > 100) {
                        mostrarAlertaPersonalizada(
                            "El porcentaje ingresado esta por fuera del rango permitido.\nRealiza lo siguiente:\n\n" +
                                    "REALIZAR EL LLENADO DE TINACO DE OSMOSIS Y\n" +
                                    "FUNCIONAMIENTO DE FLOTADORES"
                        )
                    }
                }
            }
        }
// presion de linea de osmosis
        preAOs.isEnabled = true
        //
        val qrLauncher5 = registerForActivityResult(ScanContract()) { result ->
            if (result.contents != null) {
                qrCodeText6 = result.contents
                if (qrCodeText6 == "NS-QR-MTTO-D-006") {
                    preAOs.isEnabled = true
                    spinnerBomba1Osmosis.isEnabled = true
                    spinnerBomba2Osmosis.isEnabled = true
                    Toast.makeText(this, "Codigo del Area: $qrCodeText6", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Error: Código QR incorrecto", Toast.LENGTH_SHORT).show()
                }
            }
        }
        //
        btnScanQR6.setOnClickListener {
            val options5 = ScanOptions()
            options5.setPrompt("Escanea el codigo QR")
            options5.setBeepEnabled(true)
            options5.setBarcodeImageEnabled(true)
            qrLauncher5.launch(options5)
        }
        //
        preAOs.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val value = preAOs.text.toString().toDoubleOrNull()
                if (value != null) {
                    if (value < 4 || value > 7) {
                        mostrarAlertaPersonalizada(
                            "La presión ingresada esta por fuera del rango permitido.\nRealiza lo siguiente:\n\n" +
                                    "REVISAR EL SIMINISTRO ELECTRICO\n" +
                                    "INTERRUPTORES INDIVIDUALES DE TABLERO BOMBAS"
                        )
                    }
                }
            }
        }

//funcionamiento bomba 1 linea osmosis
        // Configurar validaciones para Spinner de Bomba 1
        spinnerBomba1Osmosis.isEnabled = true
        spinnerBomba1Osmosis.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "NO") {
                    mostrarAlertaPersonalizada(
                        "REVISA SUMINISTRO ELECTRICO E \n INTERRUPTORES INDIVIDUALES DE TABLERO BOMBAS."
                    )
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

//funcionamiento bomba 2 linea osmosis
        // Configurar validaciones para Spinner de Bomba 2
        spinnerBomba2Osmosis.isEnabled = true
        spinnerBomba2Osmosis.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "NO") {
                    mostrarAlertaPersonalizada(
                        "REVISA SUMINISTRO ELECTRICO E \n INTERRUPTORES INDIVIDUALES DE TABLERO BOMBAS."
                    )
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }


// nivel cisterna agua filtrada
        porSisAF.isEnabled = true
        //
        val qrLauncher6 = registerForActivityResult(ScanContract()) { result ->
            if (result.contents != null) {
                qrCodeText7 = result.contents
                if (qrCodeText7 == "NS-QR-MTTO-D-007") {
                    porSisAF.isEnabled = true
                    Toast.makeText(this, "Codigo del Area: $qrCodeText7", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Error: Código QR incorrecto", Toast.LENGTH_SHORT).show()
                }
            }
        }
        //
        btnScanQR7.setOnClickListener {
            val options7 = ScanOptions()
            options7.setPrompt("Escanea el codigo QR")
            options7.setBeepEnabled(true)
            options7.setBarcodeImageEnabled(true)
            qrLauncher6.launch(options7)
        }
        //
        porSisAF.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val value = porSisAF.text.toString().toDoubleOrNull()
                if (value != null) {
                    if (value < 90 || value > 100) {
                        mostrarAlertaPersonalizada(
                            "El porcentaje ingresado esta por fuera del rango permitido.\nRealiza lo siguiente:\n\n" +
                                    "REVISAR LLAVES DE PASO DE TUBERÍA ABIERTAS\nSUMINISTRO ELECTRICO E INTERRUPTORES INDIVIDUALES DE TABLERO BOMBAS AGUA CRUDA" +
                                    "REVISAR FUNCIONAMIENTO EN MANUAL DE BOMBAS DE AGUA CRUDA"
                        )
                    }
                }
            }
        }
//presion linea agua filtrada
        preLinAF.isEnabled = true
        //
        val qrLauncher7 = registerForActivityResult(ScanContract()) { result ->
            if (result.contents != null) {
                qrCodeText8 = result.contents
                if (qrCodeText8 == "NS-QR-MTTO-D-008") {
                    preLinAF.isEnabled = true
                    spinnerBomba1AF.isEnabled = true
                    spinnerBomba2AF.isEnabled = true
                    Toast.makeText(this, "Codigo del Area: $qrCodeText8", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Error: Código QR incorrecto", Toast.LENGTH_SHORT).show()
                }

            }
        }
        //
        btnScanQR8.setOnClickListener {
            val options8 = ScanOptions()
            options8.setPrompt("Escanea el codigo QR")
            options8.setBeepEnabled(true)
            options8.setBarcodeImageEnabled(true)
            qrLauncher7.launch(options8)
        }
        //
        preLinAF.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val value = preLinAF.text.toString().toDoubleOrNull()
                if (value != null) {
                    if (value < 50 || value > 100) {
                        mostrarAlertaPersonalizada(
                            "El porcentaje ingresado esta por fuera del rango permitido.\nRealiza lo siguiente:\n\n" +
                                    "REVISAR SUMINISTRO ELECTRICO E INTERRUPTORES INDIVIDUALES EN TABLERO BOMBAS\n" +
                                    "FUNCIONAMIENTO DE BOMBAS"
                        )
                    }
                }
            }
        }

//funconamiento bomba 1 agua filtrada
        spinnerBomba1AF.isEnabled = true
        spinnerBomba1AF.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "NO") {
                    mostrarAlertaPersonalizada(
                        "REVISA SUMINISTRO ELECTRICO E \n INTERRUPTORES INDIVIDUALES DE TABLERO BOMBAS."
                    )
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
//funconamiento bomba 2 agua filtrada
        // Configurar validaciones para Spinner de Bomba 2 de Agua Filtrada
        spinnerBomba2AF.isEnabled = true
        spinnerBomba2AF.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "NO") {
                    mostrarAlertaPersonalizada(
                        "REVISA SUMINISTRO ELECTRICO E \n INTERRUPTORES INDIVIDUALES DE TABLERO BOMBAS."
                    )
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

//pH agua filtrada
        pH.isEnabled = true
        //
        val qrLauncher8 = registerForActivityResult(ScanContract()) { result ->
            if (result.contents != null) {
                qrCodeText9 = result.contents
                if (qrCodeText9 == "NS-QR-MTTO-D-009") {
                    pH.isEnabled = true
                    Toast.makeText(this, "Codigo del Area: $qrCodeText9", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Error: Código QR incorrecto", Toast.LENGTH_SHORT).show()
                }
            }
        }
        //
        btnScanQR9.setOnClickListener {
            val options9 = ScanOptions()
            options9.setPrompt("Escanea el codigo QR")
            options9.setBeepEnabled(true)
            options9.setBarcodeImageEnabled(true)
            qrLauncher8.launch(options9)
        }
        //
        pH.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val value = pH.text.toString().toDoubleOrNull()
                if (value != null) {
                    if (value < 6.5 || value > 8.5) {
                        mostrarAlertaPersonalizada(
                            "El pH ingresado esta por fuera del rango permitido.\nRealiza lo siguiente:\n\n" +
                                    "REALIZAR MEDICION DE PH CON COLORIMETRO EN TOMAS FINALES\n"
                        )
                    }
                }
            }
        }
// presion agua potable
        preAP.isEnabled = true
        //
        val qrLauncher9 = registerForActivityResult(ScanContract()) { result ->
            if (result.contents != null) {
                qrCodeText10 = result.contents
                if (qrCodeText10 == "NS-QR-MTTO-D-010") {
                    preAP.isEnabled = true
                    spinnerBomba1AP.isEnabled = true
                    spinnerBomba2AP.isEnabled = true
                    Toast.makeText(this, "Codigo del Area: $qrCodeText10", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(this, "Error: Código QR incorrecto", Toast.LENGTH_SHORT).show()
                }
            }
        }
        //
        btnScanQR10.setOnClickListener {
            val options10 = ScanOptions()
            options10.setPrompt("Escanea el codigo QR")
            options10.setBeepEnabled(true)
            options10.setBarcodeImageEnabled(true)
            qrLauncher9.launch(options10)
        }
        //
        preAP.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val value = preAP.text.toString().toDoubleOrNull()
                if (value != null) {
                    if (value < 4 || value > 6) {
                        mostrarAlertaPersonalizada(
                            "La presión ingresada esta por fuera del rango permitido.\nRealiza lo siguiente:\n\n" +
                                    "REVISAR SUMINISTRO ELECTRICO E INTERRUPTORES INDIVIDUALES EN TABLERO BOMBAS\n" +
                                    "PURGAR BOMBAS \n REVISAR EN SELLO MECÁNICO Y PICHANCHA"
                        )
                    }
                }
            }
        }

//funcionamiento bomba 1 agua potable
        // Configurar validaciones para Spinner de Bomba 1 de Agua Potable
        spinnerBomba1AP.isEnabled = true
        spinnerBomba1AP.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "NO") {
                    mostrarAlertaPersonalizada(
                        "REVISAR INTERRUPTOR INDIVIDUAL DE BOMBA \n PURGAR BOMBAS \n REVISAR DE FUGAS EN SELLO MÉCANICO Y PICHANCHA."
                    )
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
//funcionamiento bomba 2 agua potable
        // Configurar validaciones para Spinner de Bomba 2 de Agua Potable
        spinnerBomba2AP.isEnabled = true
        spinnerBomba2AP.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "NO") {
                    mostrarAlertaPersonalizada(
                        "REVISAR INTERRUPTOR INDIVIDUAL DE BOMBA \n PURGAR BOMBAS \n REVISAR DE FUGAS EN SELLO MÉCANICO Y PICHANCHA."
                    )
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        //////////////////////////////////////BOTON DE ENVIAR///////////////////////////////////////////////

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
// Llamar la función dentro del listener

        btSend.setOnClickListener {
            val datosFormulario1 = listOf(
                porWC.text.toString(),
                porAPlu.text.toString(),
                preAWC.text.toString(),
                spinnerBomba1.selectedItem.toString(),
                spinnerBomba2.selectedItem.toString(),
                porCarAC.text.toString(),
                cRLAgua.text.toString(),
                porAOs.text.toString(),
                preAOs.text.toString(),
                spinnerBomba1Osmosis.selectedItem.toString(),
                spinnerBomba2Osmosis.selectedItem.toString(),
                porSisAF.text.toString(),
                preLinAF.text.toString(),
                spinnerBomba1AF.selectedItem.toString(),
                spinnerBomba2AF.selectedItem.toString(),
                pH.text.toString(),
                preAP.text.toString(),
                spinnerBomba1AP.selectedItem.toString(),
                spinnerBomba2AP.selectedItem.toString(),
                etHallazgosAp.text.toString(),
                SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault()).format(Date())
            )

            manejarExcel(
                context = this,
                fileName = "RecorridoMantenimiento.xlsx",
                sheetName = "RecorridoAguaPotable",
                datos = datosFormulario1,
                headers = listOf(
                    "Nivel cisterna de servicio (%)",
                    "Nivel cisterna de agua pluvial (%)",
                    "Presión de línea de agua WC",
                    "Funcionamiento bomba agua tratada 1",
                    "Funcionamiento bomba agua tratada 2",
                    "Nivel cárcamo de agua cruda (%)",
                    "C.R.L. de agua cruda (PPM)",
                    "Nivel de agua de osmosis (%)",
                    "Presión de línea de osmosis (PSI)",
                    "Funcionamiento bomba agua de osmosis 1",
                    "Funcionamiento bomba agua de osmosis 2",
                    "Nivel cisterna agua filtrada (%)",
                    "Presión línea de agua filtrada (PSI)",
                    "Funcionamiento bomba agua filtrada 1",
                    "Funcionamiento bomba agua filtrada 2",
                    "PH en agua filtrada",
                    "Presion agua potable",
                    "Funcionamiento bomba agua potable 1",
                    "Funcionamiento bomba agua potable 2",
                    "Hallazgos",
                    "Fecha y Hora"
                )
            )

            val sharedPreferences = getSharedPreferences("StatusPref", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("statusAP", "completed")
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