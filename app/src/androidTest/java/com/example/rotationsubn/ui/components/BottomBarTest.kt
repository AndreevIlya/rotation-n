package com.example.rotationsubn.ui.components

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertContentDescriptionEquals
import androidx.compose.ui.test.assertIsNotSelected
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.hasStateDescription
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onChild
import androidx.compose.ui.test.performClick
import com.example.rotationsubn.R
import com.example.rotationsubn.ui.components.bottombar.BottomBar
import com.example.rotationsubn.ui.components.bottombar.Parametrization
import com.example.rotationsubn.ui.components.bottombar.ParametrizationButton
import com.example.rotationsubn.ui.theme.RNTheme
import com.example.rotationsubn.utils.hasClickLabel
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BottomBarTest {

    @get:Rule
    val rule = createAndroidComposeRule<ComponentActivity>()

    private val bottomBar = BottomBar(
        buttons = listOf(
            ParametrizationButton.Yuler(),
            ParametrizationButton.Quaternion()
        ),
        onNav = {}
    )

    @Test
    fun switchParamsButton() {
        rule.setContent { RNTheme { bottomBar.Content(Parametrization.Dim3.YULER, false) } }
        val yuler = rule.onNode(hasClickLabel(ParametrizationButton.YULER))
        val quaternions = rule.onNode(hasClickLabel(ParametrizationButton.QUATERNIONS))
        yuler.assertIsSelected()
        quaternions.assertIsNotSelected()
        quaternions.performClick()
        yuler.assertIsNotSelected()
        quaternions.assertIsSelected()
    }

    @Test
    fun toggleNavButton() {
        rule.setContent { RNTheme { bottomBar.Content(Parametrization.Dim3.YULER, false) } }
        val showDisplay = rule.activity.getString(R.string.semantics_bottom_bar_show_display)
        val showParams = rule.activity.getString(R.string.semantics_bottom_bar_show_parametrization)
        rule.onNode(hasStateDescription(showDisplay), useUnmergedTree = true).run {
            onChild().assertContentDescriptionEquals(showDisplay)
            assertExists()
            performClick()
            assertDoesNotExist()
        }
        rule.onNode(hasStateDescription(showParams), useUnmergedTree = true).run {
            assertExists()
            onChild().assertContentDescriptionEquals(showParams)
        }
    }
}
