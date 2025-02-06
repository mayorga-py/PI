package com.example.proyectnewro

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.proyectnewro.databinding.ActivityMainBinding
import com.google.zxing.client.android.Intents.Scan

import com.journeyapps.barcodescanner.ScanContract //scan qr
import com.journeyapps.barcodescanner.ScanOptions  //scan qr

import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

class FormAP : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("MissingInflatedId", "SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(R.layout.activity_form_ap)
        //nombre actividad
        val nombreActPorwc = findViewById<TextView>(R.id.textView4)
        val porWC = findViewById<EditText>(R.id.NivelWC)
        //nombre actividad
        val nombreActPorAPlu = findViewById<TextView>(R.id.textView5)
        val porAPlu = findViewById<EditText>(R.id.NivelAPlu)
        //nombre actividad
        val nombreActpreAWC = findViewById<TextView>(R.id.textView6)
        val preAWC = findViewById<EditText>(R.id.PresionAWC)
        //nombre actividad
        val nombreActFunbomtrat1 = findViewById<TextView>(R.id.textView7)
        val nombreActFunbomtrat2 = findViewById<TextView>(R.id.textView8)
        val funBom1AguaTratBox = findViewById<CheckBox>(R.id.FunBomAT1) //check box
        val funBom2AguaTratBox = findViewById<CheckBox>(R.id.FunBomAT2) //check box
        //nombre actividad
        val nombreActporCarAC = findViewById<TextView>(R.id.textView9)
        val nombreAcrcRLAgua = findViewById<TextView>(R.id.textView10)
        val porCarAC = findViewById<EditText>(R.id.NivelCarAC)
        val cRLAgua = findViewById<EditText>(R.id.CRLCarAC)
        //
        val nombreActporAOs = findViewById<TextView>(R.id.textView11)
        val porAOs = findViewById<EditText>(R.id.NivelAOs)
        val nombreActpreAOs = findViewById<TextView>(R.id.textView12)
        val preAOs = findViewById<EditText>(R.id.PresionAOs)
        //
        val nombreActFunLIOS1 = findViewById<TextView>(R.id.textView13)
        val funBom1LiOs = findViewById<CheckBox>(R.id.funcBomAO1) //check box
        val nombreActFunLIOS2 = findViewById<TextView>(R.id.textView14)
        val funBom2LiOs = findViewById<CheckBox>(R.id.funcBomAO2) //check box
        //
        val nombreActporSisAF = findViewById<TextView>(R.id.textView16)
        val porSisAF = findViewById<EditText>(R.id.NivelSisAF)
        val nombreActpreLinAF = findViewById<TextView>(R.id.textView17)
        val preLinAF = findViewById<EditText>(R.id.PresionLAF)
        val nombreActfunBomb1AF = findViewById<TextView>(R.id.textView18)
        val funBom1AF = findViewById<CheckBox>(R.id.funBF1) //check box
        val nombreActfunBomb2AF = findViewById<TextView>(R.id.textView19)
        val funBom2AF = findViewById<CheckBox>(R.id.funBF2) //check box

        val pH = findViewById<EditText>(R.id.PHaguafil)
        val preAP = findViewById<EditText>(R.id.PresionAP)
        val funBom1AP = findViewById<CheckBox>(R.id.FunBomAP1) //check box
        val funBom2AP = findViewById<CheckBox>(R.id.FunBomAP2) //check box

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


        var qrCodeText1: String? = null
        var qrCodeText2: String? = null
        var qrCodeText3: String? = null
        var qrCodeText4: String? = null
        var qrCodeText5: String? = null
        var qrCodeText6: String? = null
        var qrCodeText7: String? = null
        var qrCodeText8: String? = null
        var qrCodeText9: String? = null
        var qrCodeText10: String? = null



        //checkbox inicializados funcionando
        funBom1AguaTratBox.isChecked = true
        funBom2AguaTratBox.isChecked = true
        funBom1LiOs.isChecked = true
        funBom2LiOs.isChecked = true
        funBom1AF.isChecked = true
        funBom2AF.isChecked = true
        funBom1AP.isChecked = true
        funBom2AP.isChecked = true

// campo de porcentaje de wc
        porWC.isEnabled = false
        //Iniciar el escáner y obtener el resultado
        val qrLauncher = registerForActivityResult(ScanContract()) { result ->
            if (result.contents != null) {
                qrCodeText1 = result.contents //obtenemos el texto escaneado
                porWC.isEnabled = true // Habilitar el campo solo si se escanea correctamente
                Toast.makeText(this, "Codigo del Area: $qrCodeText1", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "No se escaneó ningún código QR", Toast.LENGTH_SHORT).show()
            }
        }
        // abrir scaner
        btnScanQR1.setOnClickListener {
            val options = ScanOptions()
            options.setPrompt("Escanea el código QR")
            options.setBeepEnabled(true)
            options.setBarcodeImageEnabled(true)
            qrLauncher.launch(options) // Inicia el escaneo
        }
        //validaciones
        porWC.setOnFocusChangeListener { _, hasFocus ->
            //val nombreAct = "prodis" como mando el nombre de la actividad predeterminada por mi
            if (!hasFocus) {
                val value = porWC.text.toString().toDoubleOrNull()
                if (value != null) {
                    if (value < 50 || value > 100) {
                        // Mostrar advertencia si el valor está fuera del rango
                        val alertDialog = AlertDialog.Builder(this@FormAP)
                            .setTitle("Advertencia de nivel de agua")
                            .setMessage(
                                "El porcentaje de agua está por fuera del rango. Realiza lo siguiente:\n\n" +
                                        "ABRIR LLAVE DE LLENADO DE CISTERNA Ó \nREALIZAR TRASVASE DE AGUA PLUVIAL."
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

// campo Agua pluvial
        porAPlu.isEnabled = false
        //obtener el resultado
        val qrLauncher1 = registerForActivityResult(ScanContract()) { result ->
            if (result.contents != null) {
                qrCodeText2 = result.contents
                porAPlu.isEnabled = true
                Toast.makeText(this,"Codigo del Area: $qrCodeText2",Toast.LENGTH_SHORT).show()
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
            if (!hasFocus){
                val value = porAPlu.text.toString().toDoubleOrNull()
                if (value != null){
                    if (value < 20 || value > 70){
                        val alertDialog = AlertDialog.Builder(this@FormAP)
                            .setTitle("Advertencia nivel de agua pluvial")
                            .setMessage("El porcentaje de agua plucial está por fuera del rango.\n Realiza lo siguiente:\n" +
                                    "REALIZAR TRASVASE DE AGUA")
                            .setPositiveButton("Aceptar"){dialog, _ ->
                                dialog.dismiss()
                            }
                            .create()
                        alertDialog.show()
                    }
                }
            }
        }
// campo presion Agua WC
        preAWC.isEnabled = false
        //
        val qrLauncher2 = registerForActivityResult(ScanContract()) { result ->
            if (result.contents != null){
                qrCodeText3 = result.contents
                preAWC.isEnabled = true
                Toast.makeText(this,"Codigo del Area: $qrCodeText3",Toast.LENGTH_SHORT).show()
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
                if (!hasFocus){
                    val value = preAWC.text.toString().toDoubleOrNull()
                    if (value != null){
                        if (value < 4 || value > 6.0){
                            val alertDialog = AlertDialog.Builder(this@FormAP)
                                .setTitle("Advertencia presion de agua WC")
                                .setMessage("La presion ingresada esta por fuera del rango permitido.\n Realiza lo siguiente:\n" +
                                        "REVISAR SUMINISTRO ELÉCRICO E INTERRUPTORES INDIVIDUALES DE TABLEREO BOMBAS \n" +
                                        "PURGAR BOMBAS \nREVISAR FUGAS EN SELLO MECÁNICO Y PICHANCHA")
                                .setPositiveButton("Aceptar"){dialog, _ ->
                                    dialog.dismiss()
                                }
                                .create()
                            alertDialog.show()
                        }
                    }
                }
            }

//funcionamiento bomba 1 agua trat
        funBom1AguaTratBox.setOnCheckedChangeListener { _, isChecked ->
            if (!isChecked) {
                // Si el CheckBox no está seleccionado, mostramos la advertencia
                val alertDialog = AlertDialog.Builder(this@FormAP)
                    .setTitle("Advertencia bomba 1 de agua tratada")
                    .setMessage("Verifica que todo esté funcionando correctamente.")
                    .setPositiveButton("Aceptar") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .create()
                alertDialog.show()
            }
        }
//funcionamiento bomba 2 agua trat
        funBom2AguaTratBox.setOnCheckedChangeListener { _, isChecked ->
            if (!isChecked){
                val alertDialog = AlertDialog.Builder(this@FormAP)
                    .setTitle("Advertencia bomba 2 de agua tratada")
                    .setMessage("Verifica que todo este funcionando correctamente")
                    .setPositiveButton("Aceptar") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .create()
                alertDialog.show()
            }
        }

// campo nivel carcamo agua cruda
        porCarAC.isEnabled = false
        //
        val qrLauncher3 = registerForActivityResult(ScanContract()){ result ->
            if (result.contents != null){
                qrCodeText4 = result.contents
                porCarAC.isEnabled = true
                cRLAgua.isEnabled = true
                Toast.makeText(this,"Codigo del Area: $qrCodeText4", Toast.LENGTH_SHORT).show()
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
            if (!hasFocus){
                val value = porCarAC.text.toString().toDoubleOrNull()
                if (value != null){
                    if (value < 90 || value > 100){
                        val alertDialog = AlertDialog.Builder(this@FormAP)
                            .setTitle("Advertencia nivel de carcamo agua cruda")
                            .setMessage("El porcentaje ingresado esta por fuera del rango permitido.\n Realiza lo siguiente:\n" +
                                    "REVISAR NIVELES DE CISTERNAS DE AGUA CRUDA Y\n" +
                                    "SUMINISTRO DE CEA")
                            .setPositiveButton("Aceptar"){dialog, _ ->
                                dialog.dismiss()
                            }
                            .create()
                        alertDialog.show()
                    }
                }
            }
        }
// CRL de agua cruda (ppm)
        cRLAgua.isEnabled = false
        // utiliza el QR de carcamo de agua
        cRLAgua.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus){
                val value = cRLAgua.text.toString().toDoubleOrNull()
                if (value != null){
                    if (value < 0.20 || value > 1.5){
                        val alertDialog = AlertDialog.Builder(this@FormAP)
                            .setTitle("Advertencia C.R.L. de agua cruda (PPM)")
                            .setMessage("El registro ingresado esta por fuera del rango permitido.\n Realiza lo siguiente:\n" +
                                    "SI ES MENOR A .20 COLOCAR PASTILLA DE CLORO EN CARCAMO Y REVISAR CLORO EN CISTERNAS DE AGUA CRUDA\n" +
                                    "SI ES MAYOR A 1.5 MEDIR CLORO DE AGUA FILTRADA")
                            .setPositiveButton("Aceptar"){dialog, _ ->
                                dialog.dismiss()
                            }
                            .create()
                        alertDialog.show()
                    }
                }
            }
        }
// nivel agua de osmosis
        porAOs.isEnabled = false
        //
        val qrLauncher4 = registerForActivityResult(ScanContract()){result ->
            if (result.contents != null){
                qrCodeText5 = result.contents
                porAOs.isEnabled = true
                Toast.makeText(this,"Codigo del Area: $qrCodeText5", Toast.LENGTH_SHORT).show()
            }
        }
        //
        btnScanQR5.setOnClickListener {
            val options4 = ScanOptions()
            options4.setPrompt("Escanea el codigo QR0")
            options4.setBeepEnabled(true)
            options4.setBarcodeImageEnabled(true)
            qrLauncher4.launch(options4)
        }
        //
        porAOs.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus){
                val value = porAOs.text.toString().toDoubleOrNull()
                if (value != null){
                    if (value < 50 || value > 100){
                        val alertDialog = AlertDialog.Builder(this@FormAP)
                            .setTitle("Advertencia Nivel de agua de osmosis")
                            .setMessage("El porcentaje ingresado esta por fuera del rango permitido.\n Realiza lo siguiente:\n" +
                                    "REALIZAR EL LLENADO DE TINACO DE OSMOSIS Y\n" +
                                    "FUNCIONAMIENTO DE FLOTADORES")
                            .setPositiveButton("Aceptar"){dialog, _ ->
                                dialog.dismiss()
                            }
                            .create()
                        alertDialog.show()
                    }
                }
            }
        }
// presion de linea de osmosis
        preAOs.isEnabled = false
        //
        val qrLauncher5 = registerForActivityResult(ScanContract()){ result ->
            if (result.contents!=null){
                qrCodeText6 = result.contents
                preAOs.isEnabled = true
                Toast.makeText(this,"Codigo del Area: $qrCodeText6", Toast.LENGTH_SHORT).show()
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
            if (!hasFocus){
                val value = preAOs.text.toString().toDoubleOrNull()
                if (value != null){
                    if (value < 4 || value > 7 ){
                        val alertDialog = AlertDialog.Builder(this@FormAP)
                            .setTitle("Advertencia Presion de linea de osmosis")
                            .setMessage("La presion ingresada esta por fuera del rango permitido.\n Realiza lo siguiente:\n" +
                                    "REVISAR EL SIMINISTRO ELECTRICO\n" +
                                    "INTERRUPTORES INDIVIDUALES DE TABLERO BOMBAS")
                            .setPositiveButton("Aceptar"){dialog, _ ->
                                dialog.dismiss()
                            }
                            .create()
                        alertDialog.show()
                    }
                }
            }
        }

//funcionamiento bomba 1 linea osmosis
        funBom1LiOs.setOnCheckedChangeListener { _, isChecked ->
            if (!isChecked){
                val alertDialog = AlertDialog.Builder(this@FormAP)
                    .setTitle("Advertencia bomba 1 de agua de osmosis")
                    .setMessage("Verifica que todo este funcionando correctamente")
                    .setPositiveButton("Aceptar") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .create()
                alertDialog.show()
            }
        }
//funcionamiento bomba 2 linea osmosis
        funBom1LiOs.setOnCheckedChangeListener { _, isChecked ->
            if (!isChecked){
                val alertDialog = AlertDialog.Builder(this@FormAP)
                    .setTitle("Advertencia bomba 2 de agua de osmosis")
                    .setMessage("Verifica que todo este funcionando correctamente")
                    .setPositiveButton("Aceptar") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .create()
                alertDialog.show()
            }
        }

// nivel cisterna agua filtrada
        porSisAF.isEnabled = false
        //
        val qrLauncher6 = registerForActivityResult(ScanContract()) { result ->
            if (result.contents!=null){
                qrCodeText7 = result.contents
                porSisAF.isEnabled = true
                Toast.makeText(this,"Codigo del Area: $qrCodeText7", Toast.LENGTH_SHORT).show()
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
            if (!hasFocus){
                val value = porSisAF.text.toString().toDoubleOrNull()
                if (value != null){
                    if (value < 90 || value > 100 ){
                        val alertDialog = AlertDialog.Builder(this@FormAP)
                            .setTitle("Advertencia Nivel cisterna Agua Filtrada")
                            .setMessage("El porcentaje ingresado esta por fuera del rango permitido.\n Realiza lo siguiente:\n" +
                                    "REVISAR LLAVES DE PASO DE TUBERÍA ABIERTAS\nSUMINISTRO ELECTRICO E INTERRUPTORES INDIVIDUALES DE TABLERO BOMBAS AGUA CRUDA" +
                                    "REVISAR FUNCIONAMIENTO EN MANUAL DE BOMBAS DE AGUA CRUDA")
                            .setPositiveButton("Aceptar"){dialog, _ ->
                                dialog.dismiss()
                            }
                            .create()
                        alertDialog.show()
                    }
                }
            }
        }
//presion linea agua filtrada
        preLinAF.isEnabled = false
        //
        val qrLauncher7 = registerForActivityResult(ScanContract()){ result ->
            if (result.contents!=null){
                qrCodeText8 = result.contents
                preLinAF.isEnabled = true
                Toast.makeText(this, "Codigo del Area: $qrCodeText8", Toast.LENGTH_SHORT).show()
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
            if (!hasFocus){
                val value = preLinAF.text.toString().toDoubleOrNull()
                if (value != null){
                    if (value < 4 || value > 7.5 ){
                        val alertDialog = AlertDialog.Builder(this@FormAP)
                            .setTitle("Advertencia Presion linea agua filtrada")
                            .setMessage("El porcentaje ingresado esta por fuera del rango permitido.\n Realiza lo siguiente:\n" +
                                    "REVISAR SUMINISTRO ELECTRICO E INTERRUPTORES INDIVIDUALES EN TABLERO BOMBAS\n" +
                                    "FUNCIONAMIENTO DE BOMBAS")
                            .setPositiveButton("Aceptar"){dialog, _ ->
                                dialog.dismiss()
                            }
                            .create()
                        alertDialog.show()
                    }
                }
            }
        }

//funconamiento bomba 1 agua filtrada
        funBom1AF.setOnCheckedChangeListener { _, isChecked ->
            if (!isChecked){
                val alertDialog = AlertDialog.Builder(this@FormAP)
                    .setTitle("Advertencia bomba 1 de agua filtrada")
                    .setMessage("Verifica que todo este funcionando correctamente")
                    .setPositiveButton("Aceptar") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .create()
                alertDialog.show()
            }
        }
//funconamiento bomba 2 agua filtrada
        funBom2AF.setOnCheckedChangeListener { _, isChecked ->
            if (!isChecked){
                val alertDialog = AlertDialog.Builder(this@FormAP)
                    .setTitle("Advertencia bomba 2 de agua filtrada")
                    .setMessage("Verifica que todo este funcionando correctamente")
                    .setPositiveButton("Aceptar") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .create()
                alertDialog.show()
            }
        }

//pH agua filtrada
        pH.isEnabled = false
        //
        val qrLauncher8 = registerForActivityResult(ScanContract()){ result ->
            if (result.contents!=null){
                qrCodeText9 = result.contents
                pH.isEnabled = true
                Toast.makeText(this, "Codigo del Area: $qrCodeText9", Toast.LENGTH_SHORT).show()
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
            if (!hasFocus){
                val value = pH.text.toString().toDoubleOrNull()
                if (value != null){
                    if (value < 7.2 || value > 7.8 ){
                        val alertDialog = AlertDialog.Builder(this@FormAP)
                            .setTitle("Advertencia PH en agua filtrada")
                            .setMessage("El pH ingresado esta por fuera del rango permitido.\n Realiza lo siguiente:\n" +
                                    "REALIZAR MEDICION DE PH CON COLORIMETRO EN TOMAS FINALES\n" +
                                    "")
                            .setPositiveButton("Aceptar"){dialog, _ ->
                                dialog.dismiss()
                            }
                            .create()
                        alertDialog.show()
                    }
                }
            }
        }
// presion agua potable
        preAP.isEnabled = false
        //
        val qrLauncher9 = registerForActivityResult(ScanContract()){ result ->
            if (result.contents!=null){
                qrCodeText10 = result.contents
                pH.isEnabled = true
                Toast.makeText(this, "Codigo del Area: $qrCodeText10", Toast.LENGTH_SHORT).show()
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
            if (!hasFocus){
                val value = preAP.text.toString().toDoubleOrNull()
                if (value != null){
                    if (value < 4 || value > 6 ){
                        val alertDialog = AlertDialog.Builder(this@FormAP)
                            .setTitle("Advertencia Presion de agua potable")
                            .setMessage("La presion ingresada esta por fuera del rango permitido.\n Realiza lo siguiente:\n" +
                                    "REVISAR SUMINISTRO ELECTRICO E INTERRUPTORES INDIVIDUALES EN TABLERO BOMBAS\n" +
                                    "PURGAR BOMBAS \n REVISAR EN SELLO MECÁNICO Y PICHANCHA")
                            .setPositiveButton("Aceptar"){dialog, _ ->
                                dialog.dismiss()
                            }
                            .create()
                        alertDialog.show()
                    }
                }
            }
        }

//funcionamiento bomba 1 agua potable
        funBom1AP.setOnCheckedChangeListener { _, isChecked ->
            if (!isChecked){
                val alertDialog = AlertDialog.Builder(this@FormAP)
                    .setTitle("Advertencia bomba 1 de agua potable")
                    .setMessage("Verifica que todo este funcionando correctamente")
                    .setPositiveButton("Aceptar") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .create()
                alertDialog.show()
            }
        }
//funcionamiento bomba 2 agua potable
        funBom1AP.setOnCheckedChangeListener { _, isChecked ->
            if (!isChecked){
                val alertDialog = AlertDialog.Builder(this@FormAP)
                    .setTitle("Advertencia bomba 2 de agua potable")
                    .setMessage("Verifica que todo este funcionando correctamente")
                    .setPositiveButton("Aceptar") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .create()
                alertDialog.show()
            }
        }

//////////////////////////////////////BOTON DE ENVIAR///////////////////////////////////////////////
        btSend.setOnClickListener {
            //qrporwc
            val nombreActividad = nombreActPorwc.text.toString()
            val parametroWC = porWC.text.toString()
            //qraplu
            val nombreActividad1 = nombreActPorAPlu.text.toString()
            val parametroAPlu = porAPlu.text.toString()
            //qrpreawc
            val nombreActividad2 = nombreActpreAWC.text.toString()
            val parametropreAWC = preAWC.text.toString()
            //fun bomba
            val nombreActividad3 = nombreActFunbomtrat1.text.toString()
            val funBom1AguaTratEstado = if (funBom1AguaTratBox.isChecked) "Sí" else "No"
            val nombreActividad4 = nombreActFunbomtrat2.text.toString()
            val funBom1AguaTratEstado2 = if (funBom2AguaTratBox.isChecked) "Sí" else "No"
            //fun nivel carcamo agua cruda
            val nombreActividad5 = nombreActporCarAC.text.toString()
            val parametroporCarAC = porCarAC.text.toString()
            val nombreActividad6 = nombreAcrcRLAgua.text.toString()
            val parametrocRLAgua = cRLAgua.text.toString()
            //nivel de agua de osmosis
            val nombreActividad7 = nombreActporAOs.text.toString()
            val parametroporAOs = porAOs.text.toString()
            val nombreActividad8 = nombreActpreAOs.text.toString()
            val parametropreAOs = preAOs.text.toString()
            //fun bomba
            val nombreActividad9 = nombreActFunLIOS1.text.toString()
            val funBomb1LineaOs = if (funBom1LiOs.isChecked) "Sí" else "No"
            val nombreActividad10 = nombreActFunLIOS2.text.toString()
            val funBomb2LineaOs = if (funBom2LiOs.isChecked) "Sí" else "No"

            val nombreActividad11 = nombreActporSisAF.text.toString()
            val parametroporSisAF = porSisAF.text.toString()
            val nombreActividad12 = nombreActpreLinAF.text.toString()
            val parametropreLinAF = preLinAF.text.toString()
            val nombreActividad13 = nombreActfunBomb1AF.text.toString()
            val funBomba1AF = if (funBom1AF.isChecked) "Sí" else "No"
            val nombreActividad14 = nombreActfunBomb2AF.text.toString()
            val funBomba2AF = if (funBom2AF.isChecked) "Sí" else "No"



            val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm:ss",Locale.getDefault())
            val fechaHoraActual = sdf.format(Date())
            val downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            val fileName = "RecorridoMantenimiento.xlsx"
            val file = File(downloadsDir, fileName)
            val workbook: Workbook
            val sheet : Sheet

            if(file.exists()){
                val inputStream = file.inputStream()
                workbook = XSSFWorkbook(inputStream)
                sheet = workbook.getSheetAt(0)
            }else{
                // Si el archivo no existe, crearlo
                workbook = XSSFWorkbook()
                sheet = workbook.createSheet("RecorridoAguaPotable")

                // Agregar la fila de encabezados
                val headerRow = sheet.createRow(0)
                headerRow.createCell(0).setCellValue("Código")
                headerRow.createCell(1).setCellValue("Nombre Actividad")
                headerRow.createCell(2).setCellValue("Parámetro") // Nivel cisterna de servicio WC (%)
                headerRow.createCell(3).setCellValue("Código")
                headerRow.createCell(4).setCellValue("Nombre Actividad")
                headerRow.createCell(5).setCellValue("Parámetro") // Nivel cisterna de agua pluvial (%)
                headerRow.createCell(6).setCellValue("Código")
                headerRow.createCell(7).setCellValue("Nombre Actividad")
                headerRow.createCell(8).setCellValue("Parámetro")//Presión de línea agua WC (kgf/cm2)
                headerRow.createCell(9).setCellValue("Nombre Actividad")
                headerRow.createCell(10).setCellValue("Parametro")
                headerRow.createCell(11).setCellValue("Nombre Actividad")
                headerRow.createCell(12).setCellValue("Parametro")
                headerRow.createCell(13).setCellValue("Código") //nivel carcamos de agua
                headerRow.createCell(14).setCellValue("Nombre Actividad")
                headerRow.createCell(15).setCellValue("Parámetro")
                headerRow.createCell(16).setCellValue("Nombre Actividad")
                headerRow.createCell(17).setCellValue("Parámetro")
                headerRow.createCell(18).setCellValue("Código")
                headerRow.createCell(19).setCellValue("Nombre Actividad")
                headerRow.createCell(20).setCellValue("Parámetro")// nivel de agua de osmosis
                headerRow.createCell(21).setCellValue("Código")
                headerRow.createCell(22).setCellValue("Nombre Actividad")
                headerRow.createCell(23).setCellValue("Parámetro")// precion de agua de osmosis
                headerRow.createCell(24).setCellValue("Nombre Actividad")
                headerRow.createCell(25).setCellValue("Parametro")//checkbox
                headerRow.createCell(26).setCellValue("Nombre Actividad")
                headerRow.createCell(27).setCellValue("Parametro")//checkbox
                headerRow.createCell(28).setCellValue("Código")
                headerRow.createCell(29).setCellValue("Nombre Actividad")
                headerRow.createCell(30).setCellValue("Parámetro")// Nivel cisterna agua filtrada
                headerRow.createCell(31).setCellValue("Código")
                headerRow.createCell(32).setCellValue("Nombre Actividad")
                headerRow.createCell(33).setCellValue("Parámetro")// Presion linea de agua filtrada
                headerRow.createCell(34).setCellValue("Nombre Actividad")
                headerRow.createCell(35).setCellValue("Parámetro")// checkbox bomba agua filtrada
                headerRow.createCell(36).setCellValue("Nombre Actividad")
                headerRow.createCell(37).setCellValue("Parámetro")//checkbox bomba agua filtrada
                headerRow.createCell(38).setCellValue("Código")
                headerRow.createCell(39).setCellValue("Nombre Actividad")
                headerRow.createCell(40).setCellValue("Parámetro") // pH agua filtrada
                headerRow.createCell(41).setCellValue("Código")
                headerRow.createCell(42).setCellValue("Nombre Actividad")
                headerRow.createCell(43).setCellValue("Parámetro") //presion
                headerRow.createCell(44).setCellValue("Nombre Actividad")
                headerRow.createCell(45).setCellValue("Parámetro")
                headerRow.createCell(46).setCellValue("Nombre Actividad")
                headerRow.createCell(47).setCellValue("Parámetro")
                headerRow.createCell(48).setCellValue("Fecha y Hora")
            }
            // Agregar nueva fila con los datos
            val newRow = sheet.createRow(sheet.lastRowNum + 1)
            newRow.createCell(0).setCellValue(qrCodeText1) //QR
            newRow.createCell(1).setCellValue(nombreActividad)
            newRow.createCell(2).setCellValue(parametroWC) // Nivel cisterna de servicio WC (%)
            newRow.createCell(3).setCellValue(qrCodeText2) //QR
            newRow.createCell(4).setCellValue(nombreActividad1)
            newRow.createCell(5).setCellValue(parametroAPlu) // Nivel cisterna de agua pluvial (%)
            newRow.createCell(6).setCellValue(qrCodeText3) //QR
            newRow.createCell(7).setCellValue(nombreActividad2) //
            newRow.createCell(8).setCellValue(parametropreAWC) //Presión de línea agua WC (kgf/cm2)
            newRow.createCell(9).setCellValue(nombreActividad3) //
            newRow.createCell(10).setCellValue(funBom1AguaTratEstado)
            newRow.createCell(11).setCellValue(nombreActividad4) //
            newRow.createCell(12).setCellValue(funBom1AguaTratEstado2)
            newRow.createCell(13).setCellValue(qrCodeText4) //QR
            newRow.createCell(14).setCellValue(nombreActividad5)
            newRow.createCell(15).setCellValue(parametroporCarAC) //Nivel carcamo de agua
            newRow.createCell(16).setCellValue(nombreActividad6) //
            newRow.createCell(17).setCellValue(parametrocRLAgua)
            newRow.createCell(18).setCellValue(qrCodeText5) //QR
            newRow.createCell(19).setCellValue(nombreActividad7)
            newRow.createCell(20).setCellValue(parametroporAOs) //nivel de agua de osmosis
            newRow.createCell(21).setCellValue(qrCodeText6) //QR
            newRow.createCell(22).setCellValue(nombreActividad8)
            newRow.createCell(23).setCellValue(parametropreAOs) // presion agua de osmosdis
            newRow.createCell(24).setCellValue(nombreActividad9)
            newRow.createCell(25).setCellValue(funBomb1LineaOs)
            newRow.createCell(26).setCellValue(nombreActividad10) //
            newRow.createCell(27).setCellValue(funBomb2LineaOs)
            newRow.createCell(28).setCellValue(qrCodeText7) //QR
            newRow.createCell(29).setCellValue(nombreActividad11)
            newRow.createCell(30).setCellValue(parametroporSisAF) // nivel cisterna agua filtrada000
            newRow.createCell(31).setCellValue(qrCodeText8) //QR
            newRow.createCell(32).setCellValue(nombreActividad12)
            newRow.createCell(33).setCellValue(parametropreLinAF) // presion linea de agua filtrada
            newRow.createCell(34).setCellValue(nombreActividad13)
            newRow.createCell(35).setCellValue(funBomba1AF)//
            newRow.createCell(36).setCellValue(nombreActividad14)
            newRow.createCell(37).setCellValue(funBomba2AF)//





            // ultimos parametros enviados Fecha y hora
            newRow.createCell(13).setCellValue(fechaHoraActual)
            // nombre encargado

            // Escribir los datos en el archivo
            val outputStream = FileOutputStream(file)
            workbook.write(outputStream)
            outputStream.close()
            workbook.close()

            // Mostrar un mensaje para verificar que el archivo se ha creado correctamente
            if (file.exists()) {
                Toast.makeText(this, "Archivo Excel creado en Descargas", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Error al crear el archivo Excel", Toast.LENGTH_SHORT).show()
            }
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}