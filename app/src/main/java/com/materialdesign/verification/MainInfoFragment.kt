package com.materialdesign.verification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.materialdesign.verification.databinding.FragmentMainInfoBinding

class MainInfoFragment : Fragment() {
    private lateinit var binding: FragmentMainInfoBinding

    // User data from SafeArgs
    private var emailAddress: String = ""
    private var verificationCode: String = ""
    private var firstPassword: String = ""
    private var repeatPassword: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Get arguments from SafeArgs
        val args = MainInfoFragmentArgs.fromBundle(requireArguments())
        emailAddress = args.emailAdress
        verificationCode = args.verificationCode
        firstPassword = args.firstPassword
        repeatPassword = args.repeatPassword
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMainInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up gender selection
        binding.genderEditText.setOnClickListener {
            showGenderSelectionDialog()
        }

        // Set up continue button
        binding.continueButton.setOnClickListener {
            val name = binding.nameInputEditText.text.toString()
            val surname = binding.surnameEditText.text.toString()
            val gender = binding.genderEditText.text.toString()

            // Validate input fields
            if (name.isEmpty() || surname.isEmpty() || gender.isEmpty()) {
                Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Navigate to Complete fragment
            findNavController().navigate(
                MainInfoFragmentDirections.actionMainInfoFragmentToCompleteFragment(
                    emailAdress = emailAddress,
                    verificationCode = verificationCode,
                    firstPassword = firstPassword,
                    repeatPassword = repeatPassword,
                    name = name,
                    surname = surname,
                    gender = gender
                )
            )
        }
    }

    private fun showGenderSelectionDialog() {
        // Create and show a dialog for gender selection
        val genders = arrayOf("Male", "Female", "Other")

        androidx.appcompat.app.AlertDialog.Builder(requireContext())
            .setTitle("Select Gender")
            .setItems(genders) { dialog, which ->
                binding.genderEditText.setText(genders[which])
                dialog.dismiss()
            }
            .show()
    }
}