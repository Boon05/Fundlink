package com.example.fundlink.ui.officer

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.fundlink.R
import com.example.fundlink.data.repository.OfficerRepository
import kotlinx.coroutines.launch

class OfficerDashboardFragment :
    Fragment(R.layout.fragment_officer_dashboard) {

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        val repo = OfficerRepository(requireContext())

        val opportunities =
            view.findViewById<TextView>(R.id.txtOpportunities)

        val pending =
            view.findViewById<TextView>(R.id.txtPending)

        lifecycleScope.launch {

            opportunities.text =
                "Total Opportunities: ${repo.getOpportunityCount()}"

            pending.text =
                "Pending Applications: ${repo.getPendingApplicationsCount()}"
        }
    }
}