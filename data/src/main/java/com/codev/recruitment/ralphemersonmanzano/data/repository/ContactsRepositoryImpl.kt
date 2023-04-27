package com.codev.recruitment.ralphemersonmanzano.data.repository

import com.codev.recruitment.ralphemersonmanzano.mylibrary.datasource.local.ContactsLocalDataSource
import com.codev.recruitment.ralphemersonmanzano.mylibrary.model.Contact
import com.codev.recruitment.ralphemersonmanzano.mylibrary.repository.ContactsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ContactsRepositoryImpl (
    private val localDataSource: ContactsLocalDataSource
): ContactsRepository {

    override fun getContacts(): Flow<List<Contact>> {
        return localDataSource.getContacts().map { contacts -> contacts }
    }

    override fun getFavorites(): Flow<List<Contact>> {
        return localDataSource.getFavorites()
    }

    override suspend fun getContactById(id: Long): Contact {
        return localDataSource.getContactById(id)
    }

    override suspend fun addContact(contact: Contact): Long {
        return localDataSource.addContact(contact)
    }

    override suspend fun updateContact(contact: Contact) {
        localDataSource.updateContact(contact)
    }

    override suspend fun deleteContact(id: Long) {
        localDataSource.deleteContact(id)
    }
}