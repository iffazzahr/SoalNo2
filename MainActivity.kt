package com.example.soal2

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputJamkerja = findViewById<EditText>(R.id.JamKerja)
        val inputTarif = findViewById<EditText>(R.id.Tarif)
        val button = findViewById<Button>(R.id.btnHitung)
        val tvHasil = findViewById<TextView>(R.id.Hasil)


        button.setOnClickListener {

            // Membersihkan input dari tanda titik
            val jamKerja = inputJamkerja.text.toString().replace(".", "").toDoubleOrNull()
            val tarifPerJam = inputTarif.text.toString().replace(".", "").toDoubleOrNull()

            // Validasi input
            if (jamKerja == null || tarifPerJam == null) {
                tvHasil.text = "Masukkan angka yang valid!"
                return@setOnClickListener
            }

            val batasJamNormal = 40
            val tarifLembur = 1.5

            // Perhitungan gaji
            val gaji = if (jamKerja <= batasJamNormal) {
                jamKerja * tarifPerJam
            } else {
                val jamLembur = jamKerja - batasJamNormal
                (batasJamNormal * tarifPerJam) + (jamLembur * tarifPerJam * tarifLembur)
            }

            // Format hasil gaji dengan pemisah ribuan
            val formattedGaji = String.format("%,.2f", gaji)

            // Menampilkan hasil
            tvHasil.text = "Gaji total: Rp $formattedGaji"
        }

    }
}