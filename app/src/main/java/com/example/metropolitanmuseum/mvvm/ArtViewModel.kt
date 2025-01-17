package com.example.metropolitanmuseum.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.metropolitanmuseum.dagger.App
import com.example.metropolitanmuseum.utils.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ArtViewModel(private val repository: ArtRepository) : ViewModel() {
    private val _state = MutableStateFlow<State>(State.Loading)
    val state = _state.asStateFlow()
    var artObject: ArtObject? = null
    private val id = 45734

    init {
        getArtObject()
    }

    private fun getArtObject() {
        viewModelScope.launch(Dispatchers.IO) {
            val request = repository.getArtObject(id)
            if (request != null) {
                if (request.isSuccessful) {
                    artObject = request.body()
                    _state.value = State.Success
                } else if (request.code() == 404) {
                    _state.value = State.Error("Объект не найден.")
                } else {
                    _state.value = State.Error("Произошла ошибка.")
                }
            } else {
                _state.value = State.Error("Проверьте подключение к Интернет.")
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                ArtViewModel(App.component.getArtRepository())
            }
        }
    }
}