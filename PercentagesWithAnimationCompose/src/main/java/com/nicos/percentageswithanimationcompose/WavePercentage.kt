package com.nicos.percentageswithanimationcompose

import androidx.annotation.FloatRange
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.EaseInQuad
import androidx.compose.animation.core.EaseOutQuad
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
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
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import kotlin.math.PI
import kotlin.math.sin

/**
 * @param currentPercentage - The current value of the progress Percentage (current value must be less than or equal to maximum value currentValue >= 0 && currentValue <= maximumValue)
 * @param maxPercentage - The maximum value of the progress Percentage (maximum value must be greater than or equal to 0)
 * @param circularSize - The size of the circle, default value is 100
 * @param backgroundColor - The background color of the circle, default value is White
 * @param waveColor - The color of the wave, default value is Green
 * @param percentageAnimationDuration - The duration of the animation, default value is 1500ms
 * @param waveFrequency - The frequency of the wave, default value is 1.5
 * @param waveAmplitude - The amplitude of the wave, default value is 10
 * @param waveAnimationDuration - The duration of the wave animation, default value is 500ms
 * @param continuousWaveAnimationDuration - The duration of the continuous wave animation, default value is 2000ms
 * @param centerTextStyle - The text style of the center text
 * */
@Composable
fun WavePercentage(
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
    circularSize: Int = 100,
    backgroundColor: Color = Color.White,
    waveColor: Color = Color.Green,
    percentageAnimationDuration: Int = 1_500,
    waveFrequency: Float = 1.5f,
    waveAmplitude: Float = 10f,
    waveAnimationDuration: Int = 500,
    continuousWaveAnimationDuration: Int = 2_000,
    centerTextStyle: TextStyle,
) {
    assert(currentPercentage >= 0) { "Current value must be greater than or equal to 0" }
    assert(currentPercentage <= maxPercentage) { "Current value must be less than or equal to maximum value" }
    assert(circularSize >= 0) { "Circular size must be greater than or equal to 0" }
    assert(percentageAnimationDuration >= 0) { "Percentage animation duration must be greater than or equal to 0" }
    assert(waveAnimationDuration >= 0) { "Wave animation duration must be greater than or equal to 0" }
    assert(continuousWaveAnimationDuration > 0) { "Continuous wave animation duration must be greater than 0" }

    val modifier = Modifier
    var actualPercentageToShow by remember { mutableFloatStateOf(0f) }
    val animatedPercentage = remember { Animatable(0f) } // Create an Animatable
    val animatedWaveAmplitude = remember { Animatable(0f) }

    // Cache the wave path when shape-defining parameters change
    val wavePath = remember(
        key1 = waveFrequency,
        key2 = waveAmplitude,
        key3 = maxPercentage
    ) { // Recompute if these change, excludes phase since it animates continuously.
        Path()
    }

    // Animation during percentage change
    LaunchedEffect(key1 = currentPercentage) {
        launch {
            animatedPercentage.animateTo(
                targetValue = currentPercentage,
                animationSpec = tween(durationMillis = percentageAnimationDuration),
            ) {
                actualPercentageToShow = value
            }
            actualPercentageToShow = animatedPercentage.value
        }
        launch {
            animatedWaveAmplitude.animateTo(
                targetValue = 0.2f * waveAmplitude,
                animationSpec = tween(
                    durationMillis = waveAnimationDuration / 2,
                    easing = EaseInQuad
                )
            )
            animatedWaveAmplitude.animateTo(
                targetValue = 0f,
                animationSpec = tween(
                    durationMillis = waveAnimationDuration / 2,
                    easing = EaseOutQuad
                )
            )
        }
    }

    // True when the wave has reached the top
    val isFull = maxPercentage > 0f && animatedPercentage.value >= maxPercentage
    // Fades the wave out when full and back in when the value drops again.
    // Reads the waveAmplitude parameter directly, so changes from the caller apply.
    val baseAmplitude by animateFloatAsState(
        targetValue = if (isFull) 0f else waveAmplitude,
        label = "WaveAmplitude",
    )
    // Endless wave movement, no LaunchedEffect or manual restart needed
    val phase by rememberInfiniteTransition(label = "WavePhase").animateFloat(
        initialValue = 0f,
        targetValue = 2 * PI.toFloat(),
        animationSpec = infiniteRepeatable(
            tween(
                continuousWaveAnimationDuration,
                easing = LinearEasing
            )
        ),
        label = "WavePhase",
    )

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.size(circularSize.dp)) {
            drawCircle(color = waveColor)

            clipPath(
                Path().apply {
                    val radius = size.width / 2
                    addOval(
                        Rect(
                            center = Offset(size.width / 2, size.height / 2),
                            radius = radius
                        )
                    )
                }
            ) {
                wavePath.reset() // Reuse and clear the path
                drawWave(
                    path = wavePath,
                    actualPercentageToShow = actualPercentageToShow,
                    waveFrequency = waveFrequency,
                    waveAmplitude = baseAmplitude + animatedWaveAmplitude.value, // Use modified amplitude
                    wavePhase = phase,  // Use continuous wave phase
                    maxPercentage = maxPercentage
                )
                drawPath(wavePath, color = backgroundColor)
            }
        }
        Text(
            text = actualPercentageToShow.toInt().toString(),
            style = centerTextStyle
        )
    }
}

/**
 * @param path - The path to draw the wave on
 * @param actualPercentageToShow - The actual percentage to show
 * @param waveFrequency - The frequency of the wave
 * @param waveAmplitude - The amplitude of the wave
 * @param wavePhase - The phase of the wave
 * @param maxPercentage - The maximum value of the progress indicator
 * */
private fun DrawScope.drawWave(
    path: Path, //Path as a parameter
    actualPercentageToShow: Float,
    waveFrequency: Float,
    waveAmplitude: Float,
    wavePhase: Float,
    maxPercentage: Float
) {
    val normalizedPercentage =
        if (maxPercentage > 0) 1f - (actualPercentageToShow / maxPercentage) else 1f
    path.apply {
        val fillHeightFromBottom =
            size.height * normalizedPercentage  // Calculate from bottom
        val centerX = size.width / 2
        var previousY =
            fillHeightFromBottom + waveAmplitude * sin(waveFrequency / size.width * 2 * PI * (-centerX) + PI / 2 + wavePhase).toFloat()
        moveTo(0f, previousY)

        val numPoints = 30
        val step = size.width / numPoints
        var previousX = 0f

        for (i in 0..numPoints) {
            val x = i * step
            val y =
                fillHeightFromBottom + waveAmplitude * sin(waveFrequency / size.width * 2 * PI * (x - centerX) + PI / 2 + wavePhase).toFloat()
            if (i > 0) {
                quadraticTo(previousX + step / 2, (y + previousY) / 2, x, y)
            } else {
                moveTo(x, y)
            }
            previousY = y
            previousX = x
        }

        lineTo(size.width, 0f)  // Change: Connect to top-right
        lineTo(0f, 0f)          // Change: Connect to top-left
        close()
    }
}

@Preview
@Composable
private fun WavePercentagePreview() {
    WavePercentage(
        currentPercentage = 70F,
        maxPercentage = 100F,
        centerTextStyle = TextStyle(color = Color.Red, fontSize = 15.sp),
    )
}