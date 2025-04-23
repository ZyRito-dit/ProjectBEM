package com.example.projectbem.Home.dashboard_item.devisi

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectbem.Adapter.MenuAdapter
import com.example.projectbem.Adapter.PendidikanAdapter
import com.example.projectbem.Adapter.PiketMakanAdapter
import com.example.projectbem.Data.BemRepository
import com.example.projectbem.Data.UsersViewModel
import com.example.projectbem.Data.UsersViewModelFactory
import com.example.projectbem.Data.room.BemDatabase
import com.example.projectbem.R
import com.example.projectbem.databinding.ActivityDapurBinding

class DapurActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDapurBinding

    private val viewModel: UsersViewModel by viewModels {
        UsersViewModelFactory(
            BemRepository.getInstance(
                BemDatabase.getInstance(this).bemDao(),
                ApiConfig.getApiService(token = null)
            )
        )
    }

    private lateinit var adapter: PiketMakanAdapter

    private lateinit var menuAdapter: MenuAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDapurBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarPendidikan)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Dapur"
        binding.toolbarPendidikan.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        binding.toolbarPendidikan.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        adapter = PiketMakanAdapter()
        binding.rvPiket.adapter = adapter
        binding.rvPiket.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        viewModel.fetchPiketMakan()

        viewModel.piketList.observe(this) { list ->
            adapter.submitList(list)
        }

        menuAdapter = MenuAdapter()
        binding.rvMenu.adapter = menuAdapter
        binding.rvMenu.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        viewModel.fetchMenu()
        viewModel.menuList.observe(this) { list ->
            menuAdapter.submitList(list)
        }
    }
}