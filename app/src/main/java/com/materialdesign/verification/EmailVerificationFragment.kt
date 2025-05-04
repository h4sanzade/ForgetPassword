package com.materialdesign.verification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.materialdesign.verification.databinding.FragmentEmailVerificationBinding

class EmailVerificationFragment : Fragment() {
    private lateinit var binding: FragmentEmailVerificationBinding
    private var verificationCode: String = ""
    private var emailAddress: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentEmailVerificationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get arguments from SafeArgs
        val args = EmailVerificationFragmentArgs.fromBundle(requireArguments())
        verificationCode = args.verificationCode
        emailAddress = args.emailAdress

        // Set up continue button
        binding.continueButton.setOnClickListener {
            val enteredCode = binding.emailInputEditText.text.toString()

            // Validate the verification code
            if (enteredCode == verificationCode) {
                // Navigate to Create New Password fragment
                findNavController().navigate(
                    EmailVerificationFragmentDirections.actionEmailVerificationFragmentToCreateNewPassword(
                        emailAdress = emailAddress,
                        verificationCode = verificationCode

                    )
                )
            } else {
                // Show error if code doesn't match
                Toast.makeText(context, "Invalid verification code", Toast.LENGTH_SHORT).show()
            }
        }
    }
}