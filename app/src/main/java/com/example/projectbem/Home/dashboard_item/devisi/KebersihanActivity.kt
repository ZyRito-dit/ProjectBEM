package com.example.projectbem.Home.dashboard_item.devisi

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectbem.Adapter.AlatAdapter
import com.example.projectbem.Data.BemRepository
import com.example.projectbem.Data.UsersViewModel
import com.example.projectbem.Data.UsersViewModelFactory
import com.example.projectbem.Data.room.BemDatabase
import com.example.projectbem.R
import com.example.projectbem.databinding.ActivityKebersihanBinding

class KebersihanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityKebersihanBinding
    private lateinit var adapter: AlatAdapter
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
        binding = ActivityKebersihanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarKebersihan)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Kebersihan"
        binding.toolbarKebersihan.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        adapter = AlatAdapter()
        binding.rvAlat.adapter = adapter
        binding.rvAlat.layoutManager = LinearLayoutManager(this)

        viewModel.fetchAlat()

        viewModel.alatList.observe(this){
            adapter.updateList(it)
        }
    }
}