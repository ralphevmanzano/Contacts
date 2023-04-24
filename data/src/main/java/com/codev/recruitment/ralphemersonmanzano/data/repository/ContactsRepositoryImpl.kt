package com.codev.recruitment.ralphemersonmanzano.data.repository

import com.codev.recruitment.ralphemersonmanzano.mylibrary.datasource.local.ContactLocalDataSource
import com.codev.recruitment.ralphemersonmanzano.mylibrary.model.Contact
import com.codev.recruitment.ralphemersonmanzano.mylibrary.repository.ContactRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ContactsRepositoryImpl @Inject constructor(
    private val localDataSource: ContactLocalDataSource
): ContactRepository {

    override fun getContacts(): Flow<List<Contact>> {
        return localDataSource.getContacts()
    }

    override fun getFavorites(): Flow<List<Contact>> {
        return localDataSource.getFavorites()
    }

    override suspend fun getContactById(id: Int): Contact {
        return localDataSource.getContactById(id)
    }

    override suspend fun addContact(contact: Contact) {
        localDataSource.addContact(contact)
    }

    override suspend fun updateContact(contact: Contact) {
        localDataSource.updateContact(contact)
    }

    override suspend fun deleteContact(id: Int) {
        localDataSource.deleteContact(id)
    }
}