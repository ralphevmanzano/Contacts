package com.codev.recruitment.ralphemersonmanzano.home.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.codev.recruitment.ralphemersonmanzano.home.presentation.holder.ContactsViewHolder
import com.codev.recruitment.ralphemersonmanzano.mylibrary.model.Contact

class ContactsAdapter : ListAdapter<Contact, ContactsViewHolder>(ContactsDiffCallback()) {

    var onItemClick: ((Contact) -> Unit)? = null

    class ContactsDiffCallback : DiffUtil.ItemCallback<Contact>() {
        override fun areItemsTheSame(oldItem: Contact, newItem: Contact) =
            oldItem.phoneNumber == newItem.phoneNumber

        override fun areContentsTheSame(oldItem: Contact, newItem: Contact) = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        return ContactsViewHolder.create(parent, onItemClick)
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}