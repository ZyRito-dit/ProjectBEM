package com.example.projectbem.Home.ui.Jadwal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectbem.Adapter.JadwalAdapter
import com.example.projectbem.databinding.FragmentJadwalBinding
import java.text.SimpleDateFormat
import java.util.*

class JadwalFragment : Fragment() {

    private var _binding: FragmentJadwalBinding? = null
    private val binding get() = _binding!!

    private lateinit var jadwalAdapter: JadwalAdapter
    private val viewModel: JadwalViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJadwalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTanggalHariIni()
        setupRecyclerView()
        observeJadwal()
        setupTodayButton()
    }

    private fun setTanggalHariIni() {
        val calendar = Calendar.getInstance()
        val day = SimpleDateFormat("dd", Locale.getDefault()).format(calendar.time)
        val dayAndMonth = SimpleDateFormat("EEE\nMMM yyyy", Locale.getDefault()).format(calendar.time)

        binding.txtTanggal.text = day
        binding.txtHariTahun.text = dayAndMonth
    }

    private fun setupRecyclerView() {
        jadwalAdapter = JadwalAdapter(emptyList())
        binding.rvJadwal.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = jadwalAdapter
        }
    }

    private fun observeJadwal() {
        viewModel.listJadwal.observe(viewLifecycleOwner) { list ->
            jadwalAdapter = JadwalAdapter(list)
            binding.rvJadwal.adapter = jadwalAdapter
        }
    }

    private fun setupTodayButton() {
        binding.btnToday.setOnClickListener {
            val date = SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(Date())
            Toast.makeText(requireContext(), "Hari ini: $date", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}