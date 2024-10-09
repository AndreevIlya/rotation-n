package com.example.rotationsubn.bottombar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import com.example.rotationsubn.ui.components.bottombar.BottomBar
import com.example.rotationsubn.ui.components.bottombar.Parametrization
import com.example.rotationsubn.ui.components.bottombar.ParametrizationButton

class BottomBarFragment : Fragment() {

    val bottomBar = BottomBar(
        buttons = listOf(
            ParametrizationButton.Yuler(),
            ParametrizationButton.TaitBryan(),
            ParametrizationButton.QuaternionAxis(),
            ParametrizationButton.Quaternion()
        ),
        onNav = {}
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setViewCompositionStrategy(
            ViewCompositionStrategy.DisposeOnLifecycleDestroyed(this@BottomBarFragment)
        )
        setContent {
            bottomBar.Content(
                selectedParametrization = Parametrization.Dim3.TAIT_BRYAN,
                isDisplay = false
            )
        }
    }
}
