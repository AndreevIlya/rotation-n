package com.example.rotationsubn.topbar

import com.example.rotationsubn.mvi.MviReducer
import com.example.rotationsubn.repo.MainRepo
import javax.inject.Inject

class TopBarReducer @Inject constructor(
    private val mainRepo: MainRepo
) : MviReducer<TopBarState, TopBarAction> {

    override val initialState: TopBarState
        get() = TopBarState(3..7, 3)

    override fun reduce(state: TopBarState, action: TopBarAction): TopBarState {
        if (action is TopBarAction.UpdateDimension) {
            mainRepo.dimension = action.newDimension
            return state.copy(currentDimension = action.newDimension)
        }
        return initialState
    }

}
