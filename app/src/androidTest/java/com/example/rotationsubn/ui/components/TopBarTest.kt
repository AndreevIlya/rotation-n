package com.example.rotationsubn.ui.components

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.rotationsubn.ui.components.topbar.TopBar
import com.example.rotationsubn.ui.theme.RNTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class TopBarTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Inject
    lateinit var topBar: TopBar

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun openDimensionsMenu() {
        composeRule.setContent { RNTheme { topBar.Content() } }
        composeRule.onNodeWithTag(TopBar.DIMENSION_MENU_VISIBILITY_TOGGLER).performClick()
        composeRule.onNodeWithTag(TopBar.DIMENSION_MENU_CONTENT).assertIsDisplayed()
        composeRule.onNodeWithTag(TopBar.DIMENSION_MENU_VISIBILITY_TOGGLER).performClick()
        composeRule.onNodeWithTag(TopBar.DIMENSION_MENU_CONTENT).assertIsNotDisplayed()
    }

    @Test
    fun onChosenDimensionFromMenu() {
        composeRule.setContent { RNTheme { topBar.Content() } }
        composeRule.onNodeWithTag(TopBar.DIMENSION_MENU_VISIBILITY_TOGGLER).performClick()
        composeRule.onNodeWithTag(TopBar.DIMENSION_MENU_CONTENT).assertIsDisplayed()
        composeRule.onNodeWithTag("${TopBar.DIMENSION_MENU_ITEM} 4").performClick()
        composeRule.onNodeWithTag(TopBar.DIMENSION_MENU_CONTENT).assertIsNotDisplayed()
        composeRule.onNodeWithTag(TopBar.DIMENSION_MENU_CURRENT).assertTextEquals("4")
    }
}
