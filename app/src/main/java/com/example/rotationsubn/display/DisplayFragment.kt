package com.example.rotationsubn.display

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import com.example.rotationsubn.core.Parametrization
import com.example.rotationsubn.ui.components.inputslider.InputSlider
import com.example.rotationsubn.ui.theme.RNTheme

class DisplayFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setViewCompositionStrategy(
            ViewCompositionStrategy.DisposeOnLifecycleDestroyed(this@DisplayFragment)
        )
        setContent {
            RNTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(RNTheme.colors.surface)
                        .padding(RNTheme.gaps.column.lg),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    InputSlider(
                        Parametrization.Dim3.Quaternions.parameters[0].apply {
                            value = 0.45f
                            isFixed = true
                        }
                    ).Content(true)
                    Spacer(Modifier.height(RNTheme.gaps.vertical.lg))
                    InputSlider(
                        Parametrization.Dim3.Yuler.parameters[0].apply { value = 245f }
                    ).Content()
                }
            }
        }
    }
}
