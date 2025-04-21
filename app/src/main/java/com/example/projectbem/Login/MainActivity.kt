package com.example.projectbem.Login


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
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
    private val viewModel: UsersViewModel by viewModels {
        UsersViewModelFactory(
            BemRepository.getInstance(
                BemDatabase.getInstance(this).bemDao(),
                ApiConfig.getApiService(token = "")
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val username = binding.edtNIK.text.toString().trim()
            val password = binding.edtPassword.text.toString().trim()

            if (username.isEmpty()) {
                binding.edtNIK.error = "Username wajib diisi"
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                binding.edtPassword.error = "Password wajib diisi"
                return@setOnClickListener
            }
            if (username.isEmpty() && password.isEmpty()) {
                binding.edtNIK.error = "Username wajib diisi"
                binding.edtPassword.error = "Password wajib diisi"
                return@setOnClickListener
            }
            loginUser(username, password)
        }
    }
    private fun loginUser(username: String, nim: String){
        viewModel.login(username, nim).observe(this) { result ->
            when(result){
                is Result.Error -> {
                    Toast.makeText(this, "Usernamer atau password salah", Toast.LENGTH_SHORT).show()
                }
                Result.Loading -> {

                }
                is Result.Success -> {
                    Toast.makeText(this, "Selamat datang ${result.data.role}", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.getUser { user ->
            if (user != null) {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}