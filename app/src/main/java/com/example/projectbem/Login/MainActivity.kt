package com.example.projectbem.Login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projectbem.Data.response.LoginRequest
import com.example.projectbem.Data.response.LoginResponse
import com.example.projectbem.Data.response.ProfileResponse
import com.example.projectbem.Data.retrofit.ApiConfig
import com.example.projectbem.Home.HomeActivity
import com.example.projectbem.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPref = getSharedPreferences("APP_PREF", MODE_PRIVATE)
        val isLoggedIn = sharedPref.getBoolean("IS_LOGGED_IN", false)

        if (isLoggedIn) {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
            return
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val username = binding.edtNIK.text.toString()
            val nim = binding.edtPassword.text.toString()

            if (username.isNotEmpty() && nim.isNotEmpty()) {
                loginUser(username, nim)
            } else {
                Toast.makeText(this, "Harap isi semua field", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loginUser(username: String, nim: String) {
        val request = LoginRequest(username = username, nim = nim)

        ApiConfig.getApiService(this@MainActivity).login(request)
            .enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    if (response.isSuccessful) {
                        // Setelah login sukses, ambil profil
                        ApiConfig.getApiService(this@MainActivity).getProfileCall()
                            .enqueue(object : Callback<ProfileResponse> {
                                override fun onResponse(
                                    call: Call<ProfileResponse>,
                                    response: Response<ProfileResponse>
                                ) {
                                    if (response.isSuccessful) {
                                        val profile = response.body()
                                        val sharedPref = getSharedPreferences("APP_PREF", MODE_PRIVATE)
                                        sharedPref.edit()
                                            .putBoolean("IS_LOGGED_IN", true)
                                            .putString("ROLE", profile?.role)
                                            .apply()

                                        startActivity(Intent(this@MainActivity, HomeActivity::class.java))
                                        finish()
                                    } else {
                                        Toast.makeText(
                                            this@MainActivity,
                                            "Gagal ambil data profil",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }

                                override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                                    Toast.makeText(
                                        this@MainActivity,
                                        "Gagal koneksi ke server",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            })
                    } else {
                        Toast.makeText(
                            this@MainActivity,
                            "Login gagal. Cek username atau NIM.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Toast.makeText(
                        this@MainActivity,
                        "Terjadi kesalahan: ${t.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }
}
