package com.example.finansapp06

import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.finansapp06.databinding.ActivityMainGiderBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainGider : AppCompatActivity() {

    private lateinit var binding: ActivityMainGiderBinding
    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var database: SQLiteDatabase
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainGiderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseHelper = DatabaseHelper(this)
        database = databaseHelper.writableDatabase

        adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, mutableListOf())
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner2.adapter = adapter

        binding.kategoriedittextd.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                s?.toString()?.let {
                    adapter.clear()
                    if (it.isNotEmpty()) {
                        adapter.add(it)
                    }
                    adapter.notifyDataSetChanged()
                }
            }
        })

        binding.BTNanasayfagDer.setOnClickListener {
            val intent = Intent(this, MainAnasayfa::class.java)
            startActivity(intent)
        }

        binding.BTNsfrgDer.setOnClickListener {
            database.execSQL("DELETE FROM gider")
            Toast.makeText(this, "Gider verileri sıfırlandı", Toast.LENGTH_SHORT).show()
        }

        binding.BTNGIDER.setOnClickListener {
            val miktarString = binding.gidergiredittext.text.toString()
            val miktar = miktarString.toIntOrNull()
            val tur = binding.spinner2.selectedItem?.toString() ?: ""
            val tarih = getCurrentDate()

            if (miktar != null && tur.isNotBlank()) {
                insertData(miktar, tur, tarih)
                Toast.makeText(this, "Gider kaydedildi", Toast.LENGTH_SHORT).show()
                binding.gidergiredittext.text.clear() // Giriş alanını temizle
            } else {
                // Hata mesajı göster
                Toast.makeText(this, "Lütfen geçerli bir miktar ve kategori girin", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return dateFormat.format(Date())
    }

    private fun insertData(miktar: Int, tur: String, tarih: String) {
        val values = ContentValues().apply {
            put("gider_miktar", miktar)
            put("giderturu", tur)
            put("tarih", tarih)
        }
        database.insert("gider", null, values)
    }
}