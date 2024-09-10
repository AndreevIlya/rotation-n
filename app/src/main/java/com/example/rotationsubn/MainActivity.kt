package com.example.rotationsubn

import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : FragmentActivity(R.layout.main) {

    private val viewModel by viewModels<MainViewModel>()

}
