package com.codev.recruitment.ralphemersonmanzano.data.usecase

import com.codev.recruitment.ralphemersonmanzano.mylibrary.repository.ContactsRepository
import javax.inject.Inject

class GetContactByIdUseCase @Inject constructor(private val repo: ContactsRepository) {
    suspend operator fun invoke(id: Long) = repo.getContactById(id)
}