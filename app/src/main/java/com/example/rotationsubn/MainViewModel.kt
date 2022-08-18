package com.example.rotationsubn

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _dimension = MutableLiveData(3)
    val dimension: LiveData<Int> = _dimension

    fun updateDimension(dim: Int) {
        _dimension.value = dim
    }
}