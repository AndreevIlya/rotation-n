package com.example.rotationsubn.topbar

import com.example.rotationsubn.mvi.MviReducer
import com.example.rotationsubn.mvi.WrongMviActionException
import com.example.rotationsubn.repo.MainRepo
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TopBarReducer @Inject constructor(
    private val mainRepo: MainRepo
) : MviReducer<TopBarState, TopBarAction> {

    override val initialState: TopBarState
        get() = TopBarState(
            dimensions = mainRepo.run { minDimension..maxDimension }.toList(),
            currentDimension = mainRepo.dimension
        )

    override suspend fun reduce(state: TopBarState, action: TopBarAction): TopBarState {
        if (action is TopBarAction.UpdateDimension) {
            return withContext(context = Dispatchers.IO) {
                mainRepo.dimension = action.newDimension
                state.copy(currentDimension = action.newDimension)
            }
        }
        throw WrongMviActionException("Wrong MVI action provided: $action")
    }

}
