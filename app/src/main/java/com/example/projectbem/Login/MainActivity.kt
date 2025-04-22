package com.example.projectbem.Login


import ApiConfig
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.projectbem.Data.BemRepository
import com.example.projectbem.Data.Result
import com.example.projectbem.Data.UsersViewModel
import com.example.projectbem.Data.UsersViewModelFactory
import com.example.projectbem.Data.room.BemDatabase
import com.example.projectbem.Home.HomeActivity
import com.example.projectbem.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: UsersViewModel by viewModels {
        UsersViewModelFactory(
            BemRepository.getInstance(
                BemDatabase.getInstance(this).bemDao(),
                ApiConfig.getApiService(token = null)
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            val user = viewModel.getUserNow()
            if (user != null) {
                val intent = Intent(this@MainActivity, HomeActivity::class.java)
                startActivity(intent)
                finish()
            } else {

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
                    viewModel.loginUser(username, password)
                }
                lifecycleScope.launch {
                    viewModel.loginState.collect { result ->
                        when(result) {
                            is Result.Error -> {
                                Toast.makeText(this@MainActivity, "Username atau password salah", Toast.LENGTH_SHORT).show()
                            }
                            Result.Loading -> {

                            }
                            is Result.Success -> {
                                Toast.makeText(this@MainActivity, "Login berhasil", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this@MainActivity, HomeActivity::class.java)
                                startActivity(intent)
                                finish()
                            }
                        }
                    }
                }
            }
        }
    }
}