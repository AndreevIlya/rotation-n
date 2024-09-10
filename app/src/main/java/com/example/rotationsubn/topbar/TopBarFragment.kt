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

    private val viewModel: TopBarViewModelImpl by viewModels<TopBarViewModelImpl>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setViewCompositionStrategy(
            ViewCompositionStrategy.DisposeOnLifecycleDestroyed(this@TopBarFragment)
        )
        setContent {
            val dim by viewModel.dimension.collectAsState(3)
            RNTheme {
                TopBar(dim) { viewModel.updateDimension(it) }
            }
        }
    }
}
