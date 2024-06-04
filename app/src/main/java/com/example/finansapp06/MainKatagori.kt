package com.example.finansapp06

import android.content.Intent
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import com.example.finansapp06.databinding.ActivityMainKatagoriBinding

class MainKatagori : AppCompatActivity() {

    private lateinit var binding: ActivityMainKatagoriBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainKatagoriBinding.inflate(layoutInflater)
        setContentView(binding.root)



        // BTNgDersayfasDon butonuna tıklandığında
        binding.BTNgDersayfasDon.setOnClickListener {
            val intent = Intent(this, MainGider::class.java)
            startActivity(intent)
        }
    }


}
