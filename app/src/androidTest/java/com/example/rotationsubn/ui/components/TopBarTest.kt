package com.example.rotationsubn.ui.components

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import com.example.rotationsubn.ui.components.topbar.TopBar
import com.example.rotationsubn.ui.theme.RNTheme
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TopBarTest {

    @get:Rule
    val rule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun openDimensionsMenu() {
        val topBar = TopBar(listOf(3, 4), 3) { }
        rule.setContent { RNTheme { topBar.Content() } }
        rule.onNodeWithContentDescription(TopBar.DIMENSION_MENU_VISIBILITY_TOGGLER).performClick()
        rule.onNodeWithContentDescription(TopBar.DIMENSION_MENU_CONTENT).assertIsDisplayed()
        rule.onNodeWithContentDescription(TopBar.DIMENSION_MENU_VISIBILITY_TOGGLER).performClick()
        rule.onNodeWithContentDescription(TopBar.DIMENSION_MENU_CONTENT).assertIsNotDisplayed()
    }

    @Test
    fun onChosenDimensionFromMenu() {
        val topBar = TopBar(listOf(3, 4, 5), 3) { assertEquals(4, it) }
        rule.setContent { RNTheme { topBar.Content() } }
        rule.onNodeWithContentDescription(
            label = TopBar.DIMENSION_MENU_CURRENT,
            useUnmergedTree = true
        ).assertTextEquals("3")
        rule.onNodeWithContentDescription(TopBar.DIMENSION_MENU_VISIBILITY_TOGGLER).performClick()
        rule.onNodeWithContentDescription(TopBar.DIMENSION_MENU_CONTENT).assertIsDisplayed()
        rule.onNodeWithContentDescription("${TopBar.DIMENSION_MENU_ITEM} 4").performClick()
        rule.onNodeWithContentDescription(TopBar.DIMENSION_MENU_CONTENT).assertIsNotDisplayed()
    }
}
