package com.example.finansapp06

import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.finansapp06.databinding.ActivityMainGelirBinding
import java.util.Date



class MainGelir : AppCompatActivity() {

    private lateinit var binding: ActivityMainGelirBinding
    private lateinit var database: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainGelirBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Veritabanını
        database = this.openOrCreateDatabase("FinansAppDB", MODE_PRIVATE, null)
        database.execSQL("CREATE TABLE IF NOT EXISTS gelir (gelir_id INTEGER PRIMARY KEY, gelir_miktar INTEGER, tarih TIMESTAMP DEFAULT CURRENT_TIMESTAMP)")
        database.execSQL("CREATE TABLE IF NOT EXISTS gider (gider_id INTEGER PRIMARY KEY, gider_miktar INTEGER, giderturu TEXT, tarih TIMESTAMP DEFAULT CURRENT_TIMESTAMP)")

        // Gelir Ekle
        binding.BTNGELREKLE.setOnClickListener {
            val gelirMiktari = binding.gelirgiredittext.text.toString().toIntOrNull()
            if (gelirMiktari != null ) {
                // Veritabanına kaydetme
                kaydetGelir(gelirMiktari)
                // Kullanıcıya bilgi ver
                Toast.makeText(this, "Gelir kaydedildi", Toast.LENGTH_SHORT).show()
            } else {
                // Hata işleme
                binding.gelirgiredittext.error = "Geçerli bir miktar girin (pozitif bir sayı)"
            }
        }

        // Sıfırla butonuna
        binding.BTNsfrgelR.setOnClickListener {
            // Gelir tablosunu sıfırla
            database.execSQL("DELETE FROM gelir")
            // Kullanıcıya bilgi ver
            Toast.makeText(this, "Veriler sıfırlandı", Toast.LENGTH_SHORT).show()
        }

        // Anasayfa butonuna
        binding.BTNanasayfagelR.setOnClickListener {
            val intent = Intent(this, MainAnasayfa::class.java)
            startActivity(intent)
        }
    }

    private fun kaydetGelir(miktar: Int) {
        val tarih = Date()

        val values = ContentValues().apply {
            put("gelir_miktar", miktar)
            put("tarih", tarih.time) // Milisaniye cinsinden zamanı kaydet
        }



    database.insert("gelir", null, values)
    }
}
