package com.example.finansapp06

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.finansapp06.databinding.ActivityMainKayitOlBinding

class MainKayitOl : AppCompatActivity() {
    private lateinit var binding: ActivityMainKayitOlBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainKayitOlBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("bilgileri", MODE_PRIVATE)

        binding.btnkaydet.setOnClickListener {
            val kullaniciBilgisi = binding.kayitKullaniciAdi.text.toString()
            val kullaniciParola = binding.kayitParola.text.toString()

            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString("kullanici", kullaniciBilgisi)
            editor.putString("parola", kullaniciParola)
            editor.apply()

            Toast.makeText(applicationContext, "Kayıt başarılı", Toast.LENGTH_LONG).show()

            binding.kayitKullaniciAdi.text.clear()
            binding.kayitParola.text.clear()
        }

        binding.btnGiriseDon.setOnClickListener {
            val intent = Intent(this@MainKayitOl, MainActivity::class.java)
            startActivity(intent)
        }

        enableEdgeToEdge() // Kenarlardan kenarlara ekran düzenini etkinleştir
    }

    private fun enableEdgeToEdge() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
// güncellenmiş kısmı burasıdır