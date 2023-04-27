package com.codev.recruitment.ralphemersonmanzano.details.presentation.form

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codev.recruitment.ralphemersonmanzano.data.usecase.AddContactUseCase
import com.codev.recruitment.ralphemersonmanzano.data.usecase.UpdateContactUseCase
import com.codev.recruitment.ralphemersonmanzano.mylibrary.model.Contact
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FormViewModel @Inject constructor(
    private val addContactUseCase: AddContactUseCase,
    private val updateContactUseCase: UpdateContactUseCase
) : ViewModel() {

    private val _firstName = MutableStateFlow("")
    private val _lastName = MutableStateFlow("")
    private val _phone = MutableStateFlow("")

    private val _isFormValid = MutableLiveData<Boolean>()
    val isFormValid: LiveData<Boolean> = _isFormValid

    private val _isSaveSuccessful = MutableLiveData<Pair<Boolean, Long>>()
    val isSaveSuccessful: LiveData<Pair<Boolean, Long>> = _isSaveSuccessful

    fun setFirstName(name: String) {
        _firstName.value = name
    }

    fun setLastName(name: String) {
        _lastName.value = name
    }

    fun setPhone(phone: String) {
        _phone.value = phone
    }

    init {
        viewModelScope.launch {
            combine(_firstName, _lastName, _phone) { firstName, lastName, phone ->
                return@combine firstName.isNotBlank() && lastName.isNotBlank() && phone.isNotBlank()
            }.collect { isValid ->
                _isFormValid.postValue(isValid)
            }
        }
    }

    fun addContact(color: Int) {
        val contact = Contact(
            firstName = _firstName.value,
            lastName = _lastName.value,
            phoneNumber = _phone.value,
            isFavorite = false,
            avatarColor = color
        )
        viewModelScope.launch {
            val id = addContactUseCase(contact)
            _isSaveSuccessful.postValue(Pair(true, id))
        }
    }

    fun updateContact(contact: Contact) {
        val newContact = contact.copy(
            firstName = _firstName.value,
            lastName = _lastName.value,
            phoneNumber = _phone.value
        )
        viewModelScope.launch {
            updateContactUseCase(newContact)
            _isSaveSuccessful.postValue(Pair(true, -1L))
        }
    }
}