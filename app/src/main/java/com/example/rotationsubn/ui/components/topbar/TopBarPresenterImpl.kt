package com.example.rotationsubn.ui.components.topbar

import com.example.rotationsubn.repo.MainRepo
import javax.inject.Inject

class TopBarPresenterImpl @Inject constructor(
    private val mainRepo: MainRepo
) : TopBarPresenter {

    override var dimension: Int
        get() = mainRepo.dimension
        set(value) {
            mainRepo.dimension = value
        }

}
