package com.example.rotationsubn.topbar

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class TopBarViewModelImpl @Inject constructor(
    private val topBarPresenter: TopBarPresenter
) : ViewModel(), TopBarViewModel {

    private val _dimension = MutableStateFlow(topBarPresenter.dimension)
    override val dimension: Flow<Int> = _dimension.asStateFlow()

    override fun updateDimension(dim: Int) {
        topBarPresenter.dimension = dim
        _dimension.value = dim
    }

}
