package com.example.fundlink.ui.auth

<<<<<<< HEAD
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.fundlink.R
import com.example.fundlink.data.repository.AuthRepository
import com.example.fundlink.databinding.FragmentRegisterBinding
import kotlinx.coroutines.launch

class RegisterFragment : Fragment(R.layout.fragment_register) {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentRegisterBinding.bind(view)

        val repo = AuthRepository(requireContext())

        // Register button
        binding.btnRegister.setOnClickListener {

            val name = binding.etFullName.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val pass = binding.etPassword.text.toString()
            val confirm = binding.etConfirmPassword.text.toString()

            // Validate all fields
            if (
                name.length < 2 ||
                !Patterns.EMAIL_ADDRESS.matcher(email).matches() ||
                pass.length < 8 ||
                pass != confirm
            ) {

                Toast.makeText(
                    requireContext(),
                    "Check all fields and password confirmation",
                    Toast.LENGTH_LONG
                ).show()

                return@setOnClickListener
            }

            // Create account
            viewLifecycleOwner.lifecycleScope.launch {

                binding.btnRegister.isEnabled = false

                repo.register(name, email, pass)
                    .onSuccess {

                        Toast.makeText(
                            requireContext(),
                            "Account created. Please sign in.",
                            Toast.LENGTH_LONG
                        ).show()

                        findNavController().popBackStack()
                    }
                    .onFailure {

                        Toast.makeText(
                            requireContext(),
                            it.message ?: "Registration failed",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                _binding?.btnRegister?.isEnabled = true
            }
        }

        // Return to login
        binding.tvGoToLogin.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
=======
import androidx.fragment.app.Fragment

class RegisterFragment : Fragment()
>>>>>>> origin/main
