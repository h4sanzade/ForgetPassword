package com.materialdesign.verification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.materialdesign.verification.databinding.FragmentRegistrationBinding

class RegistrationFragment : Fragment() {
        private lateinit var binding: FragmentRegistrationBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistrationBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.sendCodeButton.setOnClickListener {
            val email = binding.emailInputEditText.text.toString()
            findNavController().navigate(RegistrationFragmentDirections.actionRegistrationFragmentToEmailVerificationFragment(verificationCode = "123312",email))

        }

    }

}