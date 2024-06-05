package com.example.finansapp06

import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.finansapp06.databinding.ActivityMainGelirBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainGelir : AppCompatActivity() {

    private lateinit var binding: ActivityMainGelirBinding
    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var database: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainGelirBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseHelper = DatabaseHelper(this)
        database = databaseHelper.writableDatabase

        binding.BTNGELREKLE.setOnClickListener {
            val gelirMiktari = binding.gelirgiredittext.text.toString().toIntOrNull()
            if (gelirMiktari != null) {
                kaydetGelir(gelirMiktari)
                Toast.makeText(this, "Gelir kaydedildi", Toast.LENGTH_SHORT).show()
                binding.gelirgiredittext.text.clear() // Giriş alanını temizle
            } else {
                binding.gelirgiredittext.error = "Geçerli bir miktar girin (pozitif bir sayı)"
            }
        }

        binding.BTNsfrgelR.setOnClickListener {
            database.execSQL("DELETE FROM gelir")
            Toast.makeText(this, "Gelir verileri sıfırlandı", Toast.LENGTH_SHORT).show()
        }

        binding.BTNanasayfagelR.setOnClickListener {
            val intent = Intent(this, MainAnasayfa::class.java)
            startActivity(intent)
        }
    }

    private fun kaydetGelir(miktar: Int) {
        val tarih = getCurrentDate()

        val values = ContentValues().apply {
            put("gelir_miktar", miktar)
            put("tarih", tarih)
        }

        database.insert("gelir", null, values)
    }

    private fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return dateFormat.format(Date())
    }
}