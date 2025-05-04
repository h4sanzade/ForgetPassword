package com.materialdesign.verification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.materialdesign.verification.databinding.FragmentMainInfoBinding

class MainInfoFragment : Fragment() {
    private lateinit var binding: FragmentMainInfoBinding


    private var emailAddress: String = ""
    private var verificationCode: String = ""
    private var firstPassword: String = ""
    private var repeatPassword: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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


        binding.genderEditText.setOnClickListener {
            showGenderSelectionDialog()
        }


        binding.continueButton.setOnClickListener {
            val name = binding.nameInputEditText.text.toString()
            val surname = binding.surnameEditText.text.toString()
            val gender = binding.genderEditText.text.toString()


            if (name.isEmpty() || surname.isEmpty() || gender.isEmpty()) {
                Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

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

        val dialogView = layoutInflater.inflate(R.layout.popup_layout, null)


        val btnMale = dialogView.findViewById<Button>(R.id.btnMale)
        val btnFemale = dialogView.findViewById<Button>(R.id.btnFemale)


        MaterialAlertDialogBuilder(requireContext())
            .setView(dialogView)
            .setCancelable(true)
            .create()
            .apply {
                btnMale.setOnClickListener {
                    binding.genderEditText.setText("Male")
                    dismiss()
                }

                btnFemale.setOnClickListener {
                    binding.genderEditText.setText("Female")
                    dismiss()
                }

                show()
            }
    }
}