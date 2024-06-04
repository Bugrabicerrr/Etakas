package com.example.finansapp06

import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class MainGider : AppCompatActivity() {

    // Gerekli değişkenlerin tanımlanması
    private lateinit var btnAnaSayfaGider: Button // Ana sayfa düğmesi
    private lateinit var btnClearTable: Button // Tabloyu temizleme düğmesi
    private lateinit var btnGiderEkle: Button // Gider ekleme düğmesi
    private lateinit var database: SQLiteDatabase // SQLite veritabanı
    private lateinit var kategoriedittextd: EditText // Kategori giriş alanı
    private lateinit var gidergiredittext: EditText // Gider giriş alanı
    private lateinit var spinner2: Spinner // Kategori seçim listesi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_gider)

        // Veritabanının oluşturulması veya açılması
        database = openOrCreateDatabase("FinansAppDB", MODE_PRIVATE, null)

        // Buton ve diğer görsel öğelerin tanımlanması
        btnAnaSayfaGider = findViewById(R.id.BTNanasayfagıder) // Ana sayfa düğmesi
        btnClearTable = findViewById(R.id.BTNsfrgıder) // Tabloyu temizleme düğmesi
        btnGiderEkle = findViewById(R.id.BTNgıdergır) // Gider ekleme düğmesi
        kategoriedittextd = findViewById(R.id.kategoriedittextd) // Kategori giriş alanı
        gidergiredittext = findViewById(R.id.gidergiredittext) // Gider giriş alanı
        spinner2 = findViewById(R.id.spinner2) // Kategori seçim listesi

        // Ana sayfa düğmesine tıklanınca ana sayfaya geçişi sağlayan işlev
        btnAnaSayfaGider.setOnClickListener {
            val intent = Intent(this, MainAnasayfa::class.java)
            startActivity(intent)
        }

        // Tabloyu temizleme düğmesine tıklanınca veritabanındaki Gider tablosunu temizleyen işlev
        btnClearTable.setOnClickListener {
            clearTable()
        }

        // Gider ekleme düğmesine tıklanınca gider miktarını ve türünü veritabanına ekleyen işlev
        btnGiderEkle.setOnClickListener {
            val miktarString = gidergiredittext.text.toString()
            val miktar = miktarString.toIntOrNull() // Gider miktarı
            val tur = spinner2.selectedItem.toString() // Gider türü

            if (miktar != null && tur.isNotBlank()) { // Gerekli kontrol işlemleri
                insertData(miktar, tur) // Veritabanına gider verisinin eklenmesi
            } else {
                // Miktar veya tür boş ise veya miktar geçerli bir sayı değilse uygun bir hata mesajı gösterilebilir.
            }
        }
    }

    // Veritabanına gider verisinin eklenmesini sağlayan işlev
    private fun insertData(miktar: Int, tur: String) {
        val contentValues = ContentValues().apply {
            put("gider_miktar", miktar) // Gider miktarı sütunu
            put("giderturu", tur) // Gider türü sütunu
        }
        database.insert("gider", null, contentValues) // Veritabanına ekleme işlemi
        // Veritabanına ekleme işlemi başarılı olduysa, kullanıcıya bir mesaj gösterebilirsiniz.
    }

    // Gider tablosunu temizleyen işlev
    private fun clearTable() {
        database.execSQL("DELETE FROM gider") // Gider tablosunu temizleme işlemi
    }
}
