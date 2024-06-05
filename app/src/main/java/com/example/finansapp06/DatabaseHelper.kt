package com.example.finansapp06

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "FinansAppDB"
        const val DATABASE_VERSION = 2 // Sürümü artırdık
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE IF NOT EXISTS gelir (" +
                    "gelir_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "gelir_miktar INTEGER," +
                    "tarih TEXT)"
        )
        db.execSQL(
            "CREATE TABLE IF NOT EXISTS gider (" +
                    "gider_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "gider_miktar INTEGER," + // Eksik sütunu ekledik
                    "giderturu TEXT," +
                    "tarih TEXT)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Veritabanı sürümü yükseltilirse, tabloları silip yeniden oluşturun.
        db.execSQL("DROP TABLE IF EXISTS gelir")
        db.execSQL("DROP TABLE IF EXISTS gider")
        onCreate(db)
    }
}