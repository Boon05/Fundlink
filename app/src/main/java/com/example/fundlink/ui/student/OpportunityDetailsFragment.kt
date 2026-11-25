package com.example.fundlink.ui.student

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.fundlink.R
import com.example.fundlink.data.model.FundingOpportunity
import com.example.fundlink.data.repository.AuthRepository
import com.example.fundlink.data.repository.StudentFundingRepository
import com.example.fundlink.databinding.FragmentOpportunityDetailsBinding
import kotlinx.coroutines.launch

class OpportunityDetailsFragment : Fragment(R.layout.fragment_opportunity_details) {
    private var _binding: FragmentOpportunityDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentOpportunityDetailsBinding.bind(view)
        val args = requireArguments()
        val item = FundingOpportunity(
            id = args.getLong("opportunityId"),
            title = args.getString("title").orEmpty(),
            description = args.getString("description").orEmpty(),
            amount = args.getDouble("amount"),
            deadline = args.getString("deadline").orEmpty(),
            eligibility = args.getString("eligibility").orEmpty()
        )

        binding.tvDetailTitle.text = item.title
        binding.tvDetailDescription.text = item.description
        binding.tvDetailAmount.text = "Amount: KES %,.2f".format(item.amount)
        binding.tvDetailDeadline.text = "Deadline: ${item.deadline}"
        binding.tvDetailEligibility.text = "Eligibility: ${item.eligibility}"

        val auth = AuthRepository(requireContext())
        val repo = StudentFundingRepository(requireContext())

        binding.btnSubmitApplication.setOnClickListener {
            val amount = binding.etRequestedAmount.text.toString().toDoubleOrNull()
            val statement = binding.etStatement.text.toString().trim()
            if (amount == null || amount <= 0 || statement.isBlank()) {
                Toast.makeText(
                    requireContext(),
                    "Enter a valid amount and statement",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            viewLifecycleOwner.lifecycleScope.launch {
                binding.btnSubmitApplication.isEnabled = false
                val profile = auth.currentUser()
                if (profile == null) {
                    Toast.makeText(requireContext(), "Sign in again", Toast.LENGTH_LONG).show()
                    _binding?.btnSubmitApplication?.isEnabled = true
                    return@launch
                }
                val studentName = profile.fullName.ifBlank { "Student" }
                val result = repo.submit(item, studentName, amount, statement)
                result.onSuccess {
                    Toast.makeText(requireContext(), "Application submitted successfully", Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack()
                }.onFailure { err ->
                    Toast.makeText(requireContext(), err.message ?: "Submission failed", Toast.LENGTH_LONG).show()
                    _binding?.btnSubmitApplication?.isEnabled = true
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
