package com.example.rotationsubn.topbar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.rotationsubn.ui.components.topbar.TopBar
import com.example.rotationsubn.ui.theme.RNTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopBarFragment : Fragment() {

    private val viewModel: TopBarViewModel by viewModels<TopBarViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setViewCompositionStrategy(
            ViewCompositionStrategy.DisposeOnLifecycleDestroyed(this@TopBarFragment)
        )
        setContent {
            val state by viewModel.state.collectAsState()
            RNTheme {
                TopBar(state.dimensionRange, state.currentDimension) {
                    viewModel.sendAction(TopBarAction.UpdateDimension(it))
                }.Content()
            }
        }
    }
}
