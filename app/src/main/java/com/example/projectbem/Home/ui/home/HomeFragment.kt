package com.example.projectbem.Home.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectbem.Adapter.EventAdapter
import com.example.projectbem.Data.EventModel
import com.example.projectbem.R
import com.example.projectbem.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var eventAdapter: EventAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)


        val eventList = listOf(
            EventModel("Lomba Futsal", "Kompetisi futsal antar angkatan", R.drawable.nahida_post),
            EventModel("Hari Santri", "Acara besar pondok dengan lomba dan pentas seni", R.drawable.raiden_mei_post),
            EventModel("Pelatihan IT", "Belajar pemrograman dan desain", R.drawable.wise_post)
        )


        eventAdapter = EventAdapter(eventList)
        binding.recyclerEvent.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = eventAdapter
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
