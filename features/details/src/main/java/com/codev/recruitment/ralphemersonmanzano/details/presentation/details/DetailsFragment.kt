package com.codev.recruitment.ralphemersonmanzano.details.presentation.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.codev.recruitment.ralphemersonmanzano.details.R
import com.codev.recruitment.ralphemersonmanzano.details.databinding.FragmentDetailsBinding
import com.codev.recruitment.ralphemersonmanzano.details.presentation.DetailsEntryPointFragment.Companion.CONTACT_ID
import com.codev.recruitment.ralphemersonmanzano.mylibrary.model.Contact
import com.codev.recruitment.ralphemersonmanzano.shared.R.*
import com.codev.recruitment.ralphemersonmanzano.shared.showToast
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment: Fragment() {

    private val viewModel by viewModels<DetailsViewModel>()
    private lateinit var binding: FragmentDetailsBinding

    private var contactId: Long? = null

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
            contactId = arguments?.getLong(CONTACT_ID)
            contactId?.let { viewModel.getContact(contactId!!) }
        }
        binding.toolbar.inflateMenu(R.menu.menu_details)
        setupObservers()
    }

    private fun setupObservers() = with(viewModel) {
        contact.observe(viewLifecycleOwner) { contact ->
            setupUI(contact)
        }

        isFavorite.observe(viewLifecycleOwner) { isFavorite ->
            updateFavoriteState(isFavorite)
        }

        isDeleteCompleted.observe(viewLifecycleOwner) { isDeleteCompleted ->
            if (isDeleteCompleted) {
                showToast(getString(R.string.contact_successfully_deleted))
                findNavController().popBackStack()
            }
        }
    }

    private fun setupUI(contact: Contact) = with(binding) {
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu_edit -> {
                    findNavController().navigate(DetailsFragmentDirections.actionToForm(contact))
                }
                R.id.menu_favorite -> {
                    viewModel.favoriteContact(contact)
                }
            }
            true
        }
        toolbar.setNavigationOnClickListener { findNavController().popBackStack() }

        val initials = "${contact.firstName.first()}${contact.lastName.first()}"

        avatarImageView.setBackgroundColor(contact.avatarColor)
        initialsTextView.text = initials
        nameTextView.text = getString(string.full_name, contact.firstName, contact.lastName)
        phoneTextView.text = contact.phoneNumber

        callButton.setOnClickListener { performCall(contact.phoneNumber) }
        textButton.setOnClickListener { performMessage(contact.phoneNumber) }
        deleteButton.setOnClickListener { showDeleteDialog() }
    }

    private fun updateFavoriteState(isFavorite: Boolean) = with(binding) {
        val drawableId = if (isFavorite) R.drawable.ic_favorite_filled else R.drawable.ic_favorite_unfilled
        toolbar.menu.findItem(R.id.menu_favorite).icon = ContextCompat.getDrawable(requireContext(), drawableId)
    }

    private fun performCall(phoneNumber: String) {
        val dialIntent = Intent(Intent.ACTION_DIAL)
        dialIntent.data = Uri.parse("tel:$phoneNumber")
        startActivity(dialIntent)
    }

    private fun performMessage(phoneNumber: String) {
        val messageIntent = Intent(Intent.ACTION_VIEW)
        messageIntent.data = Uri.parse("sms:$phoneNumber")
        startActivity(messageIntent)
    }

    private fun showDeleteDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.delete_contact))
            .setMessage(getString(R.string.this_contact_will_be_removed))
            .setNegativeButton(getString(R.string.cancel)) { _, _ ->
                // do nothing
            }
            .setPositiveButton(getString(R.string.delete)) { _, _ ->
                viewModel.deleteContact(contactId!!)
            }
            .show()
    }
}