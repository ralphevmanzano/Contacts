package com.codev.recruitment.ralphemersonmanzano.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.codev.recruitment.ralphemersonmanzano.data.usecase.AddContactUseCase
import com.codev.recruitment.ralphemersonmanzano.data.usecase.UpdateContactUseCase
import com.codev.recruitment.ralphemersonmanzano.details.presentation.form.FormViewModel
import com.codev.recruitment.ralphemersonmanzano.mylibrary.model.Contact
import com.codev.recruitment.ralphemersonmanzano.testutils.MainCoroutineScopeRule
import com.codev.recruitment.ralphemersonmanzano.testutils.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
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
class FormViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    @Mock
    private lateinit var addContactUseCase: AddContactUseCase

    @Mock
    private lateinit var updateContactUseCase: UpdateContactUseCase

    @Test
    fun `when firstName only input isValid is false`() {
        coroutineScope.runBlockingTest {

            // Given
            val firstName = "James"

            // When
            val viewModel = FormViewModel(addContactUseCase, updateContactUseCase)
            viewModel.setFirstName(firstName)

            // Then
            val result = viewModel.isFormValid.getOrAwaitValue()
            assertEquals(false, result)
        }
    }

    @Test
    fun `when firstName and lastName only input isValid is false`() {
        coroutineScope.runBlockingTest {
            // Given
            val firstName = "James"
            val lastName = "Bond"

            // When
            val viewModel = FormViewModel(addContactUseCase, updateContactUseCase)
            viewModel.setFirstName(firstName)
            viewModel.setLastName(lastName)

            // Then
            val result = viewModel.isFormValid.getOrAwaitValue()
            assertEquals(false, result)
        }
    }

    @Test
    fun `when add contact should call useCase and update liveData`() {
        coroutineScope.runBlockingTest {
            // Given
            val firstName = "James"
            val lastName = "Bond"
            val phone = "77777"
            val contact = Contact(
                firstName = firstName,
                lastName = lastName,
                phoneNumber = phone,
                isFavorite = false,
                avatarColor = 0
            )
            `when`(addContactUseCase.invoke(contact)).thenReturn(1L)


            // When
            val viewModel = FormViewModel(addContactUseCase, updateContactUseCase)
            viewModel.setFirstName(firstName)
            viewModel.setLastName(lastName)
            viewModel.setPhone(phone)
            viewModel.addContact(0)

            // Then
            val result = viewModel.isSaveSuccessful.getOrAwaitValue()
            assertEquals(true, result.first)
            assertEquals(1L, result.second)
            verify(addContactUseCase, times(1)).invoke(contact)
        }
    }

    @Test
    fun `when add update should call useCase and update liveData`() {
        coroutineScope.runBlockingTest {
            // Given
            val firstName = "James"
            val lastName = "Bond"
            val phone = "77777"
            val contact = Contact(
                firstName = firstName,
                lastName = lastName,
                phoneNumber = phone,
                isFavorite = false,
                avatarColor = 0
            )

            // When
            val viewModel = FormViewModel(addContactUseCase, updateContactUseCase)
            viewModel.setFirstName("Jamesss")
            viewModel.setLastName(lastName)
            viewModel.setPhone(phone)
            viewModel.updateContact(contact)

            // Then
            val result = viewModel.isSaveSuccessful.getOrAwaitValue()
            assertEquals(true, result.first)
            assertEquals(-1L, result.second)
            verify(updateContactUseCase, times(1)).invoke(
                contact.copy(firstName = "Jamesss",)
            )
        }
    }
}