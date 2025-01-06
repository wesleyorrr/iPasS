package com.freelanceror.ipaas.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.freelanceror.ipaas.model.NasaPicture
import com.freelanceror.ipaas.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {

    private val _pictures = MutableLiveData<List<NasaPicture>>()
    val pictures: LiveData<List<NasaPicture>> get() = _pictures

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun fetchPictures(apiKey: String) {
        viewModelScope.launch {
            _loading.value = true
            try {
                val response = withContext(Dispatchers.IO) {
                    RetrofitInstance.api.getPictures(apiKey)
                }
                _pictures.value = response
            } catch (e: Exception) {
                _error.value = "Erro ao carregar dados: ${e.message}"
            } finally {
                _loading.value = false
            }
        }
    }
}
