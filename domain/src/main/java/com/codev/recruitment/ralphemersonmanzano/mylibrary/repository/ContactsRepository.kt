package com.codev.recruitment.ralphemersonmanzano.mylibrary.repository

import com.codev.recruitment.ralphemersonmanzano.mylibrary.model.Contact
import kotlinx.coroutines.flow.Flow

interface ContactsRepository {
    fun getContacts(): Flow<List<Contact>>
    fun getFavorites(): Flow<List<Contact>>
    suspend fun getContactById(id: Long): Contact
    suspend fun addContact(contact: Contact): Long
    suspend fun updateContact(contact: Contact)
    suspend fun deleteContact(id: Long)
}