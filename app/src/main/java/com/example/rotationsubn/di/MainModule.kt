package com.example.rotationsubn.di

import com.example.rotationsubn.repo.MainRepo
import com.example.rotationsubn.repo.MainRepoImpl
import com.example.rotationsubn.topbar.TopBarPresenter
import com.example.rotationsubn.topbar.TopBarPresenterImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class MainModule {

    @Binds
    abstract fun topBarPresenter(presenter: TopBarPresenterImpl): TopBarPresenter

    @Binds
    abstract fun mainRepo(repo: MainRepoImpl): MainRepo

}
