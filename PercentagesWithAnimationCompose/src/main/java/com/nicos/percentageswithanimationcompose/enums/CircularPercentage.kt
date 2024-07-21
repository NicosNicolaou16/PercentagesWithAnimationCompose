package com.nicos.percentageswithanimationcompose.enums

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
import androidx.compose.ui.unit.Dp.Companion.Infinity
import androidx.compose.ui.unit.dp

@Composable
fun CircularPercentage(
    currentValue: Float = 70F,
    maximumValue: Float = 100F,
    circularSize: Int = 200,
    percentageAnimationDuration: Int = 1_500,
) {
    var percentage by remember { mutableFloatStateOf(0F) }
    val progressAnimation by animateFloatAsState(
        targetValue = if (percentage != Infinity.value) percentage else 0F,
        animationSpec = tween(
            durationMillis = percentageAnimationDuration,
            easing = FastOutSlowInEasing
        ),
        label = "",
    )

    Box(contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier.size(circularSize.dp)) {
            drawCircle(
                color = Color.Red,
                style = Stroke(width = 10f),
                radius = size.minDimension / 2,
                center = Offset(size.width / 2, size.height / 2)
            )

            drawArc(
                color = Color.Blue,
                startAngle = -90f,
                sweepAngle = progressAnimation,
                useCenter = false,
                style = Stroke(width = 10f),
                size = Size(size.width, size.height),
                topLeft = Offset(0f, 0f)
            )
        }
        Text(
            text = "${(percentage / 360 * maximumValue).toInt()}%",
            style = TextStyle(textAlign = TextAlign.Center, color = Color.Red),
        )
    }
    LaunchedEffect(Unit) {
        percentage = currentValue * 360 / maximumValue
    }
}