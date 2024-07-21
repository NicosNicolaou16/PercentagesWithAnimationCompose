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
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp.Companion.Infinity
import androidx.compose.ui.unit.dp

/**
 * @param currentValue - The current value of the Linear Percentage (current value must be less than or equal to maximum value currentValue >= 0 && currentValue <= maximumValue)
 * @param maximumValue - The maximum value of the Linear Percentage (maximum value must be greater than or equal to 0)
 * @param circularSize - The size of the circular percentage
 * @param percentageAnimationDuration - The duration of the animation in milliseconds
 * @param circularPercentageBackgroundColor - The background color of the circular percentage
 * @param circularPercentageColor - The color of the circular percentage
 * @param circularStrokeBackgroundWidth - The width of the background stroke of the circular percentage
 * @param circularStrokeWidth - The width of the stroke of the circular percentage
 * @param centerTextStyle - The text style of the center of the circular percentage
 * */
@Composable
fun CircularPercentage(
    @FloatRange(
        from = 0.0,
        to = Float.MAX_VALUE.toDouble()
    )
    currentValue: Float,
    @FloatRange(
        from = 0.0,
        to = Float.MAX_VALUE.toDouble()
    )
    maximumValue: Float,
    circularSize: Int = 100,
    percentageAnimationDuration: Int = 1_500,
    circularPercentageBackgroundColor: Color = Color.LightGray,
    circularPercentageColor: Color = Color.Black,
    circularStrokeBackgroundWidth: Float = 10F,
    circularStrokeWidth: Float = 10F,
    centerTextStyle: TextStyle,
) {
    val modifier = Modifier
    var percentage by remember { mutableFloatStateOf(0F) }
    var actualPercentage by remember { mutableFloatStateOf(0F) }
    val progressAnimation by animateFloatAsState(
        targetValue = if (percentage != Infinity.value) percentage else 0F,
        animationSpec = tween(
            durationMillis = percentageAnimationDuration,
            easing = FastOutSlowInEasing
        ),
        label = "",
    )
    assert(currentValue >= 0) { "Current value must be greater than or equal to 0" }
    assert(currentValue <= maximumValue) { "Current value must be less than or equal to maximum value" }
    assert(percentageAnimationDuration >= 0) { "Percentage animation duration must be greater than or equal to 0" }
    assert(circularSize >= 0) { "Circular size must be greater than or equal to 0" }

    Box(contentAlignment = Alignment.Center) {
        Canvas(modifier = modifier.size(circularSize.dp)) {
            actualPercentage = (progressAnimation / 360) * maximumValue
            drawCircle(
                color = circularPercentageBackgroundColor,
                style = Stroke(width = circularStrokeBackgroundWidth),
                radius = size.minDimension / 2,
                center = Offset(size.width / 2, size.height / 2)
            )

            drawArc(
                color = circularPercentageColor,
                startAngle = -90f,
                sweepAngle = progressAnimation,
                useCenter = false,
                style = Stroke(width = circularStrokeWidth),
                size = Size(size.width, size.height),
                topLeft = Offset(0f, 0f)
            )
        }
        Text(
            text = actualPercentage.toInt().toString(),
            style = centerTextStyle,
        )
    }
    LaunchedEffect(Unit) {
        percentage = (currentValue * 360) / maximumValue
    }
}