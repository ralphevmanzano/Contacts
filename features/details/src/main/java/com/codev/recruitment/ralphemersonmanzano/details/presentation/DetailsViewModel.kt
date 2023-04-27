package com.codev.recruitment.ralphemersonmanzano.details.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codev.recruitment.ralphemersonmanzano.data.usecase.GetContactByIdUseCase
import com.codev.recruitment.ralphemersonmanzano.mylibrary.model.Contact
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getContactByIdUseCase: GetContactByIdUseCase
) : ViewModel() {

    private val _contact = MutableLiveData<Contact>()
    val contact: LiveData<Contact> = _contact

    fun getContact(id: Int) {
        viewModelScope.launch {
            val contact = getContactByIdUseCase(id)
            _contact.postValue(contact)
        }
    }
}