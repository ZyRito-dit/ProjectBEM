package com.example.projectbem.Home.dashboard_item.devisi

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.projectbem.Adapter.ObatAdapter
import com.example.projectbem.Adapter.PasienAdapter
import com.example.projectbem.Data.BemRepository
import com.example.projectbem.Data.UsersViewModel
import com.example.projectbem.Data.UsersViewModelFactory
import com.example.projectbem.Data.room.BemDatabase
import com.example.projectbem.R
import com.example.projectbem.databinding.ActivityKesehatanBinding

class KesehatanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityKesehatanBinding
    private lateinit var adapter: ObatAdapter
    private lateinit var pasienAdapter: PasienAdapter
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
        binding = ActivityKesehatanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarKesehatan)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Kesehatan"
        binding.toolbarKesehatan.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        binding.toolbarKesehatan.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        adapter = ObatAdapter()
        binding.rvObat.adapter = adapter
        binding.rvObat.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        viewModel.fetchObat()

        viewModel.obatList.observe(this){
            adapter.updateList(it)
        }

        pasienAdapter = PasienAdapter()
        binding.tvPasien.adapter = pasienAdapter
        binding.tvPasien.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        viewModel.fetchPasien()

        viewModel.pasienList.observe(this){
            pasienAdapter.updateList(it)
        }
    }
}