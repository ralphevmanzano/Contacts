package com.codev.recruitment.ralphemersonmanzano.favorites.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codev.recruitment.ralphemersonmanzano.data.usecase.GetFavoritesUseCase
import com.codev.recruitment.ralphemersonmanzano.mylibrary.model.Contact
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoritesUseCase: GetFavoritesUseCase
): ViewModel() {

    private val _favorites = MutableLiveData<List<Contact>>()
    val favorites: LiveData<List<Contact>> = _favorites

    init {
        viewModelScope.launch {
            getFavoritesUseCase().collect {
                _favorites.postValue(it)
            }
        }
    }
}