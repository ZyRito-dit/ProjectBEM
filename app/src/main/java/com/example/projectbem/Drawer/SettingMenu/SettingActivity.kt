package com.example.projectbem.Drawer.SettingMenu

import ApiConfig
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.projectbem.Data.BemRepository
import com.example.projectbem.Data.UsersViewModel
import com.example.projectbem.Data.UsersViewModelFactory
import com.example.projectbem.Data.room.BemDatabase
import com.example.projectbem.Login.MainActivity
import com.example.projectbem.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingBinding
    private lateinit var preferences: SharedPreferences
    private val viewModel: UsersViewModel by viewModels {
        UsersViewModelFactory(
            BemRepository.getInstance(
                BemDatabase.getInstance(this).bemDao(),
                ApiConfig.getApiService()
            )
        )
    }
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

        binding.buttonLogout.setOnClickListener {
            viewModel.logoutUser()
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
}