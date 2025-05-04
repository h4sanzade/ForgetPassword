package com.materialdesign.verification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.materialdesign.verification.databinding.FragmentCreateNewPasswordBinding

class CreateNewPassword : Fragment() {
    private lateinit var binding: FragmentCreateNewPasswordBinding
    private var emailAddress: String = ""
    private var verificationCode: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCreateNewPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get arguments from SafeArgs
        val args = CreateNewPasswordArgs.fromBundle(requireArguments())
        emailAddress = args.emailAdress
        verificationCode = args.verificationCode

        // Set up continue button
        binding.continueButton.setOnClickListener {
            val firstPassword = binding.passwordInputEditText.text.toString()
            val repeatPassword = binding.repeatPasswordEditText.text.toString()

            // Validate passwords
            if (firstPassword.isEmpty() || repeatPassword.isEmpty()) {
                Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (firstPassword != repeatPassword) {
                Toast.makeText(context, "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Navigate to Main Info fragment
            findNavController().navigate(
                CreateNewPasswordDirections.actionCreateNewPasswordToMainInfoFragment(
                    emailAdress = emailAddress,
                    verificationCode = verificationCode,
                    firstPassword = firstPassword,
                    repeatPassword = repeatPassword
                )
            )
        }
    }
}