package com.codev.recruitment.ralphemersonmanzano.data.datasource.local

import com.codev.recruitment.ralphemersonmanzano.data.datasource.local.dao.ContactsDao
import com.codev.recruitment.ralphemersonmanzano.data.datasource.local.entity.ContactEntity
import com.codev.recruitment.ralphemersonmanzano.mylibrary.datasource.local.ContactsLocalDataSource
import com.codev.recruitment.ralphemersonmanzano.mylibrary.model.Contact
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class ContactsLocalDataSourceImpl (
    private val contactsDao: ContactsDao,
    private val dispatcher: CoroutineDispatcher
) : ContactsLocalDataSource {
    override fun getContacts(): Flow<List<Contact>> {
        return contactsDao.getContacts().flowOn(dispatcher)
            .map { contactsEntity -> contactsEntity.map { it.toContact() } }
    }

    override fun getFavorites(): Flow<List<Contact>> {
        return contactsDao.getFavorites().flowOn(dispatcher)
            .map { contactsEntity -> contactsEntity.map { it.toContact() } }
    }

    override suspend fun getContactById(id: Long): Contact = withContext(dispatcher) {
        return@withContext contactsDao.getContactById(id).toContact()
    }

    override suspend fun addContact(contact: Contact): Long = withContext(dispatcher) {
        return@withContext contactsDao.addContact(
            ContactEntity(
                firstName = contact.firstName,
                lastName = contact.lastName,
                phoneNumber = contact.phoneNumber,
                isFavorite = contact.isFavorite,
                avatarColor = contact.avatarColor
            )
        )
    }

    override suspend fun updateContact(contact: Contact) = withContext(dispatcher) {
        contactsDao.updateContact(
            ContactEntity(
                id = contact.id ?: 0,
                firstName = contact.firstName,
                lastName = contact.lastName,
                phoneNumber = contact.phoneNumber,
                isFavorite = contact.isFavorite,
                avatarColor = contact.avatarColor
            )
        )
    }

    override suspend fun deleteContact(id: Long) = withContext(dispatcher) {
        contactsDao.deleteContact(id)
    }
}