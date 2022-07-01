package com.example.rotation_n

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _dimension = MutableLiveData(3)
    val dimension: LiveData<Int> = _dimension

    fun updateDimension(dim: Int) {
        _dimension.value = dim
    }
}