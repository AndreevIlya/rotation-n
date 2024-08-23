package com.example.rotationsubn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.rotationsubn.ui.theme.RNTheme
import com.example.rotationsubn.ui.components.topbar.TopBar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    @Inject
    lateinit var topBar: TopBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RNTheme {
                Scaffold(
                    modifier = Modifier.background(MaterialTheme.colorScheme.background),
                    topBar = { topBar.Content() }
                ) {
                    Box(modifier = Modifier.padding(it))
                }
            }
        }
    }
}
