package com.example.finansapp06

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainAnasayfa : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_anasayfa)

        // Butonları tanımlayın
        val btnGelirGir: Button = findViewById(R.id.BTNGELİRGİR)
        val btnGiderGir: Button = findViewById(R.id.BTNGİDERGİR)
        val btnRapor: Button = findViewById(R.id.BTNRaporgit)

        // Butonlara tıklama olaylarını dinleyici olarak ekleme
        btnGelirGir.setOnClickListener {
            openActivity(MainGelir::class.java)
        }

        btnGiderGir.setOnClickListener {
            openActivity(MainGider::class.java)
        }

        btnRapor.setOnClickListener {
            openActivity(MainRapor::class.java)
        }
    }

    // Diğer aktivitelere yönlendirme işlemini gerçekleştiren fonksiyon
    private fun openActivity(activityClass: Class<*>) {
        val intent = Intent(this, activityClass)
        startActivity(intent)
    }
}
