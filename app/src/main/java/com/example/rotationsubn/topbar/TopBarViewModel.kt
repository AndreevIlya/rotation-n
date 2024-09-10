package com.example.rotationsubn.topbar

import kotlinx.coroutines.flow.Flow

interface TopBarViewModel {
    val dimension: Flow<Int>
    fun updateDimension(dim: Int)
}
