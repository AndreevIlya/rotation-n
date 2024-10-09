package com.example.rotationsubn.ui.components.bottombar

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rotationsubn.R
import com.example.rotationsubn.ui.components.bottombar.ParametrizationButton.Companion.byType
import com.example.rotationsubn.ui.theme.RNTheme

class BottomBar(
    private val buttons: List<ParametrizationButton>,
    private val onNav: (Boolean) -> Unit
) {

    @Composable
    fun Content(selectedParametrization: Parametrization.Dim3, isDisplay: Boolean) {
        Surface(
            modifier = Modifier.height(dimensionResource(R.dimen.bottom_bar_height)),
            color = RNTheme.colors.primaryContainer,
            contentColor = RNTheme.colors.onPrimaryContainer
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(RNTheme.colors.primaryContainer)
                    .padding(RNTheme.gaps.row.md),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                val (selectedButton, onSelected) = remember {
                    mutableStateOf(buttons.byType(selectedParametrization))
                }
                Row(
                    modifier = Modifier.selectableGroup(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    buttons.forEach {
                        ParametrizationButton(it, it == selectedButton) { onSelected(it) }
                        Spacer(Modifier.width(RNTheme.gaps.horizontal.lg))
                    }
                }

                NavigationButton(toDisplay = !isDisplay) { onNav(!isDisplay) }
            }
        }
    }

    @Composable
    private fun ParametrizationButton(
        button: ParametrizationButton,
        isSelected: Boolean,
        onSelected: (ParametrizationButton) -> Unit
    ) {
        Box(
            modifier = Modifier
                .semantics { onClick(label = button.label, action = { true }) }
                .size(width = 40.dp, height = 40.dp)
                .selectable(selected = isSelected) {
                    onSelected(button)
                    button.onSelected()
                }
                .backgroundOrBorder(isSelected),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = button.iconRes),
                tint = RNTheme.colors.run { if (isSelected) onPrimary else primary },
                contentDescription = button.label,
            )
        }
    }

    @Composable
    private fun NavigationButton(toDisplay: Boolean, onClicked: (Boolean) -> Unit) {
        val navState = remember { mutableStateOf(toDisplay) }
        val description = stringResource(
            if (navState.value) {
                R.string.semantics_bottom_bar_show_display
            } else {
                R.string.semantics_bottom_bar_show_parametrization
            }
        )
        Box(
            modifier = Modifier
                .semantics { stateDescription = description }
                .size(width = 56.dp, height = 56.dp)
                .background(
                    color = RNTheme.colors.primary,
                    shape = RNTheme.corners.md
                )
                .toggleable(toDisplay) {
                    navState.value = !navState.value
                    onClicked(it)
                },
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(
                    id = if (navState.value) {
                        R.drawable.ic_nav_to_display
                    } else {
                        R.drawable.ic_nav_to_params
                    }
                ),
                tint = RNTheme.colors.onPrimary,
                contentDescription = description,
            )
        }
    }

    @Composable
    private fun Modifier.backgroundOrBorder(isActive: Boolean): Modifier = if (isActive) {
        this.background(
            color = RNTheme.colors.primary,
            shape = RNTheme.corners.md
        )
    } else {
        this.border(
            width = 2.dp,
            color = RNTheme.colors.primary,
            shape = RNTheme.corners.md
        )
    }
}

@Preview
@Composable
fun BottomBarPreview() {
    RNTheme {
        BottomBar(
            buttons = listOf(
                ParametrizationButton.Yuler(),
                ParametrizationButton.TaitBryan(),
                ParametrizationButton.QuaternionAxis(),
                ParametrizationButton.Quaternion()
            ),
            onNav = {}
        ).Content(
            selectedParametrization = Parametrization.Dim3.TAIT_BRYAN,
            isDisplay = false
        )
    }
}
