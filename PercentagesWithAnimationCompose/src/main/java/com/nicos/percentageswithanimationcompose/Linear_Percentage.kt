package com.nicos.percentageswithanimationcompose

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp.Companion.Infinity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun LinearPercentage(
    currentValue: Float,
    maximumValue: Float,
    progressAnimationDuration: Int = 1_500,
    heightPercentageBackground: Int,
    heightPercentage: Int,
    startTextEndPadding: Int = 5,
    endTextStartPadding: Int = 5,
    startTextStyle: TextStyle,
    endTextStyle: TextStyle
) {
    val modifier = Modifier
    var progress by remember { mutableFloatStateOf(0F) }
    var actualProgress by remember { mutableFloatStateOf(0F) }
    val density = LocalDensity.current
    var maxWidth by remember { mutableStateOf(0.dp) }
    val progressAnimation by animateFloatAsState(
        targetValue = if (progress != Infinity.value) progress else 0F,
        animationSpec = tween(
            durationMillis = progressAnimationDuration,
            easing = FastOutSlowInEasing
        ),
        label = "",
    )
    val actualProgressAnimation by animateFloatAsState(
        targetValue = if (progress != Infinity.value) actualProgress else 0F,
        animationSpec = tween(
            durationMillis = progressAnimationDuration,
            easing = FastOutSlowInEasing
        ),
        label = "",
    )

    Row(
        modifier = modifier
            .height(50.dp)
            .padding(horizontal = 15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = (actualProgressAnimation).toInt().toString(),
            modifier = modifier.padding(end = startTextEndPadding.dp),
            style = startTextStyle
        )
        Box(modifier = modifier.weight(1f)) {
            Box(
                modifier = modifier
                    .height(heightPercentageBackground.dp)
                    .fillMaxWidth()
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
                    modifier = modifier
                        .width(progressAnimation.dp)
                        .height(heightPercentage.dp)
                        .align(Alignment.CenterStart)
                        .background(Color.Blue)
                )
            }
        }
        Text(
            text = maximumValue.toInt().toString(),
            modifier = modifier
                .padding(start = endTextStartPadding.dp),
            style = endTextStyle
        )
    }
    LaunchedEffect(Unit) {
        progress = (currentValue * maxWidth.value.toInt()) / maximumValue
        actualProgress = progress * maximumValue / maxWidth.value.toInt()
    }
}

@Preview
@Composable
fun LinearPercentagePreview() {
    LinearPercentage(
        currentValue = 50F,
        maximumValue = 100F,
        heightPercentageBackground = 20,
        heightPercentage = 20,
        startTextStyle = TextStyle(color = Color.Blue, fontSize = 15.sp),
        endTextStyle = TextStyle(color = Color.Red, fontSize = 15.sp)
    )
}