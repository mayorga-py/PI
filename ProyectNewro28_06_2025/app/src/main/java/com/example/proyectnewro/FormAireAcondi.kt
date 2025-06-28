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

class FormAireAcondi : AppCompatActivity() {
    private lateinit var spiChill1 : Spinner
    private lateinit var spiChill2 : Spinner
    private lateinit var spiChill3 : Spinner
    private lateinit var spiChiller1 : Spinner
    private lateinit var spiChiller2 : Spinner
    private lateinit var spiChiller3 : Spinner
    private lateinit var spiChill4 : Spinner
    private lateinit var spiChill5 : Spinner
    private lateinit var spiINY1 : Spinner
    private lateinit var spiINY2 : Spinner
    private lateinit var spiINY3 : Spinner
    private lateinit var spiUMA1 : Spinner
    private lateinit var spiUMA2 : Spinner
    private lateinit var spiUMA3 : Spinner
    private lateinit var spiUMA4 : Spinner
    private lateinit var spiUMA5 : Spinner
    private lateinit var spiUMA7 : Spinner
    private lateinit var spiExt7 : Spinner
    private lateinit var spiExt16 : Spinner
    private lateinit var spiExt17 : Spinner
    private lateinit var spiExt18 : Spinner
    private lateinit var spiExt19 : Spinner
    private lateinit var spiExt8 : Spinner
    private lateinit var spiExt9 : Spinner
    private lateinit var spiExt10 : Spinner
    private lateinit var spiExt11 : Spinner
    private lateinit var spiExt12 : Spinner
    private lateinit var spiExt20 : Spinner
    private lateinit var spiExt21 : Spinner
    private lateinit var spiExt22 : Spinner
    private lateinit var spiExt23 : Spinner
    private lateinit var spiExt24 : Spinner

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_form_aire_acondi)

        val et = findViewById<EditText>(R.id.etPreBombChill)
        val btnpresionbombachiller = findViewById<Button>(R.id.btPreBombChill)
        spiChill1 = findViewById(R.id.spChill1)
        spiChill2 = findViewById(R.id.spChill2)
        spiChill3 = findViewById(R.id.spChill3)
        spiChiller1 = findViewById(R.id.spChiller1)
        val btnChiller1 = findViewById<Button>(R.id.btChiller1)
        spiChiller2 = findViewById(R.id.spChiller2)
        val btnChiller2 = findViewById<Button>(R.id.btChiller2)
        spiChiller3 = findViewById(R.id.spChiller3)
        val btnChiller3 = findViewById<Button>(R.id.btChiller3)
        spiChill4 = findViewById(R.id.spChiller4)
        spiChill5 = findViewById(R.id.spChiller5)
        val tempSalidaChill = findViewById<EditText>(R.id.etTempSalChiller)
        val btntempSall = findViewById<Button>(R.id.btTempSalChiller)
        spiINY1 = findViewById(R.id.spINY1)
        spiINY2 = findViewById(R.id.spINY2)
        spiINY3 = findViewById(R.id.spINY3)
        val btnINY3 = findViewById<Button>(R.id.btINY3)
        spiUMA1 = findViewById(R.id.spUMA1)
        spiUMA2 = findViewById(R.id.spUMA2)
        spiUMA3 = findViewById(R.id.spUMA3)
        spiUMA4 = findViewById(R.id.spUMA4)
        spiUMA5 = findViewById(R.id.spUMA5)
        spiUMA7 = findViewById(R.id.spUMA7)
        val btnUMA7 = findViewById<Button>(R.id.btUMA7)
        spiExt7 = findViewById(R.id.spExt7)
        val btnExt7 = findViewById<Button>(R.id.btExt7)
        spiExt16 = findViewById(R.id.spExt16)
        spiExt17 = findViewById(R.id.spExt17)
        spiExt18 = findViewById(R.id.spExt18)
        spiExt19 = findViewById(R.id.spExt19)
        spiExt8 = findViewById(R.id.spExt08)
        val btnExt08 = findViewById<Button>(R.id.btExt08)
        spiExt9 = findViewById(R.id.spExt09)
        spiExt10 = findViewById(R.id.spExt10)
        val btnExt10 = findViewById<Button>(R.id.btExt10)
        spiExt11 = findViewById(R.id.spExt11)
        val btnExt11 = findViewById<Button>(R.id.btExt11)
        spiExt12 = findViewById(R.id.spExt12)
        val btnExt12 = findViewById<Button>(R.id.btExt12)
        spiExt20 = findViewById(R.id.spExt20)
        spiExt21 = findViewById(R.id.spExt21)
        val btnExt21 = findViewById<Button>(R.id.btExt21)
        spiExt22 = findViewById(R.id.spExt22)
        spiExt23 = findViewById(R.id.spExt23)
        spiExt24 = findViewById(R.id.spExt24)
        val etporgaslpcalderas = findViewById<EditText>(R.id.etPorGaslpcalderas)
        val btnporgaslpcalderas = findViewById<Button>(R.id.btPorGaslpcalderas)
        val etporgaslpcocina = findViewById<EditText>(R.id.etPorGaslpcocina)
        val btnporgaslpcocina = findViewById<Button>(R.id.btPorGaslpcocina)
        val etHallazgosAireAcondi = findViewById<EditText>(R.id.etHaAAc)

        val continar = findViewById<Button>(R.id.continuar_rr)

        var btnQRR1: String?
        var btnQRR2: String?
        var btnQRR3: String?
        var btnQRR4: String?
        var btnQRR5: String?
        var btnQRR6: String?
        var btnQRR7: String?
        var btnQRR8: String?
        var btnQRR9: String?
        var btnQRR10: String?
        var btnQRR11: String?
        var btnQRR12: String?
        var btnQRR13: String?
        var btnQRR14: String?
        var btnQRR15: String?


        //presion bomba chiller   //chiller 1,2,3
        et.isEnabled = false
        spiChill1.isEnabled = false
        spiChill2.isEnabled = false
        spiChill3.isEnabled = false
        et.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val value = et.text.toString().toDoubleOrNull()
                if (value != null) {
                    if (value < 60) {
                        val alertDialog = AlertDialog.Builder(this@FormAireAcondi)
                            .setTitle("Advertencia Presion bomba chiller")
                            .setMessage(
                                "La Presion de bomba chiller esta por fuera del rango Realiza lo siguiente:\n\n" +
                                        "REVISAR FUNCIONAMIENTO BOMBAS CHILLERS"
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
        val qrLauncher1 = registerForActivityResult(ScanContract()) { result ->
            if (result.contents != null) {
                btnQRR1 = result.contents // Obtenemos el texto escaneado
                if (btnQRR1 == "NS-QR-MTTO-D-054") {//cambiar codigo de 001 A D-016
                    et.isEnabled = true // Habilitar el campo si el código es correcto
                    spiChill1.isEnabled = true
                    spiChill2.isEnabled = true
                    spiChill3.isEnabled = true
                    Toast.makeText(this, "Código del Área: $btnQRR1", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Error: Código QR incorrecto", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "No se escaneó ningún código QR", Toast.LENGTH_SHORT).show()
            }
        }
        btnpresionbombachiller.setOnClickListener {
            val options1 = ScanOptions()
            options1.setPrompt("Escanea el código QR")
            options1.setBeepEnabled(true)
            options1.setBarcodeImageEnabled(true)
            qrLauncher1.launch(options1) // Inicia el escaneo
        }
        spiChill1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "APAGADO") {
                    // Mostrar advertencia si la opción seleccionada es "No funcionando"
                    val alertDialog = AlertDialog.Builder(this@FormAireAcondi)
                        .setTitle("REVISAR CON ING CHAVA")
                        .setMessage("")
                        .setPositiveButton("Aceptar") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                    alertDialog.show()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        spiChill2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "APAGADO") {
                    // Mostrar advertencia si la opción seleccionada es "No funcionando"
                    val alertDialog = AlertDialog.Builder(this@FormAireAcondi)
                        .setTitle("REVISAR CON ING CHAVA")
                        .setMessage("")
                        .setPositiveButton("Aceptar") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                    alertDialog.show()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        spiChill3.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "APAGADO") {
                    // Mostrar advertencia si la opción seleccionada es "No funcionando"
                    val alertDialog = AlertDialog.Builder(this@FormAireAcondi)
                        .setTitle("REVISAR CON ING CHAVA")
                        .setMessage("")
                        .setPositiveButton("Aceptar") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                    alertDialog.show()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        //spinners chiller con qr
        spiChiller1.isEnabled = false
        spiChiller1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "APAGADO") {
                    // Mostrar advertencia si la opción seleccionada es "No funcionando"
                    val alertDialog = AlertDialog.Builder(this@FormAireAcondi)
                        .setTitle("Advertencia chiller 1")
                        .setMessage("RESTAURAR FUNCIONAMIENTO Y REGISTRAR ERROR EN HALLAZGOS")
                        .setPositiveButton("Aceptar") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                    alertDialog.show()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        val qrLauncher2 = registerForActivityResult(ScanContract()) { result ->
            if (result.contents != null) {
                btnQRR2 = result.contents // Obtenemos el texto escaneado
                if (btnQRR2 == "NS-QR-MTTO-D-055") {//cambiar codigo de 001 A D-016
                    spiChiller1.isEnabled = true // Habilitar el campo si el código es correcto
                    Toast.makeText(this, "Código del Área: $btnQRR2", Toast.LENGTH_SHORT).show()
                } else {
                    spiChiller1.isEnabled =false // Asegurarse de que el campo permanezca deshabilitado
                    Toast.makeText(this, "Error: Código QR incorrecto", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "No se escaneó ningún código QR", Toast.LENGTH_SHORT).show()
            }
        }
        btnChiller1.setOnClickListener {
            val options2 = ScanOptions()
            options2.setPrompt("Escanea el código QR")
            options2.setBeepEnabled(true)
            options2.setBarcodeImageEnabled(true)
            qrLauncher2.launch(options2) // Inicia el escaneo
        }

        spiChiller2.isEnabled = false
        spiChiller2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "APAGADO") {
                    // Mostrar advertencia si la opción seleccionada es "No funcionando"
                    val alertDialog = AlertDialog.Builder(this@FormAireAcondi)
                        .setTitle("Advertencia chiller 2")
                        .setMessage("RESTAURAR FUNCIONAMIENTO Y REGISTRAR ERROR EN HALLAZGOS")
                        .setPositiveButton("Aceptar") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                    alertDialog.show()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        val qrLauncher3 = registerForActivityResult(ScanContract()) { result ->
            if (result.contents != null) {
                btnQRR3 = result.contents // Obtenemos el texto escaneado
                if (btnQRR3 == "NS-QR-MTTO-D-056") {//cambiar codigo de 001 A D-016
                    spiChiller2.isEnabled = true // Habilitar el campo si el código es correcto
                    Toast.makeText(this, "Código del Área: $btnQRR3", Toast.LENGTH_SHORT).show()
                } else {
                    spiChiller2.isEnabled =
                        false // Asegurarse de que el campo permanezca deshabilitado
                    Toast.makeText(this, "Error: Código QR incorrecto", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "No se escaneó ningún código QR", Toast.LENGTH_SHORT).show()
            }
        }
        btnChiller2.setOnClickListener {
            val options3 = ScanOptions()
            options3.setPrompt("Escanea el código QR")
            options3.setBeepEnabled(true)
            options3.setBarcodeImageEnabled(true)
            qrLauncher3.launch(options3) // Inicia el escaneo
        }

        spiChiller3.isEnabled = false
        spiChiller3.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "APAGADO") {
                    // Mostrar advertencia si la opción seleccionada es "No funcionando"
                    val alertDialog = AlertDialog.Builder(this@FormAireAcondi)
                        .setTitle("Advertencia chiller 3")
                        .setMessage("RESTAURAR FUNCIONAMIENTO Y REGISTRAR ERROR EN HALLAZGOS")
                        .setPositiveButton("Aceptar") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                    alertDialog.show()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        val qrLauncher4 = registerForActivityResult(ScanContract()) { result ->
            if (result.contents != null) {
                btnQRR4 = result.contents // Obtenemos el texto escaneado
                if (btnQRR4 == "NS-QR-MTTO-D-057") {//cambiar codigo de 001 A D-016
                    spiChiller3.isEnabled = true // Habilitar el campo si el código es correcto
                    spiChill4.isEnabled = true
                    spiChill5.isEnabled = true
                    Toast.makeText(this, "Código del Área: $btnQRR4", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Error: Código QR incorrecto", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "No se escaneó ningún código QR", Toast.LENGTH_SHORT).show()
            }
        }
        btnChiller3.setOnClickListener {
            val options4 = ScanOptions()
            options4.setPrompt("Escanea el código QR")
            options4.setBeepEnabled(true)
            options4.setBarcodeImageEnabled(true)
            qrLauncher4.launch(options4) // Inicia el escaneo
        }

        //spinners chiller sin qr
        spiChill4.isEnabled = false
        spiChill5.isEnabled = false
        spiChill4.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "APAGADO") {
                    // Mostrar advertencia si la opción seleccionada es "No funcionando"
                    val alertDialog = AlertDialog.Builder(this@FormAireAcondi)
                        .setTitle("Advertencia chiller 4")
                        .setMessage("RESTAURAR FUNCIONAMIENTO Y REGISTRAR ERROR EN HALLAZGOS")
                        .setPositiveButton("Aceptar") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                    alertDialog.show()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        spiChill5.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "APAGADO") {
                    // Mostrar advertencia si la opción seleccionada es "No funcionando"
                    val alertDialog = AlertDialog.Builder(this@FormAireAcondi)
                        .setTitle("Advertencia chiller 3")
                        .setMessage("RESTAURAR FUNCIONAMIENTO Y REGISTRAR ERROR EN HALLAZGOS")
                        .setPositiveButton("Aceptar") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                    alertDialog.show()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        //temp salida chillers
        tempSalidaChill.isEnabled = false
        tempSalidaChill.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val value = et.text.toString().toDoubleOrNull()
                if (value != null) {
                    if (value < 10) {
                        val alertDialog = AlertDialog.Builder(this@FormAireAcondi)
                            .setTitle("Advertencia de temperatura chillers")
                            .setMessage(
                                "La temperattura del chiller esta por fuera del rango Realiza lo siguiente:\n\n" +
                                        "REALIZAR REVISIÓN DE FUNCIONAMIENTO DE CHILLERS Y BOMBAS DE CHILLERS"
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
        val qrLauncher5 = registerForActivityResult(ScanContract()) { result ->
            if (result.contents != null) {
                btnQRR5 = result.contents // Obtenemos el texto escaneado
                if (btnQRR5 == "NS-QR-MTTO-D-060") {//cambiar codigo de 001 A D-016
                    tempSalidaChill.isEnabled = true // Habilitar el campo si el código es correcto
                    spiINY1.isEnabled = true
                    spiINY2.isEnabled = true
                    Toast.makeText(this, "Código del Área: $btnQRR5", Toast.LENGTH_SHORT).show()
                } else {
                    tempSalidaChill.isEnabled = false // Asegurarse de que el campo permanezca deshabilitado
                    Toast.makeText(this, "Error: Código QR incorrecto", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "No se escaneó ningún código QR", Toast.LENGTH_SHORT).show()
            }
        }
        btntempSall.setOnClickListener {
            val options5 = ScanOptions()
            options5.setPrompt("Escanea el código QR")
            options5.setBeepEnabled(true)
            options5.setBarcodeImageEnabled(true)
            qrLauncher5.launch(options5) // Inicia el escaneo
        }

        //iny
        spiINY1.isEnabled = false
        spiINY2.isEnabled = false
        spiINY3.isEnabled = false
        spiINY1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "APAGADO") {
                    // Mostrar advertencia si la opción seleccionada es "No funcionando"
                    val alertDialog = AlertDialog.Builder(this@FormAireAcondi)
                        .setTitle("REVISAR CON CHAVA")
                        .setMessage("")
                        .setPositiveButton("Aceptar") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                    alertDialog.show()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        spiINY2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "APAGADO") {
                    // Mostrar advertencia si la opción seleccionada es "No funcionando"
                    val alertDialog = AlertDialog.Builder(this@FormAireAcondi)
                        .setTitle("REVISAR CON CHAVA")
                        .setMessage("")
                        .setPositiveButton("Aceptar") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                    alertDialog.show()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        spiINY3.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "APAGADO") {
                    // Mostrar advertencia si la opción seleccionada es "No funcionando"
                    val alertDialog = AlertDialog.Builder(this@FormAireAcondi)
                        .setTitle("REVISAR CON CHAVA")
                        .setMessage("")
                        .setPositiveButton("Aceptar") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                    alertDialog.show()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        val qrLauncher6 = registerForActivityResult(ScanContract()) { result ->
            if (result.contents != null) {
                btnQRR6 = result.contents // Obtenemos el texto escaneado
                if (btnQRR6 == "NS-QR-MTTO-D-063") {//cambiar codigo de 001 A D-016
                    spiINY3.isEnabled = true // Habilitar el campo si el código es correcto
                    spiUMA1.isEnabled = true
                    spiUMA2.isEnabled = true
                    spiUMA3.isEnabled = true
                    spiUMA4.isEnabled = true
                    spiUMA5.isEnabled = true
                    spiUMA7.isEnabled = true
                    Toast.makeText(this, "Código del Área: $btnQRR6", Toast.LENGTH_SHORT).show()
                } else {

                    Toast.makeText(this, "Error: Código QR incorrecto", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "No se escaneó ningún código QR", Toast.LENGTH_SHORT).show()
            }
        }
        btnINY3.setOnClickListener {
            val options6 = ScanOptions()
            options6.setPrompt("Escanea el código QR")
            options6.setBeepEnabled(true)
            options6.setBarcodeImageEnabled(true)
            qrLauncher6.launch(options6) // Inicia el escaneo
        }


        spiUMA1.isEnabled = false
        spiUMA2.isEnabled = false
        spiUMA3.isEnabled = false
        spiUMA4.isEnabled = false
        spiUMA5.isEnabled = false
        spiUMA7.isEnabled = false
        //umas 1,2,3,4,5 y 7
        spiUMA1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "APAGADO") {
                    // Mostrar advertencia si la opción seleccionada es "No funcionando"
                    val alertDialog = AlertDialog.Builder(this@FormAireAcondi)
                        .setTitle("REVISAR CON CHAVA")
                        .setMessage("")
                        .setPositiveButton("Aceptar") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                    alertDialog.show()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        spiUMA2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "APAGADO") {
                    // Mostrar advertencia si la opción seleccionada es "No funcionando"
                    val alertDialog = AlertDialog.Builder(this@FormAireAcondi)
                        .setTitle("REVISAR CON CHAVA")
                        .setMessage("")
                        .setPositiveButton("Aceptar") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                    alertDialog.show()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        spiUMA3.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "APAGADO") {
                    // Mostrar advertencia si la opción seleccionada es "No funcionando"
                    val alertDialog = AlertDialog.Builder(this@FormAireAcondi)
                        .setTitle("REVISAR CON CHAVA")
                        .setMessage("")
                        .setPositiveButton("Aceptar") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                    alertDialog.show()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        spiUMA4.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "APAGADO") {
                    // Mostrar advertencia si la opción seleccionada es "No funcionando"
                    val alertDialog = AlertDialog.Builder(this@FormAireAcondi)
                        .setTitle("REVISAR CON CHAVA")
                        .setMessage("")
                        .setPositiveButton("Aceptar") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                    alertDialog.show()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        spiUMA5.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "APAGADO") {
                    // Mostrar advertencia si la opción seleccionada es "No funcionando"
                    val alertDialog = AlertDialog.Builder(this@FormAireAcondi)
                        .setTitle("REVISAR CON CHAVA")
                        .setMessage("")
                        .setPositiveButton("Aceptar") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                    alertDialog.show()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        spiUMA7.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "APAGADO") {
                    // Mostrar advertencia si la opción seleccionada es "No funcionando"
                    val alertDialog = AlertDialog.Builder(this@FormAireAcondi)
                        .setTitle("Avertencia UMA 7")
                        .setMessage("ENCENDER EXTRACTOR")
                        .setPositiveButton("Aceptar") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                    alertDialog.show()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        val qrLauncher7 = registerForActivityResult(ScanContract()) { result ->
            if (result.contents != null) {
                btnQRR7 = result.contents // Obtenemos el texto escaneado
                if (btnQRR7 == "NS-QR-MTTO-D-069") {//cambiar codigo de 001 A D-016
                    spiUMA1.isEnabled = true
                    spiUMA2.isEnabled = true
                    spiUMA4.isEnabled = true
                    spiUMA5.isEnabled = true
                    spiUMA7.isEnabled = true // Habilitar el campo si el código es correcto
                    Toast.makeText(this, "Código del Área: $btnQRR7", Toast.LENGTH_SHORT).show()
                } else {
                    spiUMA1.isEnabled = false
                    spiUMA2.isEnabled = false
                    spiUMA4.isEnabled = false
                    spiUMA5.isEnabled = false
                    spiUMA7.isEnabled = false // Asegurarse de que el campo permanezca deshabilitado
                    Toast.makeText(this, "Error: Código QR incorrecto", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "No se escaneó ningún código QR", Toast.LENGTH_SHORT).show()
            }
        }
        btnUMA7.setOnClickListener {
            val options7 = ScanOptions()
            options7.setPrompt("Escanea el código QR")
            options7.setBeepEnabled(true)
            options7.setBarcodeImageEnabled(true)
            qrLauncher7.launch(options7) // Inicia el escaneo
        }

        //extractor 7
        spiExt7.isEnabled = true
        spiExt7.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "APAGADO") {
                    // Mostrar advertencia si la opción seleccionada es "No funcionando"
                    val alertDialog = AlertDialog.Builder(this@FormAireAcondi)
                        .setTitle("Avertencia Extractor 7")
                        .setMessage("ENCENDER EXTRACTOR")
                        .setPositiveButton("Aceptar") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                    alertDialog.show()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        val qrLauncher8 = registerForActivityResult(ScanContract()) { result ->
            if (result.contents != null) {
                btnQRR8 = result.contents // Obtenemos el texto escaneado
                if (btnQRR8 == "NS-QR-MTTO-D-070") {//cambiar codigo de 001 A D-016
                    spiExt7.isEnabled = true // Habilitar el campo si el código es correcto
                    spiExt16.isEnabled = true
                    spiExt17.isEnabled = true
                    spiExt18.isEnabled = true
                    spiExt19.isEnabled = true
                    Toast.makeText(this, "Código del Área: $btnQRR8", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Error: Código QR incorrecto", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "No se escaneó ningún código QR", Toast.LENGTH_SHORT).show()
            }
        }
        btnExt7.setOnClickListener {
            val options8 = ScanOptions()
            options8.setPrompt("Escanea el código QR")
            options8.setBeepEnabled(true)
            options8.setBarcodeImageEnabled(true)
            qrLauncher8.launch(options8) // Inicia el escaneo
        }


        spiExt16.isEnabled = false
        spiExt17.isEnabled = false
        spiExt18.isEnabled = false
        spiExt19.isEnabled = false
        spiExt8.isEnabled = false
        //ext 16 17 18 19 08
        spiExt16.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "APAGADO") {
                    // Mostrar advertencia si la opción seleccionada es "No funcionando"
                    val alertDialog = AlertDialog.Builder(this@FormAireAcondi)
                        .setTitle("Avertencia Extractor 16")
                        .setMessage("revisar con ing chava")
                        .setPositiveButton("Aceptar") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                    alertDialog.show()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        spiExt17.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "APAGADO") {
                    // Mostrar advertencia si la opción seleccionada es "No funcionando"
                    val alertDialog = AlertDialog.Builder(this@FormAireAcondi)
                        .setTitle("Avertencia Extractor 17")
                        .setMessage("revisar")
                        .setPositiveButton("Aceptar") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                    alertDialog.show()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        spiExt18.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "APAGADO") {
                    // Mostrar advertencia si la opción seleccionada es "No funcionando"
                    val alertDialog = AlertDialog.Builder(this@FormAireAcondi)
                        .setTitle("Avertencia Extractor 18")
                        .setMessage("revisar")
                        .setPositiveButton("Aceptar") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                    alertDialog.show()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        spiExt19.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "APAGADO") {
                    // Mostrar advertencia si la opción seleccionada es "No funcionando"
                    val alertDialog = AlertDialog.Builder(this@FormAireAcondi)
                        .setTitle("Avertencia Extractor 19")
                        .setMessage("ENCENDER EXTRACTOR")
                        .setPositiveButton("Aceptar") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                    alertDialog.show()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        spiExt8.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "APAGADO") {
                    // Mostrar advertencia si la opción seleccionada es "No funcionando"
                    val alertDialog = AlertDialog.Builder(this@FormAireAcondi)
                        .setTitle("Avertencia Extractor 8")
                        .setMessage("ENCENDER EXTRACTOR")
                        .setPositiveButton("Aceptar") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                    alertDialog.show()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        val qrLauncher9 = registerForActivityResult(ScanContract()) { result ->
            if (result.contents != null) {
                btnQRR9 = result.contents // Obtenemos el texto escaneado
                if (btnQRR9 == "NS-QR-MTTO-D-075") {//cambiar codigo de 001 A D-016
                    spiExt9.isEnabled = true
                    spiExt8.isEnabled = true // Habilitar el campo si el código es correcto
                    Toast.makeText(this, "Código del Área: $btnQRR9", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Error: Código QR incorrecto", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "No se escaneó ningún código QR", Toast.LENGTH_SHORT).show()
            }
        }
        btnExt08.setOnClickListener {
            val options9 = ScanOptions()
            options9.setPrompt("Escanea el código QR")
            options9.setBeepEnabled(true)
            options9.setBarcodeImageEnabled(true)
            qrLauncher9.launch(options9) // Inicia el escaneo
        }

        spiExt9.isEnabled = false
        spiExt10.isEnabled = false
        // ext 9 10
        spiExt9.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "APAGADO") {
                    // Mostrar advertencia si la opción seleccionada es "No funcionando"
                    val alertDialog = AlertDialog.Builder(this@FormAireAcondi)
                        .setTitle("Avertencia Extractor 9")
                        .setMessage("")
                        .setPositiveButton("Aceptar") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                    alertDialog.show()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        spiExt10.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "APAGADO") {
                    // Mostrar advertencia si la opción seleccionada es "No funcionando"
                    val alertDialog = AlertDialog.Builder(this@FormAireAcondi)
                        .setTitle("Avertencia Extractor 10")
                        .setMessage("")
                        .setPositiveButton("Aceptar") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                    alertDialog.show()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        val qrLauncher10 = registerForActivityResult(ScanContract()) { result ->
            if (result.contents != null) {
                btnQRR10 = result.contents // Obtenemos el texto escaneado
                if (btnQRR10 == "NS-QR-MTTO-D-077") {//cambiar codigo de 001 A D-016
                    spiExt9.isEnabled = true
                    spiExt10.isEnabled = true // Habilitar el campo si el código es correcto
                    Toast.makeText(this, "Código del Área: $btnQRR10", Toast.LENGTH_SHORT).show()
                } else {
                    spiExt9.isEnabled =false
                    spiExt10.isEnabled = false // Asegurarse de que el campo permanezca deshabilitado
                    Toast.makeText(this, "Error: Código QR incorrecto", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "No se escaneó ningún código QR", Toast.LENGTH_SHORT).show()
            }
        }
        btnExt10.setOnClickListener {
            val options10 = ScanOptions()
            options10.setPrompt("Escanea el código QR")
            options10.setBeepEnabled(true)
            options10.setBarcodeImageEnabled(true)
            qrLauncher10.launch(options10) // Inicia el escaneo
        }

        spiExt11.isEnabled = false
        // ext 11
        spiExt11.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "APAGADO") {
                    // Mostrar advertencia si la opción seleccionada es "No funcionando"
                    val alertDialog = AlertDialog.Builder(this@FormAireAcondi)
                        .setTitle("Avertencia Extractor 11")
                        .setMessage("Encencer extractor")
                        .setPositiveButton("Aceptar") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                    alertDialog.show()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        val qrLauncher11 = registerForActivityResult(ScanContract()) { result ->
            if (result.contents != null) {
                btnQRR11 = result.contents // Obtenemos el texto escaneado
                if (btnQRR11 == "NS-QR-MTTO-D-078") {//cambiar codigo de 001 A D-016
                    spiExt11.isEnabled = true // Habilitar el campo si el código es correcto
                    Toast.makeText(this, "Código del Área: $btnQRR11", Toast.LENGTH_SHORT).show()
                } else {
                    spiExt11.isEnabled = false // Asegurarse de que el campo permanezca deshabilitado
                    Toast.makeText(this, "Error: Código QR incorrecto", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "No se escaneó ningún código QR", Toast.LENGTH_SHORT).show()
            }
        }
        btnExt11.setOnClickListener {
            val options11 = ScanOptions()
            options11.setPrompt("Escanea el código QR")
            options11.setBeepEnabled(true)
            options11.setBarcodeImageEnabled(true)
            qrLauncher11.launch(options11) // Inicia el escaneo
        }

        spiExt12.isEnabled = false
        //ext 12
        spiExt12.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "APAGADO") {
                    // Mostrar advertencia si la opción seleccionada es "No funcionando"
                    val alertDialog = AlertDialog.Builder(this@FormAireAcondi)
                        .setTitle("Avertencia Extractor 12")
                        .setMessage("Encencer extractor")
                        .setPositiveButton("Aceptar") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                    alertDialog.show()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        val qrLauncher12 = registerForActivityResult(ScanContract()) { result ->
            if (result.contents != null) {
                btnQRR12 = result.contents // Obtenemos el texto escaneado
                if (btnQRR12 == "NS-QR-MTTO-D-079") {//cambiar codigo de 001 A D-016
                    spiExt12.isEnabled = true // Habilitar el campo si el código es correcto
                    Toast.makeText(this, "Código del Área: $btnQRR12", Toast.LENGTH_SHORT).show()
                } else {
                    spiExt12.isEnabled = false // Asegurarse de que el campo permanezca deshabilitado
                    Toast.makeText(this, "Error: Código QR incorrecto", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "No se escaneó ningún código QR", Toast.LENGTH_SHORT).show()
            }
        }
        btnExt12.setOnClickListener {
            val options12 = ScanOptions()
            options12.setPrompt("Escanea el código QR")
            options12.setBeepEnabled(true)
            options12.setBarcodeImageEnabled(true)
            qrLauncher12.launch(options12) // Inicia el escaneo
        }

        //ext 20 21
        spiExt20.isEnabled = false
        spiExt21.isEnabled = false
        spiExt20.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "APAGADO") {
                    // Mostrar advertencia si la opción seleccionada es "No funcionando"
                    val alertDialog = AlertDialog.Builder(this@FormAireAcondi)
                        .setTitle("Avertencia Extractor 20")
                        .setMessage("")
                        .setPositiveButton("Aceptar") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                    alertDialog.show()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        spiExt21.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "APAGADO") {
                    // Mostrar advertencia si la opción seleccionada es "No funcionando"
                    val alertDialog = AlertDialog.Builder(this@FormAireAcondi)
                        .setTitle("Avertencia Extractor 21")
                        .setMessage("Encencer extractor")
                        .setPositiveButton("Aceptar") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                    alertDialog.show()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        val qrLauncher13 = registerForActivityResult(ScanContract()) { result ->
            if (result.contents != null) {
                btnQRR13 = result.contents // Obtenemos el texto escaneado
                if (btnQRR13 == "NS-QR-MTTO-D-081") {//cambiar codigo de 001 A D-016
                    spiExt20.isEnabled = true
                    spiExt21.isEnabled = true // Habilitar el campo si el código es correcto
                    spiExt22.isEnabled = true
                    spiExt23.isEnabled = true
                    spiExt24.isEnabled = true
                    Toast.makeText(this, "Código del Área: $btnQRR13", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Error: Código QR incorrecto", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "No se escaneó ningún código QR", Toast.LENGTH_SHORT).show()
            }
        }
        btnExt21.setOnClickListener {
            val options13 = ScanOptions()
            options13.setPrompt("Escanea el código QR")
            options13.setBeepEnabled(true)
            options13.setBarcodeImageEnabled(true)
            qrLauncher13.launch(options13) // Inicia el escaneo
        }

        //ext 22,23,24
        spiExt22.isEnabled = false
        spiExt23.isEnabled = false
        spiExt24.isEnabled = false
        spiExt22.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "APAGADO") {
                    // Mostrar advertencia si la opción seleccionada es "No funcionando"
                    val alertDialog = AlertDialog.Builder(this@FormAireAcondi)
                        .setTitle("Avertencia Extractor 22")
                        .setMessage("")
                        .setPositiveButton("Aceptar") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                    alertDialog.show()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        spiExt23.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "APAGADO") {
                    // Mostrar advertencia si la opción seleccionada es "No funcionando"
                    val alertDialog = AlertDialog.Builder(this@FormAireAcondi)
                        .setTitle("Avertencia Extractor 23")
                        .setMessage("")
                        .setPositiveButton("Aceptar") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                    alertDialog.show()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        spiExt24.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "APAGADO") {
                    // Mostrar advertencia si la opción seleccionada es "No funcionando"
                    val alertDialog = AlertDialog.Builder(this@FormAireAcondi)
                        .setTitle("Avertencia Extractor 24")
                        .setMessage("")
                        .setPositiveButton("Aceptar") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                    alertDialog.show()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        etporgaslpcalderas.isEnabled = false
        //et porcentajes
        etporgaslpcalderas.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val value = et.text.toString().toDoubleOrNull()
                if (value != null) {
                    if (value < 50) {
                        val alertDialog = AlertDialog.Builder(this@FormAireAcondi)
                            .setTitle("Advertencia Porcentaje gas lp calderas")
                            .setMessage(
                                "El porcentaje de gas lp calderas esta por fuera del rango Realiza lo siguiente:\n\n" +
                                        "REALIZAR REVISIÓN DE FUGAS Y REALIZAR RECARGA DE TANQUE"
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
        val qrLauncher14 = registerForActivityResult(ScanContract()) { result ->
            if (result.contents != null) {
                btnQRR14 = result.contents // Obtenemos el texto escaneado
                if (btnQRR14 == "NS-QR-MTTO-D-085") {//cambiar codigo de 001 A D-016
                    etporgaslpcalderas.isEnabled = true // Habilitar el campo si el código es correcto
                    Toast.makeText(this, "Código del Área: $btnQRR14", Toast.LENGTH_SHORT).show()
                } else {
                    etporgaslpcalderas.isEnabled = false // Asegurarse de que el campo permanezca deshabilitado
                    Toast.makeText(this, "Error: Código QR incorrecto", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "No se escaneó ningún código QR", Toast.LENGTH_SHORT).show()
            }
        }
        btnporgaslpcalderas.setOnClickListener {
            val options14 = ScanOptions()
            options14.setPrompt("Escanea el código QR")
            options14.setBeepEnabled(true)
            options14.setBarcodeImageEnabled(true)
            qrLauncher14.launch(options14) // Inicia el escaneo
        }

        etporgaslpcocina.isEnabled = false
        etporgaslpcocina.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val value = et.text.toString().toDoubleOrNull()
                if (value != null) {
                    if (value < 50) {
                        val alertDialog = AlertDialog.Builder(this@FormAireAcondi)
                            .setTitle("Advertencia Porcentaje gas lp cocina")
                            .setMessage(
                                "El porcentaje de gas lp cocina esta por fuera del rango Realiza lo siguiente:\n\n" +
                                        "REALIZAR REVISIÓN DE FUGAS Y REALIZAR RECARGA DE TANQUE"
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
        val qrLauncher15 = registerForActivityResult(ScanContract()) { result ->
            if (result.contents != null) {
                btnQRR15 = result.contents // Obtenemos el texto escaneado
                if (btnQRR15 == "NS-QR-MTTO-D-086") {//cambiar codigo de 001 A D-016
                    etporgaslpcocina.isEnabled = true // Habilitar el campo si el código es correcto
                    Toast.makeText(this, "Código del Área: $btnQRR15", Toast.LENGTH_SHORT).show()
                } else {
                    etporgaslpcocina.isEnabled = false // Asegurarse de que el campo permanezca deshabilitado
                    Toast.makeText(this, "Error: Código QR incorrecto", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "No se escaneó ningún código QR", Toast.LENGTH_SHORT).show()
            }
        }
        btnporgaslpcocina.setOnClickListener {
            val options15 = ScanOptions()
            options15.setPrompt("Escanea el código QR")
            options15.setBeepEnabled(true)
            options15.setBarcodeImageEnabled(true)
            qrLauncher15.launch(options15) // Inicia el escaneo
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

        continar.setOnClickListener {
            val datosFormularioAA = listOf(
                et.text.toString(),
                spiChill1.selectedItem.toString(),
                spiChill2.selectedItem.toString(),
                spiChill3.selectedItem.toString(),
                spiChiller1.selectedItem.toString(),
                spiChiller2.selectedItem.toString(),
                spiChiller3.selectedItem.toString(),
                spiChill4.selectedItem.toString(),
                spiChill5.selectedItem.toString(),
                tempSalidaChill.text.toString(),
                spiINY1.selectedItem.toString(),
                spiINY2.selectedItem.toString(),
                spiINY3.selectedItem.toString(),
                spiUMA1.selectedItem.toString(),
                spiUMA2.selectedItem.toString(),
                spiUMA3.selectedItem.toString(),
                spiUMA4.selectedItem.toString(),
                spiUMA5.selectedItem.toString(),
                spiUMA7.selectedItem.toString(),
                spiExt7.selectedItem.toString(),
                spiExt16.selectedItem.toString(),
                spiExt17.selectedItem.toString(),
                spiExt18.selectedItem.toString(),
                spiExt19.selectedItem.toString(),
                spiExt8.selectedItem.toString(),
                spiExt9.selectedItem.toString(),
                spiExt10.selectedItem.toString(),
                spiExt11.selectedItem.toString(),
                spiExt12.selectedItem.toString(),
                spiExt20.selectedItem.toString(),
                spiExt21.selectedItem.toString(),
                spiExt22.selectedItem.toString(),
                spiExt23.selectedItem.toString(),
                spiExt24.selectedItem.toString(),
                etporgaslpcalderas.text.toString(),
                etporgaslpcocina.text.toString(),
                etHallazgosAireAcondi.text.toString(),
                SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault()).format(Date())
            )

            manejarExcel(
                context = this, fileName = "RecorridoMantenimiento.xlsx",
                sheetName = "RecorridoAireAcondicionado", datos = datosFormularioAA,
                headers = listOf(
                    "Presión de bombas de chiller (PSI)",
                    "BOMBA CHILLER 1",
                    "BOMBA CHILLER 2",
                    "BOMBA CHILLER 3",
                    "CHILLER 1",
                    "CHILLER 2",
                    "CHILLER 3",
                    "CHILLER 4",
                    "CHILLER 5",
                    "Temp. de salida Chillers (Analogica) (°C)",
                    "INY 1",
                    "INY 2",
                    "INY 3",
                    "UMA 1 (QUIROFANO 1)",
                    "UMA 2 (QUIROFANO 2)",
                    "UMA 3 (QUIROFANO 3)",
                    "UMA 4 (PASILLO ÁREA BLANCA 1)",
                    "UMA 5 (PASILLO ÁREA BLANCA 2)",
                    "UMA 7 (CEYE)",
                    "EXTRACTOR 07 (COCINA)",
                    "EXTRACTOR 16 (QUIROFANO 1)",
                    "EXTRACTOR 17 (QUIROFANO 2)",
                    "EXTRACTOR 18 (QUIROFANO 3)",
                    "EXTRACTOR 19 (PASILLO ÁREA BLANCA)",
                    "EXTRACTOR 08 (URGENCIAS, SÉPTICOS 1N Y 2N)",
                    "EXTRACTOR 09 (ALMACÉN PB, 2N)",
                    "EXTRACTOR 10 (BAÑOS PB, 1N, 2N, 3N)",
                    "EXTRACTOR 11 (ALMACÉN DE RPBI)",
                    "EXTRACTOR 12 (BAÑOS 1N Y 2N)",
                    "EXTRACTOR 20 (BAÑOS 4N)",
                    "EXTRACTOR 21 (CEYE)",
                    "EXTRACTOR 22 (RPBI 4N)",
                    "EXTRACTOR 23 (TRANSFER)",
                    "EXTRACTOR 24 (VESTIDORES)",
                    "Porcentaje de Gas LP Calderas (%)",
                    "Porcentaje de Gas LP Cocina (%)",
                    "Hallazgos",
                    "Fecha y hora"
                )
            )

            // 🔹 Ahora actualizamos el estado a "completed"
            val sharedPreferences = getSharedPreferences("StatusPref", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("statusAA", "completed")  // 🔹 Usa la clave correspondiente a este formulario
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