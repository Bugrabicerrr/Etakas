package com.example.finansapp06

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.finansapp06.databinding.ActivityMainBinding

class  MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferences = getSharedPreferences("bilgileri", MODE_PRIVATE)

        binding.btnGirisyap.setOnClickListener {
            val kayitliKullanici = preferences.getString("kullanici", "")
            val kayitliParola = preferences.getString("parola", "")

            val girisKullanici = binding.girisKullaniciAdi.text.toString()
            val girisParola = binding.girisParola.text.toString()

            if (kayitliKullanici == girisKullanici && kayitliParola == girisParola) {
                val intent = Intent(this@MainActivity, MainHosgeldiniz::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(applicationContext, "Giriş bilgileri hatalı", Toast.LENGTH_LONG).show()
            }
        }

        binding.btnKayit.setOnClickListener {
            val intent = Intent(this@MainActivity, MainKayitOl::class.java)
            startActivity(intent)
        }

        enableEdgeToEdge()
    }

    private fun enableEdgeToEdge() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
