package com.example.proyectnewro

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

class FormEletrico : AppCompatActivity() {
    private lateinit var spPlantaEmergencia : Spinner
    private lateinit var spiTab_gn2 : Spinner
    private lateinit var spiTab_gn1 : Spinner
    private lateinit var spiTab_gsv : Spinner
    private lateinit var spiTab_gcr : Spinner
    private lateinit var spiTab_gse : Spinner
    private lateinit var spiTab_psn : Spinner
    private lateinit var spiTab_ps_sv : Spinner
    private lateinit var spiTab_pb_r : Spinner
    private lateinit var spiTab_pb_sv : Spinner
    private lateinit var spiTab_pb_cr : Spinner
    private lateinit var spiTab_pbn : Spinner
    private lateinit var spiTab_cc_p1_r : Spinner
    private lateinit var spiTab_p1_sv : Spinner
    private lateinit var spiTab_p1n : Spinner
    private lateinit var spiTab_p2_r : Spinner
    private lateinit var spiTab_p2_sv : Spinner
    private lateinit var spiTab_p2_cr : Spinner
    private lateinit var spiTab_p2n : Spinner
    private lateinit var spiTab_p3_r : Spinner
    private lateinit var spiTab_p3_sv : Spinner
    private lateinit var spiTab_p3_cr : Spinner
    private lateinit var spiTab_p3_n1 : Spinner
    private lateinit var spiTab_p3_n2 : Spinner
    private lateinit var spiTab_p4_r : Spinner
    private lateinit var spiTab_p4_sv : Spinner
    private lateinit var spiTab_p4_cr : Spinner
    private lateinit var spiTab_cc_p4n : Spinner
    private lateinit var spiTab_pan3 : Spinner
    private lateinit var spiTab_p4n : Spinner
    private lateinit var spiTab_pan2 : Spinner
    private lateinit var spiTab_pan1 : Spinner
    private lateinit var spiTab_pa_se : Spinner

    @Suppress("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_form_eletrico)

        spPlantaEmergencia = findViewById(R.id.spPlanta_emerg)
        val btnPlanta_emerg = findViewById<Button>(R.id.btPlanta_emerg)
        val FallasEncon = findViewById<EditText>(R.id.etFallas_elec)
        spiTab_gn2 = findViewById(R.id.spTab_gn2)
        val btnTab_gn2 = findViewById<Button>(R.id.btspTab_gn2)
        spiTab_gn1 =findViewById(R.id.spTab_gn1)
        val btnTab_gn1 = findViewById<Button>(R.id.btTab_gn1)
        spiTab_gsv = findViewById(R.id.spTab_gsv)
        spiTab_gcr = findViewById(R.id.spTab_gcr)
        val bttTab_gcr = findViewById<Button>(R.id.btTab_gcr)
        spiTab_gse = findViewById(R.id.spTab_gse)
        spiTab_psn = findViewById(R.id.spTab_psn)
        spiTab_ps_sv = findViewById(R.id.spTab_ps_sv)
        val btnTab_ps_sv = findViewById<Button>(R.id.btTab_ps_sv)
        spiTab_pb_r = findViewById(R.id.spTab_pb_r)
        spiTab_pb_sv = findViewById(R.id.spTab_pb_sv)
        spiTab_pb_cr = findViewById(R.id.spTab_pb_cr)
        val btnTab_pb_cr = findViewById<Button>(R.id.btTab_pb_cr)
        spiTab_pbn = findViewById(R.id.spTab_pbn)
        spiTab_cc_p1_r = findViewById(R.id.spTab_cc_p1_r)
        spiTab_p1_sv = findViewById(R.id.spTab_p1_sv)
        val btnTab_p1_sv = findViewById<Button>(R.id.btTab_p1_sv)
        spiTab_p1n = findViewById(R.id.spTab_p1n)
        spiTab_p2_r = findViewById(R.id.spTab_p2_r)
        spiTab_p2_sv = findViewById(R.id.spTab_p2_sv)
        spiTab_p2_cr = findViewById(R.id.spTab_p2_cr)
        val btnTab_p2_cr = findViewById<Button>(R.id.btTab_p2_cr)
        spiTab_p2n = findViewById(R.id.spTab_p2n)
        spiTab_p3_r = findViewById(R.id.spTab_p3_r)
        spiTab_p3_sv = findViewById(R.id.spTab_p3_sv)
        spiTab_p3_cr = findViewById(R.id.spTab_p3_cr)
        val btnTab_p3_cr = findViewById<Button>(R.id.btTab_p3_cr)
        spiTab_p3_n1 = findViewById(R.id.spTab_p3_n1)
        spiTab_p3_n2 = findViewById(R.id.spTab_p3_n2)
        spiTab_p4_r = findViewById(R.id.spTab_p4_r)
        spiTab_p4_sv = findViewById(R.id.spTab_p4_sv)
        spiTab_p4_cr = findViewById(R.id.spTab_p4_cr)
        val btnTab_p4_cr = findViewById<Button>(R.id.btTab_p4_cr)
        spiTab_cc_p4n = findViewById(R.id.spTab_cc_p4n)
        spiTab_p4n = findViewById(R.id.spTab_p4n)
        spiTab_pan3 = findViewById(R.id.spTab_pan3)
        spiTab_pan2 = findViewById(R.id.spTab_pan2)
        spiTab_pan1 = findViewById(R.id.spTab_pan1)
        spiTab_pa_se = findViewById(R.id.spTab_pa_se)
        val btnTan_pa_se = findViewById<Button>(R.id.btTan_pa_se)
        val etHallazgosEletrico = findViewById<EditText>(R.id.etHaEle)

        val btnSend2 = findViewById<Button>(R.id.continuar)

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
        var qrCodeText11: String?

        val opciones = resources.getStringArray(R.array.opciones_bomba)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, opciones)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//campo planta de emergencia ostia tio
        spPlantaEmergencia.isEnabled = false
        FallasEncon.isEnabled = false
        //validaciones spinner planta de emergencia
        spPlantaEmergencia.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,view: View?,position: Int,id: Long) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "NO") {
                    val alertDialog = AlertDialog.Builder(this@FormEletrico)
                        .setTitle("Advertencia planta de emergencia")
                        .setMessage("COLOCAR PLANTA DE EMERGENCIA EN MODO AUTOMATICO")
                        .setPositiveButton("Aceptar") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                    alertDialog.show()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        //ejecutar launcher
        val qrlauncher1 = registerForActivityResult(ScanContract()){result ->
            if(result.contents != null){
                qrCodeText1 = result.contents
                if(qrCodeText1 == "NS-QR-MTTO-D-021"){
                    spPlantaEmergencia.isEnabled = true
                    FallasEncon.isEnabled = true
                    Toast.makeText(this, "Codigo del Area: $qrCodeText1", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "Error: Código QR incorrecto", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "No se escaneó ningún código QR", Toast.LENGTH_SHORT).show()
            }
        }
        //iniciar QR con boton
        btnPlanta_emerg.setOnClickListener {
            val options1 = ScanOptions()
            options1.setPrompt("Escanea el codigo QR")
            options1.setBeepEnabled(true)
            options1.setBarcodeImageEnabled(true)
            qrlauncher1.launch(options1)
        }

//campo tab gn2
        spiTab_gn2.isEnabled = false
        //validaciones spinner planta de emergencia
        spiTab_gn2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,view: View?,position: Int,id: Long) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "NO") {
                    val alertDialog = AlertDialog.Builder(this@FormEletrico)
                        .setTitle("Advertencia Tab_gn2")
                        .setMessage("RESTAURAR INTERRUPTORES Y REGISTRARLO EN COMENTARIOS")
                        .setPositiveButton("Aceptar") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                    alertDialog.show()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        //ejecutar launcher
        val qrlauncher2 = registerForActivityResult(ScanContract()){result ->
            if(result.contents != null){
                qrCodeText2 = result.contents
                if(qrCodeText2 == "NS-QR-MTTO-D-022"){
                    spiTab_gn2.isEnabled = true
                    Toast.makeText(this, "Codigo del Area: $qrCodeText2", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "Error: Código QR incorrecto", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "No se escaneó ningún código QR", Toast.LENGTH_SHORT).show()
            }
        }
        //iniciar QR con boton
        btnTab_gn2.setOnClickListener {
            val options2 = ScanOptions()
            options2.setPrompt("Escanea el codigo QR")
            options2.setBeepEnabled(true)
            options2.setBarcodeImageEnabled(true)
            qrlauncher2.launch(options2)
        }


//campo tab gn1 y Tab gsv
        spiTab_gn1.isEnabled = false
        spiTab_gsv.isEnabled = false
        //validaciones spinner planta de emergencia
        spiTab_gn1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,view: View?,position: Int,id: Long) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "NO") {
                    val alertDialog = AlertDialog.Builder(this@FormEletrico)
                        .setTitle("Advertencia Tab_gn1")
                        .setMessage("RESTAURAR INTERRUPTORES Y REGISTRARLO EN COMENTARIOS")
                        .setPositiveButton("Aceptar") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                    alertDialog.show()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        //ejecutar launcher
        val qrlauncher3 = registerForActivityResult(ScanContract()){result ->
            if(result.contents != null){
                qrCodeText3 = result.contents
                if(qrCodeText3 == "NS-QR-MTTO-D-023"){
                    spiTab_gsv.isEnabled = true
                    spiTab_gn1.isEnabled = true
                    Toast.makeText(this, "Codigo del Area: $qrCodeText3", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "Error: Código QR incorrecto", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "No se escaneó ningún código QR", Toast.LENGTH_SHORT).show()
            }
        }
        //iniciar QR con boton
        btnTab_gn1.setOnClickListener {
            val options3 = ScanOptions()
            options3.setPrompt("Escanea el codigo QR")
            options3.setBeepEnabled(true)
            options3.setBarcodeImageEnabled(true)
            qrlauncher3.launch(options3)
        }

        spiTab_gsv.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,view: View?,position: Int,id: Long) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "NO") {
                    val alertDialog = AlertDialog.Builder(this@FormEletrico)
                        .setTitle("Advertencia Tab_gsv")
                        .setMessage("RESTAURAR INTERRUPTORES Y REGISTRARLO EN COMENTARIOS")
                        .setPositiveButton("Aceptar") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                    alertDialog.show()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

//campo Tab gcr Tab gse y Tab psn
        spiTab_gcr.isEnabled = false
        spiTab_gse.isEnabled = false
        spiTab_psn.isEnabled = false
        val qrlauncher4 = registerForActivityResult(ScanContract()){result ->
            if(result.contents != null){
                qrCodeText4 = result.contents
                if(qrCodeText4 == "NS-QR-MTTO-D-025"){
                    spiTab_gcr.isEnabled = true
                    spiTab_gse.isEnabled = true
                    spiTab_psn.isEnabled = true
                    Toast.makeText(this, "Codigo del Area: $qrCodeText4", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "Error: Código QR incorrecto", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "No se escaneó ningún código QR", Toast.LENGTH_SHORT).show()
            }
        }
        //iniciar QR con boton
        bttTab_gcr.setOnClickListener {
            val options4 = ScanOptions()
            options4.setPrompt("Escanea el codigo QR")
            options4.setBeepEnabled(true)
            options4.setBarcodeImageEnabled(true)
            qrlauncher4.launch(options4)
        }

        spiTab_gcr.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,view: View?,position: Int,id: Long) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "NO") {
                    val alertDialog = AlertDialog.Builder(this@FormEletrico)
                        .setTitle("Advertencia Tab_gcr")
                        .setMessage("RESTAURAR INTERRUPTORES Y REGISTRARLO EN COMENTARIOS")
                        .setPositiveButton("Aceptar") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                    alertDialog.show()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        spiTab_gse.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,view: View?,position: Int,id: Long) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "NO") {
                    val alertDialog = AlertDialog.Builder(this@FormEletrico)
                        .setTitle("Advertencia Tab_gse")
                        .setMessage("RESTAURAR INTERRUPTORES Y REGISTRARLO EN COMENTARIOS")
                        .setPositiveButton("Aceptar") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                    alertDialog.show()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        spiTab_psn.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,view: View?,position: Int,id: Long) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "NO") {
                    val alertDialog = AlertDialog.Builder(this@FormEletrico)
                        .setTitle("Advertencia Tab_psn")
                        .setMessage("RESTAURAR INTERRUPTORES Y REGISTRARLO EN COMENTARIOS")
                        .setPositiveButton("Aceptar") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                    alertDialog.show()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

//campo tab ps sv , tab pb r, tam pb sv
        spiTab_ps_sv.isEnabled = false
        spiTab_pb_r.isEnabled = false
        spiTab_pb_sv.isEnabled =  false
        val qrlauncher5 = registerForActivityResult(ScanContract()){result ->
            if(result.contents != null){
                qrCodeText5 = result.contents
                if(qrCodeText5 == "NS-QR-MTTO-D-028"){
                    spiTab_ps_sv.isEnabled = true
                    spiTab_pb_r.isEnabled = true
                    spiTab_pb_sv.isEnabled =  true
                    Toast.makeText(this, "Codigo del Area: $qrCodeText5", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "Error: Código QR incorrecto", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "No se escaneó ningún código QR", Toast.LENGTH_SHORT).show()
            }
        }
        //iniciar QR con boton
        btnTab_ps_sv.setOnClickListener {
            val options5 = ScanOptions()
            options5.setPrompt("Escanea el codigo QR")
            options5.setBeepEnabled(true)
            options5.setBarcodeImageEnabled(true)
            qrlauncher5.launch(options5)
        }

        spiTab_ps_sv.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,view: View?,position: Int,id: Long) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "NO") {
                    val alertDialog = AlertDialog.Builder(this@FormEletrico)
                        .setTitle("Advertencia Tab_ps_sv")
                        .setMessage("RESTAURAR INTERRUPTORES Y REGISTRARLO EN COMENTARIOS")
                        .setPositiveButton("Aceptar") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                    alertDialog.show()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        spiTab_pb_r.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,view: View?,position: Int,id: Long) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "NO") {
                    val alertDialog = AlertDialog.Builder(this@FormEletrico)
                        .setTitle("Advertencia Tab_pb_r")
                        .setMessage("RESTAURAR INTERRUPTORES Y REGISTRARLO EN COMENTARIOS")
                        .setPositiveButton("Aceptar") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                    alertDialog.show()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        spiTab_pb_sv.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,view: View?,position: Int,id: Long) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "NO") {
                    val alertDialog = AlertDialog.Builder(this@FormEletrico)
                        .setTitle("Advertencia Tab_pb_sv")
                        .setMessage("RESTAURAR INTERRUPTORES Y REGISTRARLO EN COMENTARIOS")
                        .setPositiveButton("Aceptar") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                    alertDialog.show()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

//campo Tab_pb_cr , Tab_pbn , Tab_cc_p1_r
        spiTab_pb_cr.isEnabled = false
        spiTab_pbn.isEnabled = false
        spiTab_cc_p1_r.isEnabled = false
        val qrlauncher6 = registerForActivityResult(ScanContract()){result ->
            if(result.contents != null){
                qrCodeText6 = result.contents
                if(qrCodeText6 == "NS-QR-MTTO-D-031"){
                    spiTab_pb_cr.isEnabled = true
                    spiTab_pbn.isEnabled = true
                    spiTab_cc_p1_r.isEnabled = true
                    Toast.makeText(this, "Codigo del Area: $qrCodeText6", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "Error: Código QR incorrecto", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "No se escaneó ningún código QR", Toast.LENGTH_SHORT).show()
            }
        }
        //iniciar QR con boton
        btnTab_pb_cr.setOnClickListener {
            val options6 = ScanOptions()
            options6.setPrompt("Escanea el codigo QR")
            options6.setBeepEnabled(true)
            options6.setBarcodeImageEnabled(true)
            qrlauncher6.launch(options6)
        }

        spiTab_pb_cr.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,view: View?,position: Int,id: Long) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "NO") {
                    val alertDialog = AlertDialog.Builder(this@FormEletrico)
                        .setTitle("Advertencia Tab_cc_p1_r")
                        .setMessage("RESTAURAR INTERRUPTORES Y REGISTRARLO EN COMENTARIOS")
                        .setPositiveButton("Aceptar") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                    alertDialog.show()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        spiTab_pbn.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,view: View?,position: Int,id: Long) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "NO") {
                    val alertDialog = AlertDialog.Builder(this@FormEletrico)
                        .setTitle("Advertencia Tab_pbn")
                        .setMessage("RESTAURAR INTERRUPTORES Y REGISTRARLO EN COMENTARIOS")
                        .setPositiveButton("Aceptar") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                    alertDialog.show()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        spiTab_cc_p1_r.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,view: View?,position: Int,id: Long) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "NO") {
                    val alertDialog = AlertDialog.Builder(this@FormEletrico)
                        .setTitle("Advertencia Tab_cc_p1_r")
                        .setMessage("RESTAURAR INTERRUPTORES Y REGISTRARLO EN COMENTARIOS")
                        .setPositiveButton("Aceptar") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                    alertDialog.show()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

//campo Tab p1 sv,  Tab p1n, Tab p2 r y tab p2 sv
        spiTab_p1_sv.isEnabled = false
        spiTab_p1n.isEnabled = false
        spiTab_p2_r.isEnabled = false
        spiTab_p2_sv.isEnabled = false
        val qrlauncher7 = registerForActivityResult(ScanContract()){result ->
            if(result.contents != null){
                qrCodeText7 = result.contents
                if(qrCodeText7 == "NS-QR-MTTO-D-034"){
                    spiTab_p1_sv.isEnabled = true
                    spiTab_p1n.isEnabled = true
                    spiTab_p2_r.isEnabled = true
                    spiTab_p2_sv.isEnabled = true
                    Toast.makeText(this, "Codigo del Area: $qrCodeText7", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "Error: Código QR incorrecto", Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this, "No se escaneó ningún código QR", Toast.LENGTH_SHORT).show()
            }
        }
        //iniciar lector con boton
        btnTab_p1_sv.setOnClickListener {
            val options7 = ScanOptions()
            options7.setPrompt("Escanea el codigo QR")
            options7.setBeepEnabled(true)
            options7.setBarcodeImageEnabled(true)
            qrlauncher7.launch(options7)
        }

        spiTab_p1_sv.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,view: View?,position: Int,id: Long) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "NO") {
                    val alertDialog = AlertDialog.Builder(this@FormEletrico)
                        .setTitle("Advertencia Tab_p1_sv")
                        .setMessage("RESTAURAR INTERRUPTORES Y REGISTRARLO EN COMENTARIOS")
                        .setPositiveButton("Aceptar") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                    alertDialog.show()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        spiTab_p1n.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,view: View?,position: Int,id: Long) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "NO") {
                    val alertDialog = AlertDialog.Builder(this@FormEletrico)
                        .setTitle("Advertencia Tab_p1n")
                        .setMessage("RESTAURAR INTERRUPTORES Y REGISTRARLO EN COMENTARIOS")
                        .setPositiveButton("Aceptar") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                    alertDialog.show()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        spiTab_p2_r.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,view: View?,position: Int,id: Long) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "NO") {
                    val alertDialog = AlertDialog.Builder(this@FormEletrico)
                        .setTitle("Advertencia Tab_p2_r")
                        .setMessage("RESTAURAR INTERRUPTORES Y REGISTRARLO EN COMENTARIOS")
                        .setPositiveButton("Aceptar") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                    alertDialog.show()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        spiTab_p2_sv.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,view: View?,position: Int,id: Long) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "NO") {
                    val alertDialog = AlertDialog.Builder(this@FormEletrico)
                        .setTitle("Advertencia Tab_p2_sv")
                        .setMessage("RESTAURAR INTERRUPTORES Y REGISTRARLO EN COMENTARIOS")
                        .setPositiveButton("Aceptar") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                    alertDialog.show()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

//campo tab p2 cr, tab p2n, tab p3 r, tab p3 sv
        spiTab_p2_cr.isEnabled = false
        spiTab_p2n.isEnabled = false
        spiTab_p3_r.isEnabled = false
        spiTab_p3_sv.isEnabled = false
        val qrlauncher8 = registerForActivityResult(ScanContract()){result ->
            if(result.contents != null){
                qrCodeText8 = result.contents
                if(qrCodeText8 == "NS-QR-MTTO-D-038"){
                    spiTab_p2_cr.isEnabled = true
                    spiTab_p2n.isEnabled = true
                    spiTab_p3_r.isEnabled = true
                    spiTab_p3_sv.isEnabled = true
                    Toast.makeText(this, "Codigo del Area: $qrCodeText8", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "Error: Código QR incorrecto", Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this, "No se escaneó ningún código QR", Toast.LENGTH_SHORT).show()
            }
        }
        //iniciar lector con boton
        btnTab_p2_cr.setOnClickListener {
            val options8 = ScanOptions()
            options8.setPrompt("Escanea el codigo QR")
            options8.setBeepEnabled(true)
            options8.setBarcodeImageEnabled(true)
            qrlauncher8.launch(options8)
        }

        spiTab_p2_cr.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,view: View?,position: Int,id: Long) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "NO") {
                    val alertDialog = AlertDialog.Builder(this@FormEletrico)
                        .setTitle("Advertencia Tab_p2_cr")
                        .setMessage("RESTAURAR INTERRUPTORES Y REGISTRARLO EN COMENTARIOS")
                        .setPositiveButton("Aceptar") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                    alertDialog.show()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        spiTab_p2n.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,view: View?,position: Int,id: Long) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "NO") {
                    val alertDialog = AlertDialog.Builder(this@FormEletrico)
                        .setTitle("Advertencia Tab_p2n")
                        .setMessage("RESTAURAR INTERRUPTORES Y REGISTRARLO EN COMENTARIOS")
                        .setPositiveButton("Aceptar") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                    alertDialog.show()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        spiTab_p3_r.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,view: View?,position: Int,id: Long) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "NO") {
                    val alertDialog = AlertDialog.Builder(this@FormEletrico)
                        .setTitle("Advertencia Tab_p3_r")
                        .setMessage("RESTAURAR INTERRUPTORES Y REGISTRARLO EN COMENTARIOS")
                        .setPositiveButton("Aceptar") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                    alertDialog.show()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        spiTab_p3_sv.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,view: View?,position: Int,id: Long) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "NO") {
                    val alertDialog = AlertDialog.Builder(this@FormEletrico)
                        .setTitle("Advertencia Tab_p3_sv")
                        .setMessage("RESTAURAR INTERRUPTORES Y REGISTRARLO EN COMENTARIOS")
                        .setPositiveButton("Aceptar") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                    alertDialog.show()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }


// campo tab p3 cr, tab p3n1, tab p3n2, tab p4 r, tab p4 sv
        spiTab_p3_cr.isEnabled = false
        spiTab_p3_n1.isEnabled = false
        spiTab_p3_n2.isEnabled = false
        spiTab_p4_r.isEnabled = false
        spiTab_p4_sv.isEnabled = false
        val qrlauncher9 = registerForActivityResult(ScanContract()){result ->
            if(result.contents != null){
                qrCodeText9 = result.contents
                if(qrCodeText9 == "NS-QR-MTTO-D-042"){
                    spiTab_p3_cr.isEnabled = true
                    spiTab_p3_n1.isEnabled = true
                    spiTab_p3_n2.isEnabled = true
                    spiTab_p4_r.isEnabled = true
                    spiTab_p4_sv.isEnabled = true
                    Toast.makeText(this, "Codigo del Area: $qrCodeText9", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "Error: Código QR incorrecto", Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this, "No se escaneó ningún código QR", Toast.LENGTH_SHORT).show()
            }
        }
        //iniciar lector con boton
        btnTab_p3_cr.setOnClickListener {
            val options9 = ScanOptions()
            options9.setPrompt("Escanea el codigo QR")
            options9.setBeepEnabled(true)
            options9.setBarcodeImageEnabled(true)
            qrlauncher9.launch(options9)
        }

        spiTab_p3_cr.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,view: View?,position: Int,id: Long) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "NO") {
                    val alertDialog = AlertDialog.Builder(this@FormEletrico)
                        .setTitle("Advertencia Tab_p3_cr")
                        .setMessage("RESTAURAR INTERRUPTORES Y REGISTRARLO EN COMENTARIOS")
                        .setPositiveButton("Aceptar") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                    alertDialog.show()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        spiTab_p3_n1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,view: View?,position: Int,id: Long) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "NO") {
                    val alertDialog = AlertDialog.Builder(this@FormEletrico)
                        .setTitle("Advertencia Tab_p3_n1")
                        .setMessage("RESTAURAR INTERRUPTORES Y REGISTRARLO EN COMENTARIOS")
                        .setPositiveButton("Aceptar") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                    alertDialog.show()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        spiTab_p3_n2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,view: View?,position: Int,id: Long) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "NO") {
                    val alertDialog = AlertDialog.Builder(this@FormEletrico)
                        .setTitle("Advertencia Tab_p3_n2")
                        .setMessage("RESTAURAR INTERRUPTORES Y REGISTRARLO EN COMENTARIOS")
                        .setPositiveButton("Aceptar") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                    alertDialog.show()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        spiTab_p4_r.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,view: View?,position: Int,id: Long) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "NO") {
                    val alertDialog = AlertDialog.Builder(this@FormEletrico)
                        .setTitle("Advertencia Tab_p4_r")
                        .setMessage("RESTAURAR INTERRUPTORES Y REGISTRARLO EN COMENTARIOS")
                        .setPositiveButton("Aceptar") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                    alertDialog.show()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        spiTab_p4_sv.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,view: View?,position: Int,id: Long) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "NO") {
                    val alertDialog = AlertDialog.Builder(this@FormEletrico)
                        .setTitle("Advertencia Tab_p4_sv")
                        .setMessage("RESTAURAR INTERRUPTORES Y REGISTRARLO EN COMENTARIOS")
                        .setPositiveButton("Aceptar") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                    alertDialog.show()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

//campo tab p4 cr, cc p4n, tab pan3, tab pan 2, tab pan1

        spiTab_p4_cr.isEnabled = false
        spiTab_cc_p4n.isEnabled = false
        spiTab_p4n.isEnabled = false
        spiTab_pan3.isEnabled = false
        spiTab_pan2.isEnabled = false
        spiTab_pan1.isEnabled = false
        val qrlauncher10 = registerForActivityResult(ScanContract()){result ->
            if(result.contents != null){
                qrCodeText10 = result.contents
                if(qrCodeText10 == "NS-QR-MTTO-D-047"){
                    spiTab_p4_cr.isEnabled = true
                    spiTab_cc_p4n.isEnabled = true
                    spiTab_p4n.isEnabled = true
                    spiTab_pan3.isEnabled = true
                    spiTab_pan2.isEnabled = true
                    spiTab_pan1.isEnabled = true
                    Toast.makeText(this, "Codigo del Area: $qrCodeText10", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "Error: Código QR incorrecto", Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this, "No se escaneó ningún código QR", Toast.LENGTH_SHORT).show()
            }
        }
        //iniciar lector con boton
        btnTab_p4_cr.setOnClickListener {
            val options10 = ScanOptions()
            options10.setPrompt("Escanea el codigo QR")
            options10.setBeepEnabled(true)
            options10.setBarcodeImageEnabled(true)
            qrlauncher10.launch(options10)
        }

        spiTab_p4_cr.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,view: View?,position: Int,id: Long) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "NO") {
                    val alertDialog = AlertDialog.Builder(this@FormEletrico)
                        .setTitle("Advertencia Tab_p4_cr")
                        .setMessage("RESTAURAR INTERRUPTORES Y REGISTRARLO EN COMENTARIOS")
                        .setPositiveButton("Aceptar") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                    alertDialog.show()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        spiTab_cc_p4n.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,view: View?,position: Int,id: Long) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "NO") {
                    val alertDialog = AlertDialog.Builder(this@FormEletrico)
                        .setTitle("Advertencia Tab_cc_p4n")
                        .setMessage("RESTAURAR INTERRUPTORES Y REGISTRARLO EN COMENTARIOS")
                        .setPositiveButton("Aceptar") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                    alertDialog.show()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        spiTab_p4n.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,view: View?,position: Int,id: Long) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "NO") {
                    val alertDialog = AlertDialog.Builder(this@FormEletrico)
                        .setTitle("Advertencia Tab_p4n")
                        .setMessage("RESTAURAR INTERRUPTORES Y REGISTRARLO EN COMENTARIOS")
                        .setPositiveButton("Aceptar") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                    alertDialog.show()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        spiTab_pan3.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,view: View?,position: Int,id: Long) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "NO") {
                    val alertDialog = AlertDialog.Builder(this@FormEletrico)
                        .setTitle("Advertencia Tab_pan3")
                        .setMessage("RESTAURAR INTERRUPTORES Y REGISTRARLO EN COMENTARIOS")
                        .setPositiveButton("Aceptar") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                    alertDialog.show()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        spiTab_pan2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,view: View?,position: Int,id: Long) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "NO") {
                    val alertDialog = AlertDialog.Builder(this@FormEletrico)
                        .setTitle("Advertencia Tab_pan2")
                        .setMessage("RESTAURAR INTERRUPTORES Y REGISTRARLO EN COMENTARIOS")
                        .setPositiveButton("Aceptar") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                    alertDialog.show()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        spiTab_pan1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,view: View?,position: Int,id: Long) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "NO") {
                    val alertDialog = AlertDialog.Builder(this@FormEletrico)
                        .setTitle("Advertencia Tab_pan1")
                        .setMessage("RESTAURAR INTERRUPTORES Y REGISTRARLO EN COMENTARIOS")
                        .setPositiveButton("Aceptar") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                    alertDialog.show()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

// campo spTab_pa_se
        spiTab_pa_se.isEnabled = false
        val qrlauncher11 = registerForActivityResult(ScanContract()){result ->
            if(result.contents != null){
                qrCodeText11 = result.contents
                if(qrCodeText11== "NS-QR-MTTO-D-053"){
                    spiTab_pa_se.isEnabled = true
                    Toast.makeText(this, "Codigo del Area: $qrCodeText11", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "Error: Código QR incorrecto", Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this, "No se escaneó ningún código QR", Toast.LENGTH_SHORT).show()
            }
        }
        btnTan_pa_se.setOnClickListener {
            val options11 = ScanOptions()
            options11.setPrompt("Escanea el codigo QR")
            options11.setBeepEnabled(true)
            options11.setBarcodeImageEnabled(true)
            qrlauncher11.launch(options11)
        }
        spiTab_pa_se.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,view: View?,position: Int,id: Long) {
                val seleccion = parent.getItemAtPosition(position).toString()
                if (seleccion == "NO") {
                    val alertDialog = AlertDialog.Builder(this@FormEletrico)
                        .setTitle("Advertencia Tab_pa_se")
                        .setMessage("RESTAURAR INTERRUPTORES Y REGISTRARLO EN COMENTARIOS")
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

        btnSend2.setOnClickListener {
            val datosFormularioEl = listOf(
                spPlantaEmergencia.selectedItem.toString(),
                FallasEncon.text.toString(),
                spiTab_gn2.selectedItem.toString(),
                spiTab_gn1.selectedItem.toString(),
                spiTab_gsv.selectedItem.toString(),
                spiTab_gcr.selectedItem.toString(),
                spiTab_gse.selectedItem.toString(),
                spiTab_psn.selectedItem.toString(),
                spiTab_ps_sv.selectedItem.toString(),
                spiTab_pb_r.selectedItem.toString(),
                spiTab_pb_sv.selectedItem.toString(),
                spiTab_pb_cr.selectedItem.toString(),
                spiTab_pbn.selectedItem.toString(),
                spiTab_cc_p1_r.selectedItem.toString(),
                spiTab_p1_sv.selectedItem.toString(),
                spiTab_p1n.selectedItem.toString(),
                spiTab_p2_r.selectedItem.toString(),
                spiTab_p2_sv.selectedItem.toString(),
                spiTab_p2_cr.selectedItem.toString(),
                spiTab_p2n.selectedItem.toString(),
                spiTab_p3_r.selectedItem.toString(),
                spiTab_p3_sv.selectedItem.toString(),
                spiTab_p3_cr.selectedItem.toString(),
                spiTab_p3_n1.selectedItem.toString(),
                spiTab_p3_n2.selectedItem.toString(),
                spiTab_p4_r.selectedItem.toString(),
                spiTab_p4_sv.selectedItem.toString(),
                spiTab_p4_cr.selectedItem.toString(),
                spiTab_cc_p4n.selectedItem.toString(),
                spiTab_p4n.selectedItem.toString(),
                spiTab_pan3.selectedItem.toString(),
                spiTab_pan2.selectedItem.toString(),
                spiTab_pan1.selectedItem.toString(),
                spiTab_pa_se.selectedItem.toString(),
                etHallazgosEletrico.text.toString(),
                SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault()).format(Date())
            )

            manejarExcel(
                context = this, fileName = "RecorridoMantenimiento.xlsx",
                sheetName = "RecorridoElectrico", datos = datosFormularioEl,
                headers = listOf(
                    "Planta de emergencia en modo auto",
                    "Errores fallas eléctricas",
                    "TAB-GN2",
                    "TAB-GN1",
                    "TAB-GSV",
                    "TAB-GCR",
                    "TAB-GSE",
                    "TAB-PSN",
                    "TAB-PS-SV",
                    "TAB-PB-R",
                    "TAB-PB-SV",
                    "TAB-PB-CR",
                    "TAB-PBN",
                    "TAB-CC-P1-R",
                    "TAB-P1-SV",
                    "TAB-P1N",
                    "TAB-P2-R",
                    "TAB-P2-SV",
                    "TAB-P2-CR",
                    "TAB-P2N",
                    "TAB-P3-R",
                    "TAB-P3-SV",
                    "TAB-P3-CR",
                    "TAB-P3N1",
                    "TAB-P3N2",
                    "TAB-P4-R",
                    "TAB-P4-SV",
                    "TAB-P4-CR",
                    "CC-P4N",
                    "TAB-P4N",
                    "TAB-PAN3",
                    "TAB-PAN2",
                    "TAB-PAN1",
                    "TAB-PA-SE",
                    "Hallazgos",
                    "Fecha y hora"
                )
            )

            val sharedPreferences = getSharedPreferences("StatusPref", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("statusElectric", "completed")
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