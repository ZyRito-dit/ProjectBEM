package com.example.projectbem.Home.dashboard_item.devisi

import ApiConfig
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectbem.Adapter.PendidikanAdapter
import com.example.projectbem.Data.BemRepository
import com.example.projectbem.Data.UsersViewModel
import com.example.projectbem.Data.UsersViewModelFactory
import com.example.projectbem.Data.room.BemDatabase
import com.example.projectbem.R
import com.example.projectbem.databinding.ActivityPendidikanBinding
import com.google.android.material.appbar.MaterialToolbar

class PendidikanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPendidikanBinding
    private val viewModel: UsersViewModel by viewModels {
        UsersViewModelFactory(
            BemRepository.getInstance(
                BemDatabase.getInstance(this).bemDao(),
                ApiConfig.getApiService(token = null)
            )
        )
    }
    private lateinit var adapter: PendidikanAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPendidikanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarPendidikan)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Pendidikan"
        binding.toolbarPendidikan.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        binding.toolbarPendidikan.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        adapter = PendidikanAdapter()
        binding.rvPendidikan.adapter = adapter
        binding.rvPendidikan.layoutManager = LinearLayoutManager(this)

        viewModel.getToken().observe(this) { user ->
            val token = user?.token
            if (!token.isNullOrEmpty()) {
                viewModel.setToken(token)
                viewModel.getPendidikanBarang(token)
            }
        }

        viewModel.pendidikanBarang.observe(this) { list ->
            adapter.updateList(list)
        }
    }
}