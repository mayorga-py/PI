package com.example.proyectnewro

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import org.apache.poi.ss.usermodel.WorkbookFactory
import java.io.File
import java.io.FileInputStream

class AdminActivity : AppCompatActivity() {

    private val REQUEST_CODE = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.vista_admin)

        // Solicitar permiso
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                REQUEST_CODE
            )
        } else {
            cargarExcel()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE && grantResults.isNotEmpty() &&
            grantResults[0] == PackageManager.PERMISSION_GRANTED
        ) {
            cargarExcel()
        } else {
            Toast.makeText(this, "Permiso denegado", Toast.LENGTH_SHORT).show()
        }
    }

    private fun cargarExcel() {
        val path =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        val archivo = File(path, "recorridomantenimiento.xlsx")

        if (!archivo.exists()) {
            Toast.makeText(this, "Archivo no encontrado", Toast.LENGTH_SHORT).show()
            return
        }

        try {
            val tableLayout = findViewById<TableLayout>(R.id.tableLayout)
            val inputStream = FileInputStream(archivo)
            val workbook = WorkbookFactory.create(inputStream)
            val sheet = workbook.getSheetAt(0)

            for (i in 0..sheet.lastRowNum) {
                val row = sheet.getRow(i)
                val tableRow = TableRow(this)

                for (j in 0 until row.physicalNumberOfCells) {
                    val cell = row.getCell(j)
                    val cellText = cell?.toString() ?: ""
                    val textView = TextView(this)
                    textView.text = cellText
                    textView.setPadding(8, 8, 8, 8)
                    tableRow.addView(textView)
                }

                tableLayout.addView(tableRow)
            }

            inputStream.close()

        } catch (e: Exception) {
            Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
    }
}
