package com.codev.recruitment.ralphemersonmanzano.data.usecase

import com.codev.recruitment.ralphemersonmanzano.mylibrary.repository.ContactsRepository
import javax.inject.Inject

class DeleteContactUseCase @Inject constructor(private val repo: ContactsRepository) {
    suspend operator fun invoke(id: Long) = repo.deleteContact(id)
}