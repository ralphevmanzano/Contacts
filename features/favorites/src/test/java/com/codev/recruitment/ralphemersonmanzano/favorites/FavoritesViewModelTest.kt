package com.codev.recruitment.ralphemersonmanzano.favorites

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.codev.recruitment.ralphemersonmanzano.data.usecase.GetFavoritesUseCase
import com.codev.recruitment.ralphemersonmanzano.favorites.presentation.FavoritesViewModel
import com.codev.recruitment.ralphemersonmanzano.mylibrary.model.Contact
import com.codev.recruitment.ralphemersonmanzano.mylibrary.repository.ContactsRepository
import com.codev.recruitment.ralphemersonmanzano.testutils.MainCoroutineScopeRule
import com.codev.recruitment.ralphemersonmanzano.testutils.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class FavoritesViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    @Mock
    private lateinit var repository: ContactsRepository

    private lateinit var getFavoritesUseCase: GetFavoritesUseCase

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        getFavoritesUseCase = GetFavoritesUseCase(repository)
    }

    @Test
    fun `when getFavorites should update liveData`() {
        coroutineScope.runBlockingTest {
            // Given
            val contacts = listOf(
                Contact(
                    0L,
                    "James",
                    "Bond",
                    "77777",
                    false,
                    0
                )
            )
            Mockito.`when`(getFavoritesUseCase.invoke()).thenReturn(flowOf(contacts))

            // When
            val viewModel = FavoritesViewModel(getFavoritesUseCase)

            // Then
            val result = viewModel.favorites.getOrAwaitValue()
            assertEquals(0L, result.first().id)
            assertEquals("James", result.first().firstName)
            assertEquals("Bond", result.first().lastName)
            assertEquals("77777", result.first().phoneNumber)
            assertEquals(false, result.first().isFavorite)
            assertEquals(0, result.first().avatarColor)
        }
    }
}