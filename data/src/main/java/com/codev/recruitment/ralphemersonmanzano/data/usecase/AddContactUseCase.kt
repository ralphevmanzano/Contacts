package com.codev.recruitment.ralphemersonmanzano.data.usecase

import com.codev.recruitment.ralphemersonmanzano.mylibrary.model.Contact
import com.codev.recruitment.ralphemersonmanzano.mylibrary.repository.ContactsRepository
import javax.inject.Inject

// UseCase class per transaction which can also be useful for further data processing like mapping, etc.
// for this case we just return with the specific repo function
class AddContactUseCase @Inject constructor(private val repo: ContactsRepository) {
    suspend operator fun invoke(contact: Contact) = repo.addContact(contact)
}