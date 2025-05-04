package com.materialdesign.verification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.materialdesign.verification.databinding.FragmentCompleteBinding

class CompleteFragment : Fragment() {
    private lateinit var binding: FragmentCompleteBinding

    // User data from SafeArgs
    private var emailAddress: String = ""
    private var name: String = ""
    private var surname: String = ""
    private var gender: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Get all arguments from SafeArgs
        val args = CompleteFragmentArgs.fromBundle(requireArguments())
        emailAddress = args.emailAdress  // Note: Using the name from navigation graph, even with typo
        name = args.name
        surname = args.surname
        gender = args.gender
        // Note: We also have args.firstPassword, args.repeatPassword, args.verificationCode
        // but not displaying those for security reasons
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCompleteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Create a text view to display all user data
        val userDataTextView = TextView(requireContext()).apply {
            text = """
                Registration Complete!
                
                Email: $emailAddress
                Name: $name
                Surname: $surname
                Gender: $gender
            """.trimIndent()

            textSize = 16f
            setPadding(16, 16, 16, 16)
        }

        // Add the text view to the container
        binding.resultContainer.removeAllViews()
        binding.resultContainer.addView(userDataTextView)

        // Setup save button action
        binding.saveButton.setOnClickListener {
            // Here you would typically save the data to a database or shared preferences
            // For now, just show a message and go back to the start
            findNavController().navigate(R.id.registrationFragment)
        }
    }
}