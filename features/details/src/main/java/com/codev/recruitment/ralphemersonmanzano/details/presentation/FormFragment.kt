package com.codev.recruitment.ralphemersonmanzano.details.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.codev.recruitment.ralphemersonmanzano.details.R
import com.codev.recruitment.ralphemersonmanzano.details.databinding.FragmentFormBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random

@AndroidEntryPoint
class FormFragment: Fragment() {

    private val viewModel by viewModels<FormViewModel>()

    private lateinit var binding: FragmentFormBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFormBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupUI()
        setupObservers()
    }

    private fun setupUI() = with(binding) {
        toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        firstNameEditText.addTextChangedListener { viewModel.setFirstName(it.toString()) }
        lastNameEditText.addTextChangedListener { viewModel.setLastName(it.toString()) }
        phoneEditText.addTextChangedListener { viewModel.setPhone(it.toString()) }

        saveButton.setOnClickListener {
            val androidColors = resources.getIntArray(R.array.avatarColors)
            val randomColor = androidColors[Random.nextInt(androidColors.size)]
            viewModel.addContact(randomColor)
        }
    }


    private fun setupObservers() {
        viewModel.isFormValid.observe(viewLifecycleOwner) { isValid ->
            binding.saveButton.isEnabled = isValid
        }
    }
}