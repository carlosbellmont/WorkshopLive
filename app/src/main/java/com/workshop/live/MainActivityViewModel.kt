package com.workshop.live

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainActivityViewModel: ViewModel() {

    private val _uiState = MutableStateFlow<State>(State.Idle)
    val uiState : StateFlow<State> = _uiState.asStateFlow()

    sealed class State {
        data object Idle : State()
        data class Success(val characters: List<Character>) : State()
        data class Error(val error: String) : State()
    }

    init {
        val fakeList = listOf(
            Character("Personaje1", "180"),
            Character("Personaje2", "160")
        )

        _uiState.value = State.Success(fakeList)
    }
}