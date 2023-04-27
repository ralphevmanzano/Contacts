package com.codev.recruitment.ralphemersonmanzano.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codev.recruitment.ralphemersonmanzano.data.usecase.GetContactsUseCase
import com.codev.recruitment.ralphemersonmanzano.mylibrary.model.Contact
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getContactsUseCase: GetContactsUseCase): ViewModel() {

    private val _contacts = MutableLiveData<List<Contact>>()
    val contacts: LiveData<List<Contact>> = _contacts

    init {
        viewModelScope.launch {
            getContactsUseCase().collect {
                _contacts.postValue(it)
            }
        }
    }
}