package com.codev.recruitment.ralphemersonmanzano.favorites.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.codev.recruitment.ralphemersonmanzano.favorites.presentation.holder.FavoritesViewHolder
import com.codev.recruitment.ralphemersonmanzano.mylibrary.model.Contact

class FavoritesAdapter: ListAdapter<Contact, FavoritesViewHolder>(ContactsDiffCallback()) {

    var onItemClick: ((Contact) -> Unit)? = null

    class ContactsDiffCallback : DiffUtil.ItemCallback<Contact>() {
        override fun areItemsTheSame(oldItem: Contact, newItem: Contact) =
            oldItem.phoneNumber == newItem.phoneNumber

        override fun areContentsTheSame(oldItem: Contact, newItem: Contact) = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        return FavoritesViewHolder.create(parent, onItemClick)
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}