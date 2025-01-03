package com.freelanceror.ipaas.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.freelanceror.ipaas.model.NasaPicture
import com.freelanceror.ipaas.repository.NasaRepository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val repository = NasaRepository()
    val pictures = MutableLiveData<List<NasaPicture>>()
    val error = MutableLiveData<String>()

    fun fetchPictures(apiKey: String) {
        viewModelScope.launch {
            try {
                pictures.value = repository.getPictures(apiKey)
            } catch (e: Exception) {
                error.value = e.message
            }
        }
    }
}