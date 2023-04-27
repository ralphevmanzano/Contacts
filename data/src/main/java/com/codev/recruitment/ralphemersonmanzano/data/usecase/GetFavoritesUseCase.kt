package com.codev.recruitment.ralphemersonmanzano.data.usecase

import com.codev.recruitment.ralphemersonmanzano.mylibrary.repository.ContactsRepository
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(private val repo: ContactsRepository) {
    operator fun invoke() = repo.getFavorites()
}