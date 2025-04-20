package com.example.projectbem.Drawer.Profile

import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projectbem.R
import com.example.projectbem.databinding.ActivityEdtBinding

class EdtActivity : AppCompatActivity() {

    lateinit var binding: ActivityEdtBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEdtBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnedtbackprofile.setOnClickListener{
            onBackPressed()
            finish()
        }

    }
}