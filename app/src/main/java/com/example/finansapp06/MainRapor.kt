package com.example.finansapp06

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.finansapp06.databinding.ActivityMainRaporBinding

class MainRapor : AppCompatActivity() {

    private lateinit var binding: ActivityMainRaporBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainRaporBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
