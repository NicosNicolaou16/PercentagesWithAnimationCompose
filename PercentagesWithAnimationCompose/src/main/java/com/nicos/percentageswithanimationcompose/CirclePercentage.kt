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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp.Companion.Infinity
import androidx.compose.ui.unit.dp

/**
 * @param currentValue - The current value of the progress indicator (current value must be less than or equal to maximum value currentValue >= 0 && currentValue <= maximumValue)
 * @param maximumValue - The maximum value of the progress indicator (maximum value must be greater than or equal to 0)
 * @param circleSize - The size of the circle (circle size must be greater than or equal to 0)
 * @param percentageAnimationDuration - The duration of the animation (percentage animation duration must be greater than or equal to 0)
 * @param circlePercentageBackgroundColor - The background color of the circle (circle percentage background color must not be null)
 * @param circlePercentageColor - The color of the circle (circle percentage color must not be null)
 * @param circleStrokeBackgroundWidth - The width of the circle stroke (circle stroke background width must be greater than 0)
 * @param centerTextStyle - The text style of the center text (center text style must not be null)
 * */
@Composable
fun CirclePercentage(
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
    circleSize: Int = 100,
    percentageAnimationDuration: Int = 1_500,
    circlePercentageBackgroundColor: Color = Color.LightGray,
    circlePercentageColor: Color = Color.Black,
    circleStrokeBackgroundWidth: Float = 10F,
    centerTextStyle: TextStyle,
) {
    assert(currentValue >= 0) { "Current value must be greater than or equal to 0" }
    assert(currentValue <= maximumValue) { "Current value must be less than or equal to maximum value" }
    assert(percentageAnimationDuration >= 0) { "Percentage animation duration must be greater than or equal to 0" }
    assert(circleSize >= 0) { "Circular size must be greater than or equal to 0" }
    assert(circleStrokeBackgroundWidth > 0) { "Circle stroke background width must be greater than 0" }

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

    Box(contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier.size(circleSize.dp)) {
            actualPercentage = (progressAnimation / 360) * maximumValue
            drawCircle(
                color = circlePercentageBackgroundColor,
                style = Stroke(width = circleStrokeBackgroundWidth),
                radius = size.minDimension / 2,
                center = Offset(size.width / 2, size.height / 2)
            )

            drawArc(
                color = circlePercentageColor,
                startAngle = -90f,
                sweepAngle = progressAnimation,
                useCenter = true,
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

@Preview
@Composable
private fun CirclePercentagePreview() {
    CirclePercentage(
        currentValue = 50F,
        maximumValue = 100F,
        centerTextStyle = TextStyle(
            color = Color.Black,
            textAlign = TextAlign.Center
        )
    )
}