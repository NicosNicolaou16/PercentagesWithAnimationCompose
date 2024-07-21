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
                end = Offset(0f, progressAnimation * (circularSize / 100)),
                colors = listOf(Color.Green, (Color.Green.copy(alpha = 0.3f)), Color.White),
            )
            /*val gradientShader = Brush.linearGradient(
                end = Offset(0f, progressAnimation - maximumValue / 2),
                start = Offset.Zero,
                colors = listOf(Color.Green, (Color.Green.copy(alpha = 0.3f)), Color.White),
            )*/

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
        centerTextStyle = TextStyle(color = Color.Red, fontSize = 15.sp),
    )
}