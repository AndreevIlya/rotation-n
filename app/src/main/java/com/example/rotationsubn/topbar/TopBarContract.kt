package com.example.rotationsubn.topbar

import com.example.rotationsubn.mvi.Action
import com.example.rotationsubn.mvi.State

data class TopBarState(
    val dimensions: List<Int>,
    val currentDimension: Int
) : State

sealed interface TopBarAction : Action {
    data class UpdateDimension(val newDimension: Int) : TopBarAction
}
