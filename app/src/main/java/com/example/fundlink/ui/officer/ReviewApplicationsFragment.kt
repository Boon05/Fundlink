package com.example.fundlink.ui.officer

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fundlink.R
import com.example.fundlink.data.repository.OfficerRepository
import com.example.fundlink.ui.adapters.ReviewAdapter
import kotlinx.coroutines.launch

class ReviewApplicationsFragment :
    Fragment(R.layout.fragment_review_applications) {

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView =
            view.findViewById<RecyclerView>(R.id.rvApplications)

        recyclerView.layoutManager =
            LinearLayoutManager(requireContext())

        val repository =
            OfficerRepository(requireContext())

        lifecycleScope.launch {

            val applications =
                repository.getApplications()

            recyclerView.adapter =
                ReviewAdapter(applications)
        }
    }
}