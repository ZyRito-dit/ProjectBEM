package com.example.projectbem.Drawer.SettingMenu

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.projectbem.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingBinding
    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarSetting)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Pengaturan"
        binding.toolbarSetting.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        preferences = getSharedPreferences("app_settings", MODE_PRIVATE)

        val darkModeEnabled = preferences.getBoolean("dark_mode", false)
        val notifEnabled = preferences.getBoolean("notification", false)

        binding.switchDarkMode.isChecked = darkModeEnabled
        binding.switchNotification.isChecked = notifEnabled

        binding.switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            preferences.edit().putBoolean("dark_mode", isChecked).apply()
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        binding.switchNotification.setOnCheckedChangeListener { _, isChecked ->
            preferences.edit().putBoolean("notification", isChecked).apply()
        }
    }
}
