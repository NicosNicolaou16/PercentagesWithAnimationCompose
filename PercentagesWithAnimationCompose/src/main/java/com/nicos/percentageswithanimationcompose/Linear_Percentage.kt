package com.nicos.percentageswithanimationcompose

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp.Companion.Infinity
import androidx.compose.ui.unit.dp


@Composable
fun LinearPercentage(
    currentValue: Float,
    maximumValue: Float,
) {
    var progress by remember { mutableFloatStateOf(0F) }
    val density = LocalDensity.current
    var maxWidth by remember { mutableStateOf(0.dp) }
    val progressAnimDuration = 1_500
    val progressAnimation by animateFloatAsState(
        targetValue = if (progress != Infinity.value) progress else 0F,
        animationSpec = tween(durationMillis = progressAnimDuration, easing = FastOutSlowInEasing),
        label = "",
    )

    Box(
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(shape = RoundedCornerShape(21.dp))
            .background(Color.Red)
            .onGloballyPositioned {
                maxWidth = with(density) {
                    it.size.width.toDp()
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .width(progressAnimation.dp)
                .height(50.dp)
                .background(Color.Blue)
        )
    }
    LaunchedEffect(Unit) {
        progress = (currentValue * maxWidth.value.toInt()) / maximumValue
    }
}

@Preview
@Composable
fun LinearPercentagePreview() {
    LinearPercentage(currentValue = 50F, maximumValue = 100F)
}