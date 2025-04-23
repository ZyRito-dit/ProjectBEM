package com.example.projectbem.Home.dashboard_item.devisi

import ApiConfig
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectbem.Adapter.SekretarisAdapter
import com.example.projectbem.Data.BemRepository
import com.example.projectbem.Data.UsersViewModel
import com.example.projectbem.Data.UsersViewModelFactory
import com.example.projectbem.Data.room.BemDatabase
import com.example.projectbem.R
import com.example.projectbem.databinding.ActivitySekretarisBinding
import com.google.android.material.appbar.MaterialToolbar

class SekretarisActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySekretarisBinding
    private val viewModel: UsersViewModel by viewModels {
        UsersViewModelFactory(
            BemRepository.getInstance(
                BemDatabase.getInstance(this).bemDao(),
                ApiConfig.getApiService(token = null)
            )
        )
    }
    private lateinit var adapter: SekretarisAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySekretarisBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbarSekretaris)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Sekretaris"
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        adapter = SekretarisAdapter(listOf())
        binding.rvSekretaris.adapter = adapter
        binding.rvSekretaris.layoutManager = LinearLayoutManager(this)

        val allMonths = listOf(
            "Januari", "Februari", "Maret", "April", "Mei", "Juni",
            "Juli", "Agustus", "September", "Oktober", "November", "Desember"
        )

        allMonths.forEach {
            viewModel.fetchNotulenByBulan(it)
        }

        viewModel.notulenList.observe(this) { notulenList ->
            adapter.updateData(notulenList)
        }
    }
}