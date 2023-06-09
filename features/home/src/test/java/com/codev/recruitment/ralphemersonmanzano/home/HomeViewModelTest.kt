package com.codev.recruitment.ralphemersonmanzano.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.codev.recruitment.ralphemersonmanzano.data.usecase.GetContactsUseCase
import com.codev.recruitment.ralphemersonmanzano.home.presentation.HomeViewModel
import com.codev.recruitment.ralphemersonmanzano.mylibrary.model.Contact
import com.codev.recruitment.ralphemersonmanzano.mylibrary.repository.ContactsRepository
import com.codev.recruitment.ralphemersonmanzano.testutils.MainCoroutineScopeRule
import com.codev.recruitment.ralphemersonmanzano.testutils.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    @Mock
    private lateinit var repository: ContactsRepository

    private lateinit var getContactsUseCase: GetContactsUseCase

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        getContactsUseCase = GetContactsUseCase(repository)
    }

    @Test
    fun `when getContacts should update liveData`() {
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
            `when`(getContactsUseCase.invoke()).thenReturn(flowOf(contacts))

            // When
            val viewModel = HomeViewModel(getContactsUseCase)

            // Then
            val result = viewModel.contacts.getOrAwaitValue()
            assertEquals(0L, result.first().id)
            assertEquals("James", result.first().firstName)
            assertEquals("Bond", result.first().lastName)
            assertEquals("77777", result.first().phoneNumber)
            assertEquals(false, result.first().isFavorite)
            assertEquals(0, result.first().avatarColor)
        }
    }
}