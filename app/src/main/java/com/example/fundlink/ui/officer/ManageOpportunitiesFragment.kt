package com.example.fundlink.ui.officer

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.lifecycleScope
import com.example.fundlink.R
import com.example.fundlink.data.repository.OfficerRepository
import kotlinx.coroutines.launch

class ManageOpportunitiesFragment :
    Fragment(R.layout.fragment_manage_opportunities) {

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView =
            view.findViewById<RecyclerView>(R.id.rvOpportunities)

        recyclerView.layoutManager =
            LinearLayoutManager(requireContext())

        val repository = OfficerRepository(requireContext())

        lifecycleScope.launch {
            repository.getActiveOpportunities()
        }
    }
}