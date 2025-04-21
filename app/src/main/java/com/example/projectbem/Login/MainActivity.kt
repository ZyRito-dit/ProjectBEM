package com.example.projectbem.Login


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.projectbem.Data.BemRepository
import com.example.projectbem.Data.Result
import com.example.projectbem.Data.UsersViewModel
import com.example.projectbem.Data.UsersViewModelFactory
import com.example.projectbem.Data.retrofit.ApiConfig
import com.example.projectbem.Data.room.BemDatabase
import com.example.projectbem.Home.HomeActivity
import com.example.projectbem.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: UsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dao = BemDatabase.getInstance(this).bemDao()
        val apiService = ApiConfig.getApiService()
        val repository = BemRepository.getInstance(dao, apiService)
        val factory = UsersViewModelFactory(repository)

        viewModel = ViewModelProvider(this, factory)[UsersViewModel::class.java]

        viewModel.users.observe(this) { result ->
            when (result) {
                is Result.Error -> {

                    Toast.makeText(this, result.error ?: "Login gagal", Toast.LENGTH_SHORT).show()
                }
                Result.Loading -> {

                }
                is Result.Success -> {
                    val userData = result.data
                    val token = userData.token
                    val role = userData.role


                    val sharedPref = getSharedPreferences("MYAPP", Context.MODE_PRIVATE)
                    with(sharedPref.edit()) {
                        putString("TOKEN", token)
                        putString("ROLE", role)
                        apply()
                    }

                    Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                }
            }
        }

        binding.btnLogin.setOnClickListener {
            val username = binding.edtNIK.text.toString().trim()
            val nim = binding.edtPassword.text.toString().trim()

            var isValid = true
            if (username.isEmpty()) {
                binding.edtNIK.error = "This field is required"
                isValid = false
            }
            if (nim.isEmpty()) {
                binding.edtPassword.error = "This field is required"
                isValid = false
            }

            if (isValid) {
                viewModel.login(username, nim)
            }
        }
    }
}
