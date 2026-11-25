package com.example.fundlink.ui.student

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fundlink.R
import com.example.fundlink.data.repository.StudentFundingRepository
import com.example.fundlink.databinding.FragmentMyApplicationsBinding
import com.example.fundlink.ui.adapters.ApplicationAdapter
import kotlinx.coroutines.launch

class MyApplicationsFragment : Fragment(R.layout.fragment_my_applications) {
    private var _binding: FragmentMyApplicationsBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentMyApplicationsBinding.bind(view)
        val adapter = ApplicationAdapter()
        binding.recyclerMyApplications.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerMyApplications.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            val list = StudentFundingRepository(requireContext()).myApplications()
            _binding?.let {
                adapter.submit(list)
                it.tvEmptyApplications.visibility = if (list.isEmpty()) View.VISIBLE else View.GONE
                it.progressApplications.visibility = View.GONE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
