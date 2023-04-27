package com.codev.recruitment.ralphemersonmanzano.details.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codev.recruitment.ralphemersonmanzano.data.usecase.DeleteContactUseCase
import com.codev.recruitment.ralphemersonmanzano.data.usecase.GetContactByIdUseCase
import com.codev.recruitment.ralphemersonmanzano.data.usecase.UpdateContactUseCase
import com.codev.recruitment.ralphemersonmanzano.mylibrary.model.Contact
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getContactByIdUseCase: GetContactByIdUseCase,
    private val updateContactUseCase: UpdateContactUseCase,
    private val deleteContactUseCase: DeleteContactUseCase
) : ViewModel() {

    private val _contact = MutableLiveData<Contact>()
    val contact: LiveData<Contact> = _contact

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> = _isFavorite

    private val _isDeleteCompleted = MutableLiveData<Boolean>()
    val isDeleteCompleted: LiveData<Boolean> = _isDeleteCompleted

    fun getContact(id: Long) {
        viewModelScope.launch {
            val contact = getContactByIdUseCase(id)
            _contact.postValue(contact)
            _isFavorite.postValue(contact.isFavorite)
        }
    }

    fun favoriteContact(contact: Contact) {
        viewModelScope.launch {
            val isFavorite = _isFavorite.value ?: false
            updateContactUseCase(contact.copy(isFavorite = !isFavorite))
            val newContact = getContactByIdUseCase(contact.id!!)
            _isFavorite.postValue(newContact.isFavorite)
        }
    }

    fun deleteContact(id: Long) {
        viewModelScope.launch {
            deleteContactUseCase(id)
            _isDeleteCompleted.postValue(true)
        }
    }
}