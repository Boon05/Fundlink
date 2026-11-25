<<<<<<< HEAD

package com.example.fundlink.ui.auth

import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.fundlink.R
import com.example.fundlink.data.repository.AuthRepository
import com.example.fundlink.data.session.SessionManager
import com.example.fundlink.databinding.FragmentLoginBinding
import kotlinx.coroutines.launch

class LoginFragment : Fragment(R.layout.fragment_login) {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private lateinit var repo: AuthRepository

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentLoginBinding.bind(view)

        repo = AuthRepository(requireContext())

        val session = SessionManager(requireContext())

        // Check whether the user is already logged in
        if (session.isLoggedIn()) {
            route(session.role())
            return
        }

        // Login button
        binding.btnLogin.setOnClickListener {

            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString()

            // Validate input
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()
                || password.isBlank()
            ) {
                Toast.makeText(
                    requireContext(),
                    "Enter valid login details",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            // Authenticate user
            viewLifecycleOwner.lifecycleScope.launch {

                binding.btnLogin.isEnabled = false

                repo.login(email, password)
                    .onSuccess {
                        route(it.role)
                    }
                    .onFailure {

                        Toast.makeText(
                            requireContext(),
                            it.message ?: "Login failed",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                _binding?.btnLogin?.isEnabled = true
            }
        }

        // Navigate to registration
        binding.tvGoToRegister.setOnClickListener {

            findNavController().navigate(
                R.id.action_loginFragment_to_registerFragment
            )
        }
    }

    // Direct user to the correct dashboard
    private fun route(role: String?) {

        when (role) {

            "STUDENT" -> {
                findNavController().navigate(
                    R.id.action_loginFragment_to_studentDashboardFragment
                )
            }

            "OFFICER" -> {
                findNavController().navigate(
                    R.id.action_loginFragment_to_officerDashboardFragment
                )
            }

            else -> {
                Toast.makeText(
                    requireContext(),
                    "Invalid account role",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
=======
package com.example.fundlink.ui.auth

import androidx.fragment.app.Fragment

class LoginFragment : Fragment()
>>>>>>> origin/main
