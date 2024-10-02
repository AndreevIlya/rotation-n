package com.example.rotationsubn.di

import com.example.rotationsubn.mvi.MviReducer
import com.example.rotationsubn.repo.MainRepo
import com.example.rotationsubn.repo.MainRepoImpl
import com.example.rotationsubn.topbar.TopBarAction
import com.example.rotationsubn.topbar.TopBarReducer
import com.example.rotationsubn.topbar.TopBarState
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class MainModule {

    @Binds
    abstract fun topBarReducer(reducer: TopBarReducer): MviReducer<TopBarState, TopBarAction>

    @Binds
    abstract fun mainRepo(repo: MainRepoImpl): MainRepo

}
