package com.example.finansapp06

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.finansapp06.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGirisyap.setOnClickListener {
            val intent = Intent(this@MainActivity, MainHosgeldiniz::class.java)
            startActivity(intent)
        }
        binding.btnKayit.setOnClickListener {
            val intent = Intent(this@MainActivity, MainKayitOl::class.java)
            startActivity(intent)
        }

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun enableEdgeToEdge() {
        // Eğer kullanıyorsanız, gerekli edge-to-edge ayarlarını buraya ekleyebilirsiniz.
    }
}
