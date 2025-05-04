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


    private var emailAddress: String = ""
    private var name: String = ""
    private var surname: String = ""
    private var gender: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val args = CompleteFragmentArgs.fromBundle(requireArguments())
        emailAddress = args.emailAdress
        name = args.name
        surname = args.surname
        gender = args.gender

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


        val userDataTextView = TextView(requireContext()).apply {
            text = """
                Registration Complete!
                
                Email: $emailAddress
                Name: $name
                Surname: $surname
                Gender: $gender
            """.trimIndent()


        }


        binding.resultContainer.removeAllViews()
        binding.resultContainer.addView(userDataTextView)


        binding.saveButton.setOnClickListener {

            findNavController().navigate(R.id.registrationFragment)
        }
    }
}