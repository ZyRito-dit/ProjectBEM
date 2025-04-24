package com.example.projectbem.Drawer.SettingMenu

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.projectbem.Login.MainActivity
import com.example.projectbem.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingBinding
    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup Toolbar
        setSupportActionBar(binding.toolbarSetting)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = "Pengaturan"
        }
        binding.toolbarSetting.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // Ambil shared preferences
        preferences = getSharedPreferences("app_settings", MODE_PRIVATE)

        // Set switch sesuai nilai yang tersimpan
        binding.switchDarkMode.isChecked = preferences.getBoolean("dark_mode", false)
        binding.switchNotification.isChecked = preferences.getBoolean("notification", false)

        // Dark Mode
        binding.switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            preferences.edit().putBoolean("dark_mode", isChecked).apply()
            AppCompatDelegate.setDefaultNightMode(
                if (isChecked) AppCompatDelegate.MODE_NIGHT_YES
                else AppCompatDelegate.MODE_NIGHT_NO
            )
        }

        // Notifikasi
        binding.switchNotification.setOnCheckedChangeListener { _, isChecked ->
            preferences.edit().putBoolean("notification", isChecked).apply()
        }

        // Logout Button
        binding.buttonLogout.setOnClickListener {
            logoutUser()
        }
    }

    private fun logoutUser() {
        val userPref = getSharedPreferences("user_session", MODE_PRIVATE)
        userPref.edit().clear().apply()

        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}
