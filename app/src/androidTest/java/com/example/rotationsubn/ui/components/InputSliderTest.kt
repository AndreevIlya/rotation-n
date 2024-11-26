package com.example.rotationsubn.ui.components

import androidx.activity.ComponentActivity
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.test.TouchInjectionScope
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertContentDescriptionEquals
import androidx.compose.ui.test.assertRangeInfoEquals
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.click
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onChild
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performTextReplacement
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.unit.dp
import com.example.rotationsubn.core.Parameter
import com.example.rotationsubn.core.ParameterType
import com.example.rotationsubn.core.Parametrization
import com.example.rotationsubn.ui.components.inputslider.InputSlider
import com.example.rotationsubn.ui.theme.RNTheme
import com.example.rotationsubn.utils.hasClickLabel
import com.example.rotationsubn.utils.hasNoChildren
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class InputSliderTest {

    @get:Rule
    val rule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var quaternion: Parameter
    private lateinit var quaternionTitle: String
    private lateinit var angle: Parameter
    private lateinit var angleTitle: String

    @Before
    fun init() {
        quaternion = Parametrization.Dim3.Quaternions.parameters[0].copy()
        angle = Parametrization.Dim3.Yuler.parameters[0].copy()
        angleTitle = rule.activity.resources.getString(angle.title)
        quaternionTitle = rule.activity.resources.getString(quaternion.title)
    }

    @Test
    fun initValue() {
        rule.setContent { RNTheme { InputSlider(quaternion).Content() } }
        rule.onNodeWithText(quaternionTitle).assertExists()
        assertInputFieldValue("0.000", ParameterType.Quaternion)
        assertSliderValue(0f, ParameterType.Quaternion)
    }

    @Test
    fun inputValidIntoField() {
        rule.setContent { RNTheme { InputSlider(angle.apply { value = 75f }).Content() } }
        rule.onNodeWithContentDescription(("$angleTitle input field")).run {
            assertTextEquals("75.00")
            assertSliderValue(75f, ParameterType.Angle)
            performTextReplacement("0")
            assertTextEquals("0")
            performImeAction()
            assertTextEquals("0.000")
            assertSliderValue(0f, ParameterType.Angle)
            performTextReplacement("90")
            assertTextEquals("90")
            performImeAction()
            assertTextEquals("90.00")
            assertSliderValue(90f, ParameterType.Angle)
            performTextReplacement("262")
            assertTextEquals("262")
            performImeAction()
            assertTextEquals("262.0")
            assertSliderValue(262f, ParameterType.Angle)
            performTextReplacement("1.2345")
            assertTextEquals("1.2345")
            performImeAction()
            assertTextEquals("1.235")
            assertSliderValue(1.235f, ParameterType.Angle)
            performTextReplacement("12.345")
            assertTextEquals("12.345")
            performImeAction()
            assertTextEquals("12.35")
            assertSliderValue(12.35f, ParameterType.Angle)
            performTextReplacement("345.678")
            assertTextEquals("345.678")
            performImeAction()
            assertTextEquals("345.7")
            assertSliderValue(345.7f, ParameterType.Angle)
        }
    }

    @Test
    fun inputInvalidIntoField() {
        rule.setContent { RNTheme { InputSlider(angle).Content() } }
        rule.onNodeWithContentDescription(("$angleTitle input field")).run {
            performTextReplacement(".")
            assertTextEquals("")
            performTextReplacement(",")
            assertTextEquals("")
            performTextReplacement("-")
            assertTextEquals("")
            performTextReplacement("2.,")
            assertTextEquals("2.")
            performTextReplacement("2..")
            assertTextEquals("2.")
        }
    }

    private fun assertInputFieldValue(value: String, type: ParameterType) {
        rule.onNodeWithContentDescription(
            "${if (type == ParameterType.Angle) angleTitle else quaternionTitle} input field"
        ).assertTextEquals(value)
    }

    @Test
    fun setSliderQuaternionValue() {
        rule.setContent { RNTheme { InputSlider(quaternion).Content() } }
        rule.onNodeWithContentDescription("$quaternionTitle slider").run {
            performTouchInput { click(Offset(clickPosition(0.000f, ParameterType.Quaternion), 0f)) }
            assertSliderValue(0f, ParameterType.Quaternion)
            assertInputFieldValue("0.000", ParameterType.Quaternion)
            performTouchInput { click(Offset(clickPosition(0.125f, ParameterType.Quaternion), 0f)) }
            assertSliderValue(0.125f, ParameterType.Quaternion)
            assertInputFieldValue("0.125", ParameterType.Quaternion)
            performTouchInput { click(Offset(clickPosition(0.250f, ParameterType.Quaternion), 0f)) }
            assertSliderValue(0.25f, ParameterType.Quaternion)
            assertInputFieldValue("0.250", ParameterType.Quaternion)
            performTouchInput { click(Offset(clickPosition(0.500f, ParameterType.Quaternion), 0f)) }
            assertSliderValue(0.5f, ParameterType.Quaternion)
            assertInputFieldValue("0.500", ParameterType.Quaternion)
            performTouchInput { click(Offset(clickPosition(0.666f, ParameterType.Quaternion), 0f)) }
            assertSliderValue(0.666f, ParameterType.Quaternion)
            assertInputFieldValue("0.666", ParameterType.Quaternion)
            performTouchInput { click(Offset(clickPosition(0.750f, ParameterType.Quaternion), 0f)) }
            assertSliderValue(0.75f, ParameterType.Quaternion)
            assertInputFieldValue("0.750", ParameterType.Quaternion)
            performTouchInput { click(Offset(clickPosition(1.000f, ParameterType.Quaternion), 0f)) }
            assertSliderValue(1f, ParameterType.Quaternion)
            assertInputFieldValue("1.000", ParameterType.Quaternion)
        }
    }

    @Test
    fun setSliderAngleValue() {
        rule.setContent { RNTheme { InputSlider(angle).Content() } }
        rule.onNodeWithContentDescription("$angleTitle slider").run {
            performTouchInput { click(Offset(clickPosition(0.000f, ParameterType.Angle), 0f)) }
            assertSliderValue(0f, ParameterType.Angle)
            assertInputFieldValue("0.000", ParameterType.Angle)
            performTouchInput { click(Offset(clickPosition(10f, ParameterType.Angle), 0f)) }
            assertSliderValue(10f, ParameterType.Angle)
            assertInputFieldValue("10.00", ParameterType.Angle)
            performTouchInput { click(Offset(clickPosition(90f, ParameterType.Angle), 0f)) }
            assertSliderValue(90f, ParameterType.Angle)
            assertInputFieldValue("90.00", ParameterType.Angle)
            performTouchInput { click(Offset(clickPosition(190f, ParameterType.Angle), 0f)) }
            assertSliderValue(190f, ParameterType.Angle)
            assertInputFieldValue("190.0", ParameterType.Angle)
            performTouchInput { click(Offset(clickPosition(234.5f, ParameterType.Angle), 0f)) }
            assertSliderValue(234.5f, ParameterType.Angle)
            assertInputFieldValue("234.5", ParameterType.Angle)
            performTouchInput { click(Offset(clickPosition(360f, ParameterType.Angle), 0f)) }
            assertSliderValue(360f, ParameterType.Angle)
            assertInputFieldValue("360.0", ParameterType.Angle)
        }
    }

    private fun assertSliderValue(value: Float, type: ParameterType) {
        rule.onNodeWithContentDescription(
            "${if (type == ParameterType.Angle) angleTitle else quaternionTitle} slider"
        ).assertRangeInfoEquals(
            ProgressBarRangeInfo(
                current = value,
                range = type.start..type.end
            )
        )
    }

    private fun TouchInjectionScope.clickPosition(value: Float, type: ParameterType): Float {
        val range: Float = type.end - type.start
        val thumb = 4.dp.toPx()
        val padding = 10.dp.roundToPx() // AccessibilityUtils.HorizontalSemanticsBoundsPadding
        return value / range * (width - thumb - 2 * padding).toFloat() + padding + thumb / 2
    }

    @Test
    fun onMinusClicked() {
        rule.setContent { RNTheme { InputSlider(angle.apply { value = 75f }).Content() } }
        rule.onNode(hasClickLabel("$angleTitle minus")).run { repeat(15) { performClick() } }
        assertSliderValue(60f, ParameterType.Angle)
        assertInputFieldValue("60.00", ParameterType.Angle)
    }

    @Test
    fun onPlusClicked() {
        rule.setContent { RNTheme { InputSlider(quaternion.apply { value = 0.65f }).Content() } }
        rule.onNode(hasClickLabel("$quaternionTitle plus")).run { repeat(26) { performClick() } }
        assertSliderValue(0.676f, ParameterType.Quaternion)
        assertInputFieldValue("0.676", ParameterType.Quaternion)
    }

    @Test
    fun onFixedSwitched() {
        rule.setContent { RNTheme { InputSlider(quaternion.apply { isFixed = true }).Content() } }
        rule.onNode(hasClickLabel("$quaternionTitle fixed"), useUnmergedTree = true).run {
            onChild().assertContentDescriptionEquals("$quaternionTitle fixed")
            assertTrue(quaternion.isFixed)
            performClick()
            assert(hasNoChildren())
            assertFalse(quaternion.isFixed)
            performClick()
            onChild().assertContentDescriptionEquals("$quaternionTitle fixed")
            assertTrue(quaternion.isFixed)
        }
    }

    @Test
    fun onSuggestionClicked() {
        rule.setContent { RNTheme { InputSlider(angle).Content() } }
        rule.onNode(hasClickLabel("$angleTitle chip 45")).performClick()
        assertSliderValue(45f, ParameterType.Angle)
        assertInputFieldValue("45.00", ParameterType.Angle)
        rule.onNode(hasClickLabel("$angleTitle chip 135")).performClick()
        assertSliderValue(135f, ParameterType.Angle)
        assertInputFieldValue("135.0", ParameterType.Angle)
    }
}
