package com.example.rotationsubn.ui.components.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.example.rotationsubn.R
import com.example.rotationsubn.ui.theme.RNTheme

class TopBar(
    private val dimensions: List<Int>,
    private val currentDimension: Int,
    private val onBack: (() -> Unit)? = null,
    private val onUpdateDimensionClicked: (Int) -> Unit
) {

    @Composable
    fun Content() {
        Surface(
            modifier = Modifier.height(60.dp),
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
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    if (onBack != null) {
                        IconButton(
                            modifier = Modifier.size(24.dp),
                            onClick = onBack,
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.back),
                                contentDescription = "top bar back",
                            )
                        }
                        Spacer(Modifier.width(RNTheme.gaps.horizontal.lg))
                    }
                    Text(
                        text = stringResource(id = R.string.app_name),
                        color = RNTheme.colors.onPrimaryContainer,
                        style = RNTheme.typography.title.lg
                    )
                }
                DimensionMenu()
            }
        }
    }

    @Composable
    fun DimensionMenu() {
        var expanded by remember { mutableStateOf(false) }

        Box {
            DimensionButton(currentDimension) { expanded = !expanded }
            DropdownMenu(
                modifier = Modifier
                    .width(72.dp)
                    .background(color = RNTheme.colors.surface, shape = RNTheme.corners.sm)
                    .semantics { testTag = DIMENSION_MENU_CONTENT },
                offset = DpOffset(x = 0.dp, y = RNTheme.gaps.vertical.md),
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                Column {
                    for (i in dimensions.indices step (2)) {
                        DimensionMenuRow(
                            dimLeft = {
                                DimensionMenuItem(
                                    dim = dimensions[i],
                                    isActive = dimensions[i] == currentDimension
                                ) {
                                    onUpdateDimensionClicked(dimensions[i])
                                    expanded = false
                                }
                            },
                            dimRight = {
                                if (i + 1 < dimensions.size) {
                                    DimensionMenuItem(
                                        dim = dimensions[i + 1],
                                        isActive = dimensions[i + 1] == currentDimension
                                    ) {
                                        onUpdateDimensionClicked(dimensions[i + 1])
                                        expanded = false
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    }

    @Composable
    private fun DimensionMenuRow(
        dimLeft: @Composable () -> Unit,
        dimRight: @Composable () -> Unit
    ) {
        Row(modifier = Modifier.padding(RNTheme.gaps.row.sm)) {
            dimLeft()
            dimRight()
        }
    }

    @Composable
    fun DimensionMenuItem(dim: Int, isActive: Boolean, onItemClicked: (Int) -> Unit) {
        Box(
            modifier = Modifier
                .semantics { testTag = "$DIMENSION_MENU_ITEM $dim" }
                .size(width = 32.dp, height = 36.dp)
                .background(
                    color = RNTheme.colors.run { if (isActive) primary else surface },
                    shape = RNTheme.corners.md
                )
                .clickable { onItemClicked(dim) },
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = dim.toString(),
                color = RNTheme.colors.run { if (isActive) onPrimary else onSurface },
                style = RNTheme.typography.body.lg,
                textAlign = TextAlign.Center
            )
        }
    }

    @Composable
    private fun DimensionButton(dim: Int, onClicked: () -> Unit) {
        Box(
            modifier = Modifier
                .semantics { testTag = DIMENSION_MENU_VISIBILITY_TOGGLER }
                .size(width = 32.dp, height = 36.dp)
                .clickable { onClicked() },
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier.semantics { testTag = DIMENSION_MENU_CURRENT },
                text = dim.toString(),
                color = RNTheme.colors.onPrimaryContainer,
                style = RNTheme.typography.title.md,
                textAlign = TextAlign.Center
            )
        }
    }

    companion object {
        const val DIMENSION_MENU_VISIBILITY_TOGGLER = "dimension menu visibility toggler"
        const val DIMENSION_MENU_CONTENT = "dimension menu content"
        const val DIMENSION_MENU_ITEM = "dimension menu item "
        const val DIMENSION_MENU_CURRENT = "dimension menu current"
    }
}

@Preview
@Composable
fun TopBar() {
    RNTheme {
        TopBar(listOf(3, 4, 5, 6, 7), 3, {}) {}.Content()
    }
}
