package com.nicos.percentageswithanimationcompose

import androidx.annotation.FloatRange
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
import com.nicos.percentageswithanimationcompose.enums.LeftAndRightText

/**
 * @param currentValue - The current value of the Linear Percentage
 * @param maximumValue - The maximum value of the Linear Percentage
 * @param progressAnimationDuration - The duration of the percentage animation, default value is 1500ms
 * @param heightPercentageBackground - The height of the background of the Linear Percentage
 * @param heightPercentage - The height of the Linear Percentage
 * @param colorPercentageBackground - The color of the background of the Linear Percentage
 * @param colorPercentage - The color of the Linear Percentage
 * @param startTextEndPadding - The padding of the start text, default value is 5
 * @param endTextStartPadding - The padding of the end text, default value is 5
 * @param startTextStyle - The style of the start text
 * @param endTextStyle - The style of the end text
 * @param leftAndRightText - The left and right text, default value is NONE
 * */
@Composable
fun LinearPercentage(
    @FloatRange(
        from = 0.0,
        to = Float.MAX_VALUE.toDouble()
    ) currentValue: Float,
    @FloatRange(
        from = 0.0,
        to = Float.MAX_VALUE.toDouble()
    )
    maximumValue: Float,
    progressAnimationDuration: Int = 1_500,
    heightPercentageBackground: Int,
    heightPercentage: Int,
    colorPercentageBackground: Color,
    colorPercentage: Color,
    startTextEndPadding: Int = 5,
    endTextStartPadding: Int = 5,
    startTextStyle: TextStyle,
    endTextStyle: TextStyle,
    leftAndRightText: LeftAndRightText = LeftAndRightText.NONE,
) {
    assert(currentValue <= maximumValue) { "Current value must be less than or equal to maximum value" }
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
        if (leftAndRightText == LeftAndRightText.LEFT_ONLY || leftAndRightText == LeftAndRightText.BOTH)
            LeftText(modifier, actualProgressAnimation, startTextEndPadding, startTextStyle)
        /**
         * Main Functionality - Linear Percentage
         * */
        Box(modifier = modifier.weight(1f)) {
            Box(
                modifier = modifier
                    .height(heightPercentageBackground.dp)
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(21.dp))
                    .background(colorPercentageBackground)
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
                        .background(colorPercentage)
                )
            }
        }
        if (leftAndRightText == LeftAndRightText.RIGHT_ONLY || leftAndRightText == LeftAndRightText.BOTH)
            RightText(modifier, maximumValue, endTextStartPadding, endTextStyle)
    }
    LaunchedEffect(Unit) {
        progress = (currentValue * maxWidth.value.toInt()) / maximumValue
        actualProgress = progress * maximumValue / maxWidth.value.toInt()
    }
}

/**
 * @param modifier - Modifier
 * @param actualProgressAnimation - Actual progress animation
 * @param startTextEndPadding - Start text end padding
 * @param startTextStyle - Start text style
 * */
@Composable
private fun LeftText(
    modifier: Modifier,
    actualProgressAnimation: Float,
    startTextEndPadding: Int,
    startTextStyle: TextStyle
) {
    Text(
        text = (actualProgressAnimation).toInt().toString(),
        modifier = modifier.padding(end = startTextEndPadding.dp),
        style = startTextStyle
    )
}

/**
 * @param modifier - Modifier
 * @param maximumValue - Maximum value
 * @param endTextStartPadding - End text start padding
 * @param endTextStyle - End text style
 * */
@Composable
private fun RightText(
    modifier: Modifier,
    maximumValue: Float,
    endTextStartPadding: Int,
    endTextStyle: TextStyle
) {
    Text(
        text = maximumValue.toInt().toString(),
        modifier = modifier
            .padding(start = endTextStartPadding.dp),
        style = endTextStyle
    )
}

@Preview
@Composable
private fun LinearPercentagePreview() {
    LinearPercentage(
        currentValue = 50F,
        maximumValue = 100F,
        heightPercentageBackground = 20,
        heightPercentage = 20,
        colorPercentageBackground = Color.Red,
        colorPercentage = Color.Blue,
        startTextStyle = TextStyle(color = Color.Blue, fontSize = 15.sp),
        endTextStyle = TextStyle(color = Color.Red, fontSize = 15.sp)
    )
}

@Preview
@Composable
private fun LeftTextPreview() {
    LeftText(
        modifier = Modifier,
        actualProgressAnimation = 50F,
        startTextEndPadding = 5,
        startTextStyle = TextStyle(color = Color.Blue, fontSize = 15.sp)
    )
}

@Preview
@Composable
private fun RightTextPreview() {
    RightText(
        modifier = Modifier,
        maximumValue = 100F,
        endTextStartPadding = 5,
        endTextStyle = TextStyle(color = Color.Red, fontSize = 15.sp)
    )
}