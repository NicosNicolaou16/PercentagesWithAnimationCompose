package com.nicos.percentageswithanimationcompose

import androidx.annotation.FloatRange
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp.Companion.Infinity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nicos.percentageswithanimationcompose.utils.PercentageTextFormatter.formatPercentageText
import kotlin.math.max

/**
 * @param currentPercentage - The current value of the progress Percentage (current value must be less than or equal to maximum value currentValue >= 0 && currentValue <= maximumValue)
 * @param maxPercentage - The maximum value of the progress Percentage (maximum value must be greater than or equal to 0)
 * @param circularSize - The size of the circle, default value is 100
 * @param listOfColors - The list of gradient colors (must contain at least two colors)
 * @param percentageAnimationDuration - The duration of the animation, default value is 1500ms
 * @param centerTextStyle - The text style of the center text
 * @param showPercentageSymbol - The symbol shown after the value when showPercentageSymbol is false, default value is "%"
 * */
@Composable
fun GradientCirclePercentage(
    @FloatRange(
        from = 0.0,
        to = Float.MAX_VALUE.toDouble()
    )
    currentPercentage: Float,
    @FloatRange(
        from = 0.0,
        to = Float.MAX_VALUE.toDouble()
    )
    maxPercentage: Float,
    listOfColors: List<Color>,
    circularSize: Int = 100,
    percentageAnimationDuration: Int = 1_500,
    centerTextStyle: TextStyle,
    showPercentageSymbol: Boolean = false,
) {
    assert(currentPercentage >= 0) { "Current value must be greater than or equal to 0" }
    assert(currentPercentage <= maxPercentage) { "Current value must be less than or equal to maximum value" }
    assert(percentageAnimationDuration >= 0) { "Percentage animation duration must be greater than or equal to 0" }
    assert(circularSize >= 0) { "Circular size must be greater than or equal to 0" }
    assert(listOfColors.size >= 2) { "listOfColors must contain at least two colors, was ${listOfColors.size}" }

    val modifier = Modifier
    var percentage by remember { mutableFloatStateOf(0F) }
    var actualPercentage by remember { mutableFloatStateOf(0F) }
    val progressAnimation by animateFloatAsState(
        targetValue = if (percentage != Infinity.value && !percentage.isNaN()) percentage else 0F,
        animationSpec = tween(
            durationMillis = percentageAnimationDuration,
            easing = FastOutSlowInEasing
        ),
        label = "GradientCirclePercentage",
    )
    Box(
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = modifier.size(circularSize.dp)) {
            actualPercentage =
                (progressAnimation / 360) * maxPercentage // still drives the text; goes away with #17
            // progressAnimation is 0..360, so this is the exact fill for this frame: no lag, no rounding
            val fraction = progressAnimation / 360f
            // Fill from the bottom up by flipping the gradient, instead of rotating the whole layout
            val gradientShader = Brush.linearGradient(
                colors = listOfColors,
                start = Offset(
                    0f,
                    size.height
                ),                                    // bottom of the circle
                end = Offset(
                    0f,
                    size.height - max(size.height * fraction, 1f)
                ),    // fill level; at least 1px so start != end at 0%
            )

            drawCircle(
                brush = gradientShader,
                radius = size.minDimension / 2,
            )
        }
        Box(
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = formatPercentageText(actualPercentage, showPercentageSymbol),
                style = centerTextStyle,
            )
        }
    }
    LaunchedEffect(key1 = currentPercentage, key2 = maxPercentage) {
        if (maxPercentage > 0) {
            percentage = (currentPercentage * 360) / maxPercentage
        } else {
            percentage = 0f
        }
    }
}

@Preview
@Composable
private fun GradientCirclePercentagePreview() {
    GradientCirclePercentage(
        currentPercentage = 50F,
        maxPercentage = 100F,
        listOfColors = mutableListOf(
            Color.Green,
            (Color.Green.copy(alpha = 0.3f)),
            Color.White
        ),
        centerTextStyle = TextStyle(color = Color.Red, fontSize = 15.sp),
    )
}