package com.example.rotationsubn.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

interface State
interface Action

abstract class MviViewModel<S : State, A : Action>(
    private val reducer: MviReducer<S, A>
) : ViewModel() {

    private val _state: MutableStateFlow<S> = MutableStateFlow(reducer.initialState)
    val state: StateFlow<S> = _state.asStateFlow()

    fun sendAction(action: A) {
        viewModelScope.launch {
            _state.emit(reducer.reduce(_state.value, action))
        }
    }

}

interface MviReducer<S : State, A : Action> {

    val initialState: S

    fun reduce(state: S, action: A): S

}
