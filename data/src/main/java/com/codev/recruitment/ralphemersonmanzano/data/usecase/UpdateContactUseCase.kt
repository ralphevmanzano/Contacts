package com.codev.recruitment.ralphemersonmanzano.data.usecase

import com.codev.recruitment.ralphemersonmanzano.mylibrary.model.Contact
import com.codev.recruitment.ralphemersonmanzano.mylibrary.repository.ContactsRepository
import javax.inject.Inject

class UpdateContactUseCase @Inject constructor(private val repo: ContactsRepository) {
    suspend operator fun invoke(contact: Contact) = repo.updateContact(contact)
}