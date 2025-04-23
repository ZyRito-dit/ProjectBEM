package com.example.projectbem.Home.dashboard_item.devisi

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projectbem.R
import com.example.projectbem.databinding.ActivityDapurBinding

class DapurActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDapurBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDapurBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarPendidikan)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Dapur"
        binding.toolbarPendidikan.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        binding.toolbarPendidikan.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}