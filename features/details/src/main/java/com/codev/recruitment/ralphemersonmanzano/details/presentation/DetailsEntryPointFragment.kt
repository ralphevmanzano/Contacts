package com.codev.recruitment.ralphemersonmanzano.details.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.codev.recruitment.ralphemersonmanzano.details.R
import com.codev.recruitment.ralphemersonmanzano.details.databinding.FragmentDetailsEntrypointBinding

class DetailsEntryPointFragment: Fragment() {

    private lateinit var binding: FragmentDetailsEntrypointBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsEntrypointBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val contactId = arguments?.getLong(CONTACT_ID) ?: -1
        val startDestination = arguments?.getInt(START_DESTINATION_ID)
        if (startDestination == R.id.fragment_details) {
            findNavController().navigate(DetailsEntryPointFragmentDirections.actionToDetails(contactId))
        } else {
            findNavController().navigate(
                DetailsEntryPointFragmentDirections.actionToForm(null)
            )
        }
    }

    companion object {
        const val START_DESTINATION_ID = "startDestinationId"
        const val CONTACT_ID = "contactId"
    }
}