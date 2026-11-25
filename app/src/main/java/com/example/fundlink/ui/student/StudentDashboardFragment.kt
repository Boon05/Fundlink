package com.example.fundlink.ui.student

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.fundlink.R
import com.example.fundlink.data.repository.AuthRepository
import com.example.fundlink.data.repository.StudentFundingRepository
import com.example.fundlink.databinding.FragmentStudentDashboardBinding
import kotlinx.coroutines.launch

class StudentDashboardFragment : Fragment(R.layout.fragment_student_dashboard) {
    private var _binding: FragmentStudentDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentStudentDashboardBinding.bind(view)
        val auth = AuthRepository(requireContext())
        val repo = StudentFundingRepository(requireContext())

        viewLifecycleOwner.lifecycleScope.launch {
            val user = auth.currentUser()
            val opportunities = repo.opportunityCount()
            val applications = repo.applicationCount()
            _binding?.apply {
                tvWelcome.text = "Welcome, ${user?.fullName ?: "Student"}"
                tvOpportunityCount.text = "Open opportunities: $opportunities"
                tvApplicationCount.text = "My applications: $applications"
            }
        }

        binding.btnBrowseOpportunities.setOnClickListener {
            findNavController().navigate(R.id.action_studentDashboardFragment_to_opportunitiesFragment)
        }
        binding.btnMyApplications.setOnClickListener {
            findNavController().navigate(R.id.action_studentDashboardFragment_to_myApplicationsFragment)
        }
        binding.btnStudentProfile.setOnClickListener {
            findNavController().navigate(R.id.action_studentDashboardFragment_to_profileFragment)
        }
        binding.btnStudentLogout.setOnClickListener {
            auth.logout()
            findNavController().navigate(R.id.action_global_loginFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
