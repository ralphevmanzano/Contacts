package com.codev.recruitment.ralphemersonmanzano.home.presentation.holder

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codev.recruitment.ralphemersonmanzano.home.databinding.ItemContactBinding
import com.codev.recruitment.ralphemersonmanzano.mylibrary.model.Contact

class ContactsViewHolder(private val binding: ItemContactBinding, private val onItemClick: ((Contact) -> Unit)?) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(contact: Contact) = with(binding) {
        val initials = "${contact.firstName.first()}${contact.lastName.first()}"

        avatarImageView.setBackgroundColor(contact.avatarColor)
        initialTextView.text = initials
        nameTextView.text = "${contact.firstName} ${contact.lastName}"

        itemView.setOnClickListener { onItemClick?.invoke(contact) }
    }

    companion object {
        fun create(parent: ViewGroup, onItemClick: ((Contact) -> Unit)?): ContactsViewHolder {
            val binding =
                ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ContactsViewHolder(binding, onItemClick)
        }
    }
}