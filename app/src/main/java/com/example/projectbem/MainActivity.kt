package com.example.projectbem

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.projectbem.Drawer.SettingMenu.SettingPreferences
import com.example.projectbem.Drawer.SettingMenu.SettingViewModel
import com.example.projectbem.Drawer.SettingMenu.SettingViewModelFactory
import com.example.projectbem.Drawer.SettingMenu.dataStore
import com.example.projectbem.Home.HomeActivity
import com.example.projectbem.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}