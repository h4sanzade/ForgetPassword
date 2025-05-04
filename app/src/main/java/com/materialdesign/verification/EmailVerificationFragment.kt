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


        val args = EmailVerificationFragmentArgs.fromBundle(requireArguments())
        emailAddress = args.emailAdress


        binding.continueButton.setOnClickListener {
            val enteredCode = binding.emailInputEditText.text.toString()


            if (enteredCode.isEmpty()) {
                Toast.makeText(context, "Please enter a verification code", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            findNavController().navigate(
                EmailVerificationFragmentDirections.actionEmailVerificationFragmentToCreateNewPassword(
                    emailAdress = emailAddress,
                    verificationCode = enteredCode
                )
            )
        }
    }
}