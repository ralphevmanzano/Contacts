package com.codev.recruitment.ralphemersonmanzano.data.datasource.local

import com.codev.recruitment.ralphemersonmanzano.data.datasource.local.dao.ContactsDao
import com.codev.recruitment.ralphemersonmanzano.mylibrary.datasource.local.ContactLocalDataSource
import com.codev.recruitment.ralphemersonmanzano.mylibrary.model.Contact
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ContactsLocalDataSourceImpl @Inject constructor(
    private val contactsDao: ContactsDao,
    private val dispatcher: CoroutineDispatcher
): ContactLocalDataSource {
    override fun getContacts(): Flow<List<Contact>> {
        return contactsDao.getContacts().flowOn(dispatcher)
    }

    override fun getFavorites(): Flow<List<Contact>> {
        return contactsDao.getFavorites().flowOn(dispatcher)
    }

    override suspend fun getContactById(id: Int): Contact = withContext(dispatcher) {
        return@withContext contactsDao.getContactById(id)
    }

    override suspend fun addContact(contact: Contact) = withContext(dispatcher) {
        contactsDao.addContact(contact)
    }

    override suspend fun updateContact(contact: Contact) = withContext(dispatcher) {
        contactsDao.updateContact(contact)
    }

    override suspend fun deleteContact(id: Int) = withContext(dispatcher) {
        contactsDao.deleteContact(id)
    }
}