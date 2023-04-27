package com.codev.recruitment.ralphemersonmanzano.details.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.codev.recruitment.ralphemersonmanzano.details.R
import com.codev.recruitment.ralphemersonmanzano.details.databinding.FragmentDetailsBinding
import com.codev.recruitment.ralphemersonmanzano.details.presentation.DetailsEntryPointFragment.Companion.CONTACT_ID
import com.codev.recruitment.ralphemersonmanzano.mylibrary.model.Contact
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment: Fragment() {

    private val viewModel by viewModels<DetailsViewModel>()
    private lateinit var binding: FragmentDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (arguments?.containsKey(CONTACT_ID) == true) {
            val id = arguments?.getInt(CONTACT_ID)
            id?.let { viewModel.getContact(id) }
        }

        setupObservers()
    }

    private fun setupObservers() {
        viewModel.contact.observe(viewLifecycleOwner) {

        }
    }

    private fun setupUI(contact: Contact) = with(binding) {
        updateFavoriteState(contact.isFavorite)
        avatarImageView.setBackgroundColor(contact.avatarColor)
        nameTextView.text = getString(R.string.full_name, contact.firstName, contact.lastName)
        phoneTextView.text = contact.phoneNumber
    }

    private fun updateFavoriteState(isFavorite: Boolean) = with(binding) {
        val drawableId = if (isFavorite) R.drawable.ic_favorite_filled else R.drawable.ic_favorite_unfilled
        toolbar.menu.findItem(R.id.menu_favorite).icon = ContextCompat.getDrawable(requireContext(), drawableId)
    }
}