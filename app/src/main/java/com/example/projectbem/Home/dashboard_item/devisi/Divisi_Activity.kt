package com.example.projectbem.Home.dashboard_item.devisi

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.projectbem.R
import com.example.projectbem.databinding.ActivityDivisiBinding
import com.google.android.material.appbar.MaterialToolbar

class Divisi_Activity : AppCompatActivity() {
    private lateinit var binding: ActivityDivisiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDivisiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbarDivisi)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Divisi"
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.ivBendahara.setOnClickListener {
            val intent = Intent(this, BendaharaActivity::class.java)
            startActivity(intent)
        }

        binding.ivSekretaris.setOnClickListener {
            val intent = Intent(this, SekretarisActivity::class.java)
            startActivity(intent)
        }

        binding.ivPendidikan.setOnClickListener {
            val intent = Intent(this, PendidikanActivity::class.java)
            startActivity(intent)
        }

        binding.ivKesehatan.setOnClickListener {
            val intent = Intent(this, KesehatanActivity::class.java)
            startActivity(intent)
        }

        binding.ivDapur.setOnClickListener {
            val intent = Intent(this, DapurActivity::class.java)
            startActivity(intent)
        }

        binding.ivKebersihan.setOnClickListener {
            val intent = Intent(this, KebersihanActivity::class.java)
            startActivity(intent)
        }
    }
}