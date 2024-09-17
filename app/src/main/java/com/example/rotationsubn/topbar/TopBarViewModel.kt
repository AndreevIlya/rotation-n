package com.example.rotationsubn.topbar

import com.example.rotationsubn.mvi.MviReducer
import com.example.rotationsubn.mvi.MviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TopBarViewModel @Inject constructor(
    reducer: MviReducer<TopBarState, TopBarAction>
) : MviViewModel<TopBarState, TopBarAction>(reducer)
