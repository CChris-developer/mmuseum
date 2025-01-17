package com.example.metropolitanmuseum.utils

sealed class State {
    object Success : State()
    object Loading: State()
    data class Error(val errorMessage: String?) : State()
}