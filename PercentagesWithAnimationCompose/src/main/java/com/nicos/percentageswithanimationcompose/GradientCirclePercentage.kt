package com.nicos.percentageswithanimationcompose

import androidx.annotation.FloatRange
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp.Companion.Infinity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * @param currentValue - The current value of the progress indicator (current value must be less than or equal to maximum value currentValue >= 0 && currentValue <= maximumValue)
 * @param maximumValue - The maximum value of the progress indicator (maximum value must be greater than or equal to 0)
 * @param circularSize - The size of the circle (circular size must be greater than or equal to 0)
 * @param percentageAnimationDuration - The duration of the animation (percentage animation duration must be greater than or equal to 0)
 * @param centerTextStyle - The text style of the center text (center text style must not be null)
 * */
@Composable
fun GradientCirclePercentage(
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
    listOfColors: MutableList<Color>,
    circularSize: Int = 100,
    percentageAnimationDuration: Int = 1_500,
    centerTextStyle: TextStyle,
) {
    assert(currentValue >= 0) { "Current value must be greater than or equal to 0" }
    assert(currentValue <= maximumValue) { "Current value must be less than or equal to maximum value" }
    assert(percentageAnimationDuration >= 0) { "Percentage animation duration must be greater than or equal to 0" }
    assert(circularSize >= 0) { "Circular size must be greater than or equal to 0" }

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
    Box(
        contentAlignment = Alignment.Center, modifier = modifier
            .background(color = Color.White)
            .rotate(180f)
    ) {
        Canvas(modifier = modifier.size(circularSize.dp)) {
            actualPercentage = (progressAnimation / 360) * maximumValue
            val gradientShader = Brush.linearGradient(
                start = Offset.Zero,
                end = Offset(
                    0F,
                    size.height * (actualPercentage / maximumValue), /*actualPercentage * (maximumValue / 100)*/
                ),
                colors = listOfColors,
            )

            drawCircle(
                brush = gradientShader,
                radius = size.maxDimension / 2,
                center = Offset(size.width / 2, size.height / 2)
            )
        }
        Box(
            modifier = modifier
                .rotate(180f)
        ) {
            Text(
                text = actualPercentage.toInt().toString(),
                style = centerTextStyle,
            )
        }
    }
    LaunchedEffect(Unit) {
        percentage = (currentValue * 360) / maximumValue
    }
}

@Preview
@Composable
fun GradientCirclePercentagePreview() {
    GradientCirclePercentage(
        currentValue = 50F,
        maximumValue = 100F,
        listOfColors = mutableListOf(
            Color.Green,
            (Color.Green.copy(alpha = 0.3f)),
            Color.White
        ),
        centerTextStyle = TextStyle(color = Color.Red, fontSize = 15.sp),
    )
}