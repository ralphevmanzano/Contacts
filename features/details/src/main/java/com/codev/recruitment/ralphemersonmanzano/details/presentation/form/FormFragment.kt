package com.codev.recruitment.ralphemersonmanzano.details.presentation.form

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.codev.recruitment.ralphemersonmanzano.details.R
import com.codev.recruitment.ralphemersonmanzano.details.databinding.FragmentFormBinding
import com.codev.recruitment.ralphemersonmanzano.mylibrary.model.Contact
import com.codev.recruitment.ralphemersonmanzano.shared.R.array
import com.codev.recruitment.ralphemersonmanzano.shared.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random

@AndroidEntryPoint
class FormFragment: Fragment() {

    private val viewModel by viewModels<FormViewModel>()
    private val args by navArgs<FormFragmentArgs>()

    private lateinit var binding: FragmentFormBinding

    private var contact: Contact? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFormBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        contact = args.contact

        setupUI()
        setupObservers()
    }

    private fun setupUI() = with(binding) {
        if (contact != null) toolbar.title = getString(R.string.edit_contact)
        else toolbar.title = getString(R.string.create_contact)

        toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        firstNameEditText.addTextChangedListener { viewModel.setFirstName(it.toString()) }
        lastNameEditText.addTextChangedListener { viewModel.setLastName(it.toString()) }
        phoneEditText.addTextChangedListener { viewModel.setPhone(it.toString()) }

        saveButton.setOnClickListener {
            if (contact == null) {
                val androidColors = resources.getIntArray(array.avatarColors)
                val randomColor = androidColors[Random.nextInt(androidColors.size)]
                viewModel.addContact(randomColor)
            } else {
                viewModel.updateContact(contact!!)
            }
        }

        if (contact != null) {
            firstNameEditText.setText(contact!!.firstName)
            lastNameEditText.setText(contact!!.lastName)
            phoneEditText.setText(contact!!.phoneNumber)
        }
    }

    private fun setupObservers() = with(viewModel) {
        isFormValid.observe(viewLifecycleOwner) { isValid ->
            binding.saveButton.isEnabled = isValid
        }

        isSaveSuccessful.observe(viewLifecycleOwner) { pair ->
            if (pair.first) {
                if (pair.second != -1L) {
                    showToast(getString(R.string.contact_saved))
                    findNavController().navigate(FormFragmentDirections.actionToDetails(pair.second))
                } else {
                    showToast(getString(R.string.contact_updated))
                    findNavController().popBackStack()
                }
            }
        }
    }
}