package com.example.rotationsubn.ui.components.topbar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

class TopBarViewModel(
    private val topBarPresenter: TopBarPresenter
) {

    private val _dimension = MutableLiveData(topBarPresenter.dimension)
    val dimension: LiveData<Int> = _dimension

    fun updateDimension(dim: Int) {
        topBarPresenter.dimension = dim
        _dimension.value = dim
    }

    class Factory @Inject constructor(
        private val presenter: TopBarPresenter
    ) {
        fun create(): TopBarViewModel = TopBarViewModel(presenter)
    }

}
