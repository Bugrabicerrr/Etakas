package com.example.finansapp06

import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.finansapp06.databinding.ActivityMainRaporBinding
import java.text.SimpleDateFormat
import java.util.*

class MainRapor : AppCompatActivity() {

    private lateinit var binding: ActivityMainRaporBinding
    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var database: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainRaporBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseHelper = DatabaseHelper(this)
        database = databaseHelper.readableDatabase

        binding.BTNsorgula.setOnClickListener {
            val startDate = binding.editTextDateGelir.text.toString()
            val endDate = binding.editTextDateGider.text.toString()

            if (startDate.isBlank() || endDate.isBlank()) {
                Toast.makeText(this, "Lütfen başlangıç ve bitiş tarihlerini girin", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val gelirData = getGelirData(startDate, endDate)
            val giderData = getGiderData(startDate, endDate)

            val report = generateReport(gelirData, giderData)
            binding.reportTextView.text = report
        }
    }

    private fun getGelirData(startDate: String, endDate: String): List<Gelir> {
        val gelirList = mutableListOf<Gelir>()
        val cursor = database.rawQuery(
            "SELECT * FROM gelir WHERE tarih BETWEEN ? AND ?",
            arrayOf(startDate, endDate)
        )
        while (cursor.moveToNext()) {
            val miktar = cursor.getInt(cursor.getColumnIndexOrThrow("gelir_miktar"))
            val tarih = cursor.getString(cursor.getColumnIndexOrThrow("tarih"))
            gelirList.add(Gelir(miktar, tarih))
        }
        cursor.close()
        return gelirList
    }

    private fun getGiderData(startDate: String, endDate: String): List<Gider> {
        val giderList = mutableListOf<Gider>()
        val cursor = database.rawQuery(
            "SELECT * FROM gider WHERE tarih BETWEEN ? AND ?",
            arrayOf(startDate, endDate)
        )
        while (cursor.moveToNext()) {
            val miktar = cursor.getInt(cursor.getColumnIndexOrThrow("gider_miktar"))
            val tur = cursor.getString(cursor.getColumnIndexOrThrow("giderturu"))
            val tarih = cursor.getString(cursor.getColumnIndexOrThrow("tarih"))
            giderList.add(Gider(miktar, tur, tarih))
        }
        cursor.close()
        return giderList
    }

    private fun generateReport(gelirData: List<Gelir>, giderData: List<Gider>): String {
        val report = StringBuilder()
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

        var toplamGelir = 0
        report.append("Gelir Raporu:\n")
        if (gelirData.isEmpty()) {
            report.append("Bu tarih aralığında gelir bulunamadı.\n")
        } else {
            for (gelir in gelirData) {
                report.append("${dateFormat.format(Date(gelir.tarih))} - ${gelir.miktar} TL\n")
                toplamGelir += gelir.miktar
            }
            report.append("Toplam Gelir: $toplamGelir TL\n")
        }

        var toplamGider = 0
        report.append("\nGider Raporu:\n")
        if (giderData.isEmpty()) {
            report.append("Bu tarih aralığında gider bulunamadı.\n")
        } else {
            for (gider in giderData) {
                report.append("${dateFormat.format(Date(gider.tarih))} - ${gider.miktar} TL - ${gider.tur}\n")
                toplamGider += gider.miktar
            }
            report.append("Toplam Gider: $toplamGider TL\n")
        }

        return report.toString()
    }
}