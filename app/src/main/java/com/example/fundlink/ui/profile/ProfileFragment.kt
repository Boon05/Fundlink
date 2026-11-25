package com.example.fundlink.ui.profile

<<<<<<< HEAD
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.fundlink.R
import com.example.fundlink.data.repository.AuthRepository
import com.example.fundlink.databinding.FragmentProfileBinding
import kotlinx.coroutines.launch

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentProfileBinding.bind(view)

        val repo = AuthRepository(requireContext())

        // Retrieve and display logged-in user
        viewLifecycleOwner.lifecycleScope.launch {

            repo.currentUser()?.let { u ->

                _binding?.apply {

                    tvProfileName.text = u.fullName

                    tvProfileEmail.text = u.email

                    tvProfileRole.text = "Role: ${u.role}"
                }
            }
        }

        // Logout button
        binding.btnLogout.setOnClickListener {

            repo.logout()

            findNavController().navigate(
                R.id.action_global_loginFragment
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
=======
import androidx.fragment.app.Fragment

class ProfileFragment : Fragment()
>>>>>>> origin/main
