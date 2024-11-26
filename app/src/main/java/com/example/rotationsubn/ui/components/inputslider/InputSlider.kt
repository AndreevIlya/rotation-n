package com.example.rotationsubn.ui.components.inputslider

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderColors
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.SliderState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.onImeAction
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.example.rotationsubn.R
import com.example.rotationsubn.core.Parameter
import com.example.rotationsubn.core.ParameterType
import com.example.rotationsubn.core.Parametrization
import com.example.rotationsubn.core.TitledValue
import com.example.rotationsubn.core.utils.filterToFloat
import com.example.rotationsubn.core.utils.toFloatByUSLocale
import com.example.rotationsubn.ui.components.IconButton
import com.example.rotationsubn.ui.components.IconData
import com.example.rotationsubn.ui.theme.RNTheme

class InputSlider(private val parameter: Parameter) {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Content(hasNext: Boolean = false) {
        val value = remember { mutableStateOf(parameter.round()) }
        val isFixed = remember { mutableStateOf(parameter.isFixed) }
        val title = stringResource(parameter.title)

        Surface(
            modifier = Modifier
                .shadow(elevation = 8.dp, shape = RNTheme.corners.md)
                .background(
                    color = RNTheme.colors.surface,
                    shape = RNTheme.corners.md
                )
                .padding(RNTheme.gaps.row.md),
            tonalElevation = 8.dp
        ) {
            Column(Modifier.background(RNTheme.colors.surface)) {
                Text(
                    text = title,
                    color = RNTheme.colors.onSurface,
                    style = RNTheme.typography.body.lg
                )
                Spacer(Modifier.height(RNTheme.gaps.vertical.sm))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    InputField(
                        value = value.value,
                        onChange = {
                            value.value = it.filterToFloat { parameter.value = it }
                        },
                        onChanged = { value.value = parameter.round() },
                        hasNext = hasNext
                    )
                    Spacer(modifier = Modifier.width(RNTheme.gaps.horizontal.lg))
                    IconButton(
                        modifier = Modifier.clickable {
                            parameter.value -= parameter.type.step
                            value.value = parameter.round()
                        },
                        semantics = {
                            onClick(
                                label = "$title minus",
                                action = { true }
                            )
                        },
                        size = DpSize(24.dp, 24.dp),
                        icon = IconData(
                            res = R.drawable.ic_minus,
                            tint = RNTheme.colors.onSurface,
                            description = "$title minus"
                        )
                    )
                    Spacer(modifier = Modifier.width(RNTheme.gaps.horizontal.md))
                    Slider(
                        modifier = Modifier.weight(1f, true),
                        value = value.value.toFloatByUSLocale()
                    ) {
                        parameter.value = it
                        value.value = parameter.round()
                    }
                    Spacer(modifier = Modifier.width(RNTheme.gaps.horizontal.md))
                    IconButton(
                        modifier = Modifier
                            .clickable {
                                parameter.value += parameter.type.step
                                value.value = parameter.round()
                            },
                        semantics = {
                            onClick(
                                label = "$title plus",
                                action = { true }
                            )
                        },
                        size = DpSize(24.dp, 24.dp),
                        icon = IconData(
                            res = R.drawable.ic_plus,
                            tint = RNTheme.colors.onSurface,
                            description = "$title plus"
                        )
                    )
                    Spacer(modifier = Modifier.width(RNTheme.gaps.horizontal.lg))
                    IconButton(
                        modifier = Modifier
                            .clickable {
                                isFixed.value = !isFixed.value
                                parameter.isFixed = isFixed.value
                            }
                            .border(
                                width = 2.dp,
                                color = RNTheme.colors.primary,
                                shape = RNTheme.corners.sm
                            ),
                        semantics = {
                            onClick(
                                label = "$title fixed",
                                action = { true }
                            )
                        },
                        size = DpSize(24.dp, 24.dp),
                        icon = if (isFixed.value) IconData(
                            res = R.drawable.ic_cross,
                            tint = RNTheme.colors.onSurface,
                            description = "$title fixed"
                        ) else null
                    )
                }
                Spacer(Modifier.height(RNTheme.gaps.vertical.sm))
                LazyRow(
                    modifier = Modifier.padding(start = 60.dp, end = 26.dp),
                    contentPadding = PaddingValues(end = RNTheme.gaps.horizontal.md)
                ) {
                    items(parameter.suggestions) { suggestion ->
                        SuggestionChip(suggestion.title) {
                            parameter.value = suggestion.value
                            value.value = parameter.round()
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun InputField(
        value: String,
        onChange: (String) -> Unit,
        onChanged: () -> Unit,
        hasNext: Boolean
    ) {
        val focusManager = LocalFocusManager.current
        val title = stringResource(parameter.title)

        BasicTextField(
            modifier = Modifier
                .semantics {
                    contentDescription = "$title input field"
                    onImeAction(
                        imeActionType = if (hasNext) ImeAction.Next else ImeAction.Done,
                        action = {
                            onChanged()
                            true
                        }
                    )
                }
                .size(width = 58.dp, height = 32.dp)
                .onFocusEvent { if (!it.isFocused) onChanged() },
            value = TextFieldValue(text = value, selection = TextRange(value.length)),
            onValueChange = { onChange(it.text) },
            textStyle = RNTheme.typography.body.md.copy(textAlign = TextAlign.Center),
            singleLine = true,
            cursorBrush = SolidColor(RNTheme.colors.onSurface),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            keyboardActions = KeyboardActions {
                focusManager.clearFocus()
                onChanged()
            },
            decorationBox = @Composable {
                Box(
                    modifier = Modifier
                        .size(width = 58.dp, height = 32.dp)
                        .border(
                            width = 2.dp,
                            color = RNTheme.colors.primary,
                            shape = RNTheme.corners.md
                        )
                        .padding(RNTheme.gaps.square.sm),
                    contentAlignment = Alignment.Center
                ) { it() }
            }
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun Slider(modifier: Modifier, value: Float, onValueChanged: (Float) -> Unit) {
        val title = stringResource(parameter.title)

        Slider(
            modifier = modifier.semantics {
                contentDescription = "$title slider"
            },
            value = value,
            valueRange = parameter.type.start..parameter.type.end,
            colors = sliderColors(),
            track = @Composable { state: SliderState ->
                SliderDefaults.Track(
                    modifier = Modifier.scale(scaleX = 1f, scaleY = 0.5f),
                    colors = sliderColors(),
                    drawStopIndicator = null,
                    thumbTrackGapSize = RNTheme.gaps.horizontal.md,
                    sliderState = state
                )
            },
            thumb = @Composable { state: SliderState ->
                SliderDefaults.Thumb(
                    interactionSource = remember { MutableInteractionSource() },
                    thumbSize = DpSize(4.dp, 24.dp),
                    colors = sliderColors(),
                    enabled = true
                )
            },
            onValueChange = { value: Float -> onValueChanged(value) }
        )
    }

    @Composable
    private fun sliderColors() = SliderColors(
        thumbColor = RNTheme.colors.primary,
        activeTrackColor = RNTheme.colors.primary,
        inactiveTrackColor = RNTheme.colors.primary,
        activeTickColor = RNTheme.colors.primary,
        inactiveTickColor = RNTheme.colors.primary,
        disabledThumbColor = RNTheme.colors.primary,
        disabledActiveTrackColor = RNTheme.colors.primary,
        disabledActiveTickColor = RNTheme.colors.primary,
        disabledInactiveTrackColor = RNTheme.colors.primary,
        disabledInactiveTickColor = RNTheme.colors.primary
    )

    @Composable
    private fun SuggestionChip(title: String, onClick: () -> Unit) {
        val paramTitle = stringResource(parameter.title)

        Box(
            modifier = Modifier
                .background(
                    color = RNTheme.colors.primaryContainer,
                    shape = RNTheme.corners.lg
                )
                .padding(RNTheme.gaps.row.sm)
                .clickable { onClick() }
                .semantics { onClick(label = "$paramTitle chip $title", action = { true }) },
        ) {
            Text(
                text = title,
                color = RNTheme.colors.onPrimaryContainer,
                style = RNTheme.typography.label.lg
            )
        }
    }

}

@Preview
@Composable
fun AnglesInputSlider() {
    Column {
        Spacer(Modifier.height(30.dp))
        InputSlider(
            Parametrization.Dim3.TaitBryan.parameters[0].apply { value = 150f }
        ).Content()
    }
}

@Preview
@Composable
fun QuaternionInputSlider() {
    Column {
        Spacer(Modifier.height(30.dp))
        InputSlider(
            Parametrization.Dim3.Quaternions.parameters[0].apply { value = 0.25f }
        ).Content()
    }
}

@Preview
@Composable
fun ManySuggestionsInputSlider() {
    Column {
        Spacer(Modifier.height(30.dp))
        InputSlider(
            Parameter(
                title = R.string.parametrization_dim3_quaternion_0,
                type = ParameterType.Quaternion,
                suggestions = listOf(
                    TitledValue("long name", 0.5f),
                    TitledValue("long name", 0.5f),
                    TitledValue("long name", 0.5f),
                    TitledValue("long name", 0.5f),
                    TitledValue("long name", 0.5f),
                    TitledValue("long name", 0.5f),
                    TitledValue("long name", 0.5f)
                )
            )
        ).Content()
    }
}
