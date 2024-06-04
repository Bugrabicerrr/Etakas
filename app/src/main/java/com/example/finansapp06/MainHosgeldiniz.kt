package com.example.finansapp06


import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.finansapp06.databinding.ActivityMainHosgeldinizBinding


class MainHosgeldiniz : AppCompatActivity() {
    lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainHosgeldinizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferences = getSharedPreferences("bilgileri", MODE_PRIVATE)
        val kayitliKullanici = preferences.getString("kullanici", "")
        val kayitliParola = preferences.getString("parola", "")

        binding.kullaniciBilgisi.text = "Kullanıcı Adı: $kayitliKullanici"
        binding.kullaniciParola.text = "Parola: $kayitliParola"

        binding.btnCikisYap.setOnClickListener {
            val intent = Intent(this@MainHosgeldiniz, MainActivity::class.java)
            startActivity(intent)
        }


        binding.btnAnasayfa.setOnClickListener {
            val intent = Intent(this@MainHosgeldiniz, MainAnasayfa::class.java)
            startActivity(intent)
        }

        enableEdgeToEdge(binding.root)
    }

    private fun enableEdgeToEdge(view: View) {
        ViewCompat.setOnApplyWindowInsetsListener(view) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
