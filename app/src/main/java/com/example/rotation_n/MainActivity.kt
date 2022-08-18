package com.example.rotation_n

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.rotation_n.ui.theme.RotationNTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RotationNTheme {
                Scaffold(
                    modifier = Modifier.background(MaterialTheme.colorScheme.background),
                    topBar = { TopBar() }
                ) {
                    Box(modifier = Modifier.padding(it))
                }
            }
        }
    }

    @Composable
    fun TopBar() {
        SmallTopAppBar(
            title = {
                Text(
                    stringResource(id = R.string.app_name),
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    style = MaterialTheme.typography.headlineLarge
                )
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            ),
            navigationIcon = { ImageVector.vectorResource(id = R.drawable.back) },
            actions = { DimensionMenu() }
        )
    }

    @Composable
    fun DimensionMenu() {
        val dimension by viewModel.dimension.observeAsState()
        var expanded by remember { mutableStateOf(false) }

        Box(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.secondaryContainer)
                .padding(8.dp)
                .size(40.dp, 20.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    (dimension ?: 3).toString(),
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    style = MaterialTheme.typography.labelLarge
                )
                IconButton(
                    onClick = { expanded = true },
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
                expanded = expanded,
                onDismissRequest = { expanded = false },
                Modifier.background(MaterialTheme.colorScheme.surface)
            ) {
                LazyVerticalGrid(
                    modifier = Modifier.size(120.dp, 96.dp),
                    columns = GridCells.Fixed(3)
                ) {
                    items((3..7).toList()) { DimensionMenuItem(dim = it) }
                }
            }
        }

    }

    @Composable
    fun DimensionMenuItem(dim: Int) {
        val scope = rememberCoroutineScope()
        DropdownMenuItem(
            text = {
                Text(
                    dim.toString(),
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.labelLarge
                )
            }, onClick = {
                scope.launch {
                    viewModel.updateDimension(dim)
                }
            }
        )
    }
}
