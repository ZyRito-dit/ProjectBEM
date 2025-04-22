package com.example.projectbem.Home.ui.home

import ApiConfig
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectbem.Adapter.EventAdapter
import com.example.projectbem.Data.BemRepository
import com.example.projectbem.Data.UsersViewModel
import com.example.projectbem.Data.UsersViewModelFactory
import com.example.projectbem.Data.room.BemDatabase
import com.example.projectbem.Home.ui.EventsDetailActivity
import com.example.projectbem.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var eventAdapter: EventAdapter

    private val viewModel: UsersViewModel by viewModels {
        UsersViewModelFactory(
            BemRepository.getInstance(
                BemDatabase.getInstance(requireContext()).bemDao(),
                ApiConfig.getApiService(token = null)
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.isLoading.observe(viewLifecycleOwner){isLoading ->
            binding.progressBarrecyclerEvent.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.events.observe(viewLifecycleOwner){events ->
            eventAdapter = EventAdapter(events) { event ->
                val intent = Intent(requireContext(), EventsDetailActivity::class.java)
                intent.putExtra("EVENT_DATA", event)
                startActivity(intent)
            }
            binding.recyclerEvent.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = eventAdapter
            }
        }
        viewModel.fetchEvents()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}