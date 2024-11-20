package com.example.rotationsubn.display

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.stringResource
import androidx.fragment.app.Fragment
import com.example.rotationsubn.R
import com.example.rotationsubn.ui.components.inputslider.InputSlider
import com.example.rotationsubn.ui.components.inputslider.Parameter
import com.example.rotationsubn.ui.components.inputslider.ParameterType
import com.example.rotationsubn.ui.components.inputslider.TitledValue
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
                        .padding(RNTheme.gaps.column.lg),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    InputSlider(
                        Parameter(
                            title = stringResource(R.string.parametrization_dim3_quaternion_1),
                            type = ParameterType.Quaternion,
                            suggestions = quaternionSuggestions
                        ).apply {
                            value = 0.45f
                            isFixed = true
                        }
                    ).Content(true)
                    Spacer(Modifier.height(RNTheme.gaps.vertical.lg))
                    InputSlider(
                        Parameter(
                            title = stringResource(R.string.parametrization_dim3_yuler_rotation),
                            type = ParameterType.Angle,
                            suggestions = angleSuggestions
                        ).apply { value = 145f }
                    ).Content()
                }
            }
        }
    }

    private companion object {
        val angleSuggestions = listOf(
            TitledValue<Float>("45", 45f),
            TitledValue<Float>("90", 90f),
            TitledValue<Float>("135", 135f)
        )
        val quaternionSuggestions = listOf(
            TitledValue<Float>("0.25", 0.25f),
            TitledValue<Float>("0.333", 0.333f),
            TitledValue<Float>("0.75", 0.75f)
        )
    }
}
