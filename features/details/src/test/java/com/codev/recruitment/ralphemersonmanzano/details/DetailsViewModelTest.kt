package com.codev.recruitment.ralphemersonmanzano.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.codev.recruitment.ralphemersonmanzano.data.usecase.DeleteContactUseCase
import com.codev.recruitment.ralphemersonmanzano.data.usecase.GetContactByIdUseCase
import com.codev.recruitment.ralphemersonmanzano.data.usecase.UpdateContactUseCase
import com.codev.recruitment.ralphemersonmanzano.details.presentation.details.DetailsViewModel
import com.codev.recruitment.ralphemersonmanzano.mylibrary.model.Contact
import com.codev.recruitment.ralphemersonmanzano.testutils.MainCoroutineScopeRule
import com.codev.recruitment.ralphemersonmanzano.testutils.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DetailsViewModelTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    @Mock
    private lateinit var getContactByIdUseCase: GetContactByIdUseCase

    @Mock
    private lateinit var updateContactUseCase: UpdateContactUseCase

    @Mock
    private lateinit var deleteContactUseCase: DeleteContactUseCase

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        // do nothing
    }

    @Test
    fun `when getContact should update liveData`() {
        coroutineScope.runBlockingTest {
            // Given
            val id = 0L
            val contact = Contact(
                0L,
                "James",
                "Bond",
                "77777",
                false,
                0
            )
            `when`(getContactByIdUseCase(id)).thenReturn(contact)

            // When
            val viewModel = DetailsViewModel(getContactByIdUseCase, updateContactUseCase, deleteContactUseCase)
            viewModel.getContact(id)

            // Then
            val resultContact = viewModel.contact.getOrAwaitValue()
            val resultIsFavorite = viewModel.isFavorite.getOrAwaitValue()
            assertEquals(0L, resultContact.id)
            assertEquals("James", resultContact.firstName)
            assertEquals("Bond", resultContact.lastName)
            assertEquals("77777", resultContact.phoneNumber)
            assertEquals(false, resultContact.isFavorite)
            assertEquals(0, resultContact.avatarColor)
            assertEquals(false, resultIsFavorite)
        }
    }

    @Test
    fun `when favoriteContact should invoke useCases and update liveData`() {
        coroutineScope.runBlockingTest {
            // Given
            val contact = Contact(
                0L,
                "James",
                "Bond",
                "77777",
                false,
                0
            )
            `when`(getContactByIdUseCase(0L)).thenReturn(contact.copy(isFavorite = true))

            // When
            val viewModel = DetailsViewModel(getContactByIdUseCase, updateContactUseCase, deleteContactUseCase)
            viewModel.favoriteContact(contact)

            // Then
            val result = viewModel.isFavorite.getOrAwaitValue()
            assertEquals(true, result)
            verify(updateContactUseCase, times(1)).invoke(contact.copy(isFavorite = true))
            verify(getContactByIdUseCase, times(1)).invoke(0L)
        }
    }

    @Test
    fun `when deleteContact should call useCase and update liveData`() {
        coroutineScope.runBlockingTest {
            // Given
            val id = 0L

            // When
            val viewModel = DetailsViewModel(getContactByIdUseCase, updateContactUseCase, deleteContactUseCase)
            viewModel.deleteContact(id)

            // Then
            val result = viewModel.isDeleteCompleted.getOrAwaitValue()
            assertTrue(result == true)
            verify(deleteContactUseCase, times(1)).invoke(id)
        }
    }
}