<<<<<<< HEAD

package com.example.fundlink.ui.student

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fundlink.R

class OpportunitiesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(
            R.layout.fragment_opportunities,
            container,
            false
        )
=======
package com.example.fundlink.ui.student

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fundlink.R
import com.example.fundlink.data.repository.StudentFundingRepository
import com.example.fundlink.databinding.FragmentOpportunitiesBinding
import com.example.fundlink.ui.adapters.OpportunityAdapter
import kotlinx.coroutines.launch

class OpportunitiesFragment : Fragment(R.layout.fragment_opportunities) {
    private var _binding: FragmentOpportunitiesBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentOpportunitiesBinding.bind(view)
        val adapter = OpportunityAdapter { item ->
            findNavController().navigate(
                R.id.action_opportunitiesFragment_to_opportunityDetailsFragment,
                bundleOf(
                    "opportunityId" to item.id,
                    "title" to item.title,
                    "description" to item.description,
                    "amount" to item.amount,
                    "deadline" to item.deadline,
                    "eligibility" to item.eligibility
                )
            )
        }
        binding.recyclerOpportunities.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerOpportunities.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            val list = StudentFundingRepository(requireContext()).opportunities()
            _binding?.let {
                adapter.submit(list)
                it.tvEmptyOpportunities.visibility = if (list.isEmpty()) View.VISIBLE else View.GONE
                it.progressOpportunities.visibility = View.GONE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
>>>>>>> origin/main
    }
}
