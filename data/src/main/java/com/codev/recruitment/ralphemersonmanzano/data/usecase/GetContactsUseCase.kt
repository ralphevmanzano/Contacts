package com.codev.recruitment.ralphemersonmanzano.data.usecase

import com.codev.recruitment.ralphemersonmanzano.mylibrary.repository.ContactsRepository
import javax.inject.Inject

class GetContactsUseCase @Inject constructor(private val repo: ContactsRepository) {
    operator fun invoke() = repo.getContacts()
}