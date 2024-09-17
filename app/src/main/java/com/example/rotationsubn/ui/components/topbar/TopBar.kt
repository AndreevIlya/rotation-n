package com.example.rotationsubn.ui.components.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
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
import androidx.compose.ui.unit.dp
import com.example.rotationsubn.R

class TopBar(
    private val dimensionRange: IntRange,
    private val currentDimension: Int,
    private val onUpdateDimensionClicked: (Int) -> Unit
) {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Content() {
        TopAppBar(
            title = {
                Text(
                    stringResource(id = R.string.app_name),
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    style = MaterialTheme.typography.headlineLarge
                )
            },
            navigationIcon = { ImageVector.vectorResource(id = R.drawable.back) },
            actions = { DimensionMenu() },
            colors = TopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                scrolledContainerColor = MaterialTheme.colorScheme.primaryContainer,
                navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        )
    }

    @Composable
    fun DimensionMenu() {
        var expanded by remember { mutableStateOf(false) }

        Box(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.secondaryContainer)
                .padding(8.dp)
                .size(40.dp, 20.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    modifier = Modifier.semantics { testTag = DIMENSION_MENU_CURRENT },
                    text = currentDimension.toString(),
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    style = MaterialTheme.typography.labelLarge
                )
                IconButton(
                    modifier = Modifier.semantics { testTag = DIMENSION_MENU_VISIBILITY_TOGGLER },
                    onClick = { expanded = !expanded },
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                ) {
                    Icon(
                        ImageVector.vectorResource(id = R.drawable.arrow_drop_down),
                        "Open",
                        tint = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
            }
            DropdownMenu(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surface)
                    .semantics { testTag = DIMENSION_MENU_CONTENT },
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                LazyVerticalGrid(
                    modifier = Modifier.size(120.dp, 96.dp),
                    columns = GridCells.Fixed(3)
                ) {
                    items(dimensionRange.toList()) { dim ->
                        DimensionMenuItem(dim = dim) {
                            onUpdateDimensionClicked(dim)
                            expanded = false
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun DimensionMenuItem(dim: Int, onItemClicked: (Int) -> Unit) {
        DropdownMenuItem(
            modifier = Modifier.semantics { testTag = "$DIMENSION_MENU_ITEM $dim" },
            text = {
                Text(
                    text = dim.toString(),
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.labelLarge,
                    textAlign = TextAlign.Center
                )
            },
            onClick = { onItemClicked(dim) }
        )
    }

    companion object {
        const val DIMENSION_MENU_VISIBILITY_TOGGLER = "dimension menu visibility toggler"
        const val DIMENSION_MENU_CONTENT = "dimension menu content"
        const val DIMENSION_MENU_ITEM = "dimension menu item "
        const val DIMENSION_MENU_CURRENT = "dimension menu current"
    }
}
