package com.codev.recruitment.ralphemersonmanzano.favorites.presentation.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codev.recruitment.ralphemersonmanzano.favorites.databinding.ItemFavoriteBinding
import com.codev.recruitment.ralphemersonmanzano.mylibrary.model.Contact
import com.codev.recruitment.ralphemersonmanzano.shared.R

class FavoritesViewHolder(private val binding: ItemFavoriteBinding, private val onItemClick: ((Contact) -> Unit)?) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(contact: Contact) = with(binding) {
        val initials = "${contact.firstName.first()}${contact.lastName.first()}"
        val context = binding.root.context

        avatarImageView.setBackgroundColor(contact.avatarColor)
        initialTextView.text = initials
        nameTextView.text = context.getString(R.string.full_name, contact.firstName, contact.lastName)

        itemView.setOnClickListener { onItemClick?.invoke(contact) }
    }

    companion object {
        fun create(parent: ViewGroup, onItemClick: ((Contact) -> Unit)?): FavoritesViewHolder {
            val binding =
                ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return FavoritesViewHolder(binding, onItemClick)
        }
    }
}