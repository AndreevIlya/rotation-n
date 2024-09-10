package com.example.rotationsubn.ui.components

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.example.rotationsubn.topbar.TopBarViewModel
import com.example.rotationsubn.ui.components.topbar.TopBar
import com.example.rotationsubn.ui.theme.RNTheme
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TopBarTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    private val mockViewModel: TopBarViewModel = mock()

    @Test
    fun openDimensionsMenu() {
        val topBar = TopBar(3) { }
        composeRule.setContent { RNTheme { topBar.Content() } }
        composeRule.onNodeWithTag(TopBar.DIMENSION_MENU_VISIBILITY_TOGGLER).performClick()
        composeRule.onNodeWithTag(TopBar.DIMENSION_MENU_CONTENT).assertIsDisplayed()
        composeRule.onNodeWithTag(TopBar.DIMENSION_MENU_VISIBILITY_TOGGLER).performClick()
        composeRule.onNodeWithTag(TopBar.DIMENSION_MENU_CONTENT).assertIsNotDisplayed()
    }

    @Test
    fun onChosenDimensionFromMenu() {
        val topBar = TopBar(3) {
            `when`(mockViewModel.dimension).thenReturn(flowOf(4))
        }
        composeRule.setContent { RNTheme { topBar.Content() } }
        composeRule.onNodeWithTag(TopBar.DIMENSION_MENU_CURRENT).assertTextEquals("3")
        composeRule.onNodeWithTag(TopBar.DIMENSION_MENU_VISIBILITY_TOGGLER).performClick()
        composeRule.onNodeWithTag(TopBar.DIMENSION_MENU_CONTENT).assertIsDisplayed()
        composeRule.onNodeWithTag("${TopBar.DIMENSION_MENU_ITEM} 4").performClick()
        composeRule.onNodeWithTag(TopBar.DIMENSION_MENU_CONTENT).assertIsNotDisplayed()
    }
}
