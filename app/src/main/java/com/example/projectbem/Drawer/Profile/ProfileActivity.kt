package com.example.projectbem.Drawer.Profile

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.projectbem.Data.repository.ProfileRepository
import com.example.projectbem.Data.response.ProfileResponse
import com.example.projectbem.Data.retrofit.ApiConfig
import com.example.projectbem.Helper.UserSessionManager
import com.example.projectbem.R
import com.example.projectbem.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var viewModel: ProfileViewModel
    private var currentProfile: ProfileResponse? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val repository = ProfileRepository(ApiConfig.getApiService(this))
        viewModel = ProfileViewModel(repository)


        viewModel.profileLiveData.observe(this) { profile ->
            if (profile != null) {
                updateUI(profile)
            } else {
                Toast.makeText(this, "Gagal memuat data profil", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.loadProfile()


        binding.btnEditProfile.setOnClickListener {
            currentProfile?.let { profile ->
                val intent = Intent(this, EdtActivity::class.java)
                intent.putExtra("USERNAME", profile.username)
                intent.putExtra("IMAGE_URL", profile.image?.toString())
                startActivity(intent)
            } ?: Toast.makeText(this, "Data profil belum tersedia", Toast.LENGTH_SHORT).show()
        }



        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun updateUI(profile: ProfileResponse) {
        binding.tvName.text = profile.username
        binding.TvNim.text = profile.nim
        currentProfile = profile
        val sharedPref = getSharedPreferences("APP_PREF", MODE_PRIVATE)
        sharedPref.edit().putString("ROLE", profile.role).apply()

        val session = UserSessionManager(this)
        session.saveUserData(profile.username, profile.nim, profile.role, profile.image?.toString() ?: "")

        val imgUrl = session.getImageUrl()
        if (!imgUrl.isNullOrBlank()) {
            Glide.with(this)
                .load(imgUrl)
                .into(findViewById(R.id.headerImage))
        }

        val imageUrl = profile.image?.toString()
        if (!imageUrl.isNullOrBlank()) {
            Glide.with(this)
                .load(imageUrl)
                .into(binding.profileImage)
        } else {
            binding.profileImage.setImageResource(R.drawable.anby_post)
        }
    }

}
