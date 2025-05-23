package com.example.projectbem.Drawer.Profile

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projectbem.R
import com.example.projectbem.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener{
           onBackPressed()
            finish()
        }

        binding.btnEditProfile.setOnClickListener{
            val intent = Intent(this, EdtActivity::class.java)
            startActivity(intent)
        }


    }


}