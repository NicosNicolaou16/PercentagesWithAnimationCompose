# Percentages with Animation Compose

This library is designed to provide developers with an easy way to implement percentages with custom
Compose views and animations, including linear, circular, circle, and gradient circle
percentages. <br />
Your support is greatly appreciated, and I would be grateful if you could share your
feedback. <br />

The library contains the following features:

- Linear Percentage.
- Circular Percentage.
- Circle Percentage.
- Gradient Circle Percentage.
- All with customizable options.

**Reasons to use this library:**

- **Easy Integration:** Quickly implement percentage displays with minimal setup in your Compose
  projects.
- **Multiple View Types:** Support for various percentage display styles, including linear,
  circular, and gradient circle percentages.
- **Customizable:** Highly customizable to match your app's design and user interface needs.
- **Smooth Animations:** Enjoy smooth, animated transitions for percentage changes, improving user
  experience.
- **Time-Saving:** Reduces development time by providing ready-to-use components for percentage
  representation.

### Versioning

Gradle Version 8.9.1 <br />
Kotlin Version 2.1.20 <br />
JDK Version 17 <br />
Minimum SDK 27 <br />
Target SDK 35 <br />
Build Tool Version 35.0.1 <br />

## IMPORTANT NOTE

THE BETA RELEASES MAY CONTAIN MAJOR OR MINOR CHANGES.

## Basic Configuration (Gradle Dependencies)

[![](https://jitpack.io/v/NicosNicolaou16/PercentagesWithAnimationCompose.svg)](https://jitpack.io/#NicosNicolaou16/PercentagesWithAnimationCompose)

### Groovy

```Groovy
implementation 'com.github.NicosNicolaou16:PercentagesWithAnimationCompose:1.3.0'
```

```Groovy
allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
```

### Kotlin DSL

```Kotlin
implementation("com.github.NicosNicolaou16:PercentagesWithAnimationCompose:1.3.0")
```

```Kotlin
dependencyResolutionManagement {
    //...
    repositories {
        //...
        maven { url = uri("https://jitpack.io") }
    }
}
```

### libs.versions.toml

```toml
[versions]
# other versions here...
percentagesWithAnimationComposeVersion = "1.3.0"

[libraries]
# other libraries here...
percentages-with-animation-compose = { group = "com.github.NicosNicolaou16", name = "PercentagesWithAnimationCompose", version.ref = "percentagesWithAnimationComposeVersion" }
```

```Kotlin
dependencyResolutionManagement {
    //...
    repositories {
        //...
        maven { url = uri("https://jitpack.io") }
    }
}
```

<p align="left">
  <a title="simulator_image"><img src="screenshots/Screenshot_20240728_152344.png" height="530" width="250"></a>
  <a title="simulator_image"><img src="screenshots/example_gif1.gif" height="530" width="250"></a>
</p>

### Linear Percentage

| Parameters                              | Description                                                                                                                                                          |
|-----------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `currentPercentage`                     | The current value of the Linear progress Percentage (current value must be less than or equal to maximum value currentValue >= 0 && currentValue <= maximumValue)    |
| `maxPercentage`                         | The maximum value of the Linear progress Percentage (maximum value must be greater than or equal to current value maximumValue >= 0 && maximumValue >= currentValue) |
| `percentageAnimationDuration`           | The duration of the percentage animation, default value is 1500ms                                                                                                    |
| `heightPercentageBackground`            | The height of the background of the Linear Percentage                                                                                                                |
| `heightPercentage`                      | The height of the Linear Percentage                                                                                                                                  |
| `colorPercentageBackground`             | The color of the background of the Linear Percentage                                                                                                                 |
| `colorPercentage`                       | The color of the Linear Percentage                                                                                                                                   |
| `startTextEndPadding`                   | The padding of the start text, default value is 5                                                                                                                    |
| `endTextStartPadding`                   | The padding of the end text, default value is 5                                                                                                                      |
| `roundedCornerShapeValue`               | The rounded corner shape value, default value is 0                                                                                                                   |
| `horizontalPadding`                     | The horizontal padding left and right of the Linear Percentage, default value is 0                                                                                   |
| `startTextStyle`                        | The style of the start/lest text (Optional), default value TextStyle(color = Color.Black)                                                                            |
| `endTextStyle`                          | The style of the end/right text (Optional), default value TextStyle(color = Color.Black)                                                                             |
| `leftAndRightText`                      | The left and right text, accepted values are `LEFT_ONLY`, `RIGHT_ONLY`, `BOTH` and `NONE`, default value is `NONE`                                                   |
| `showPercentageOnLinearPercentage`      | The percentage on Linear Percentage enabled the text percent on percentage view, default value false                                                                 |
| `percentageOnLinearPercentageTextStyle` | The text style of the percentage on Linear Percentage (Optional), default value TextStyle(color = Color.Black)                                                       |

### Circular Percentage

| Parameters                          | Description                                                                                                                                                   |
|-------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `currentPercentage`                 | The current value of the progress Percentage (current value must be less than or equal to maximum value currentValue >= 0 && currentValue <= maximumValue)    |
| `maxPercentage`                     | The maximum value of the progress Percentage (maximum value must be greater than or equal to current value maximumValue >= 0 && maximumValue >= currentValue) |
| `circularSize`                      | The size of the circular percentage, default value is 100                                                                                                     |
| `percentageAnimationDuration`       | The duration of the animation in milliseconds, default value is 1500ms                                                                                        |
| `circularPercentageBackgroundColor` | The background color of the circular percentage, default value is LightGray                                                                                   |
| `circularPercentageColor`           | The color of the circular percentage, default value is Black                                                                                                  |
| `circularStrokeBackgroundWidth`     | The width of the background stroke of the circular percentage, default value is 10                                                                            |
| `circularStrokeWidth`               | The width of the stroke of the circular percentage, default value is 10                                                                                       |
| `centerTextStyle`                   | The text style of the center of the circular percentage                                                                                                       |

### Circle Percentage

| Parameters                          | Description                                                                                                                                                   |
|-------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `currentPercentage`                 | The current value of the progress Percentage (current value must be less than or equal to maximum value currentValue >= 0 && currentValue <= maximumValue)    |
| `maxPercentage`                     | The maximum value of the progress Percentage (maximum value must be greater than or equal to current value maximumValue >= 0 && maximumValue >= currentValue) |
| `circularSize`                      | The size of the circular percentage, default value is 100                                                                                                     |
| `percentageAnimationDuration`       | The duration of the animation in milliseconds, default value is 1500ms                                                                                        |
| `circularPercentageBackgroundColor` | The background color of the circular percentage, default value is LightGray                                                                                   |
| `circularPercentageColor`           | The color of the circular percentage, default value is Black                                                                                                  |
| `circularStrokeBackgroundWidth`     | The width of the background stroke of the circular percentage, default value is 10                                                                            |
| `centerTextStyle`                   | The text style of the center of the circular percentage                                                                                                       |

### Gradient Circle Percentage

| Parameters                    | Description                                                                                                                                                   |
|-------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `currentPercentage`           | The current value of the progress Percentage (current value must be less than or equal to maximum value currentValue >= 0 && currentValue <= maximumValue)    |
| `maxPercentage`               | The maximum value of the progress Percentage (maximum value must be greater than or equal to current value maximumValue >= 0 && maximumValue >= currentValue) |
| `circularSize`                | The size of the circular percentage, default value is 100                                                                                                     |
| `listOfColors`                | The list of gradient colors (list of colors must not be empty)                                                                                                |
| `percentageAnimationDuration` | The duration of the animation, default value is 1500ms                                                                                                        |
| `centerTextStyle`             | The text style of the center text                                                                                                                             |

### Wave Percentage

| Parameters                        | Description                                                                                                                                                |
|-----------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `currentPercentage`               | The current value of the progress Percentage (current value must be less than or equal to maximum value currentValue >= 0 && currentValue <= maximumValue) |
| `maxPercentage`                   | The maximum value of the progress Percentage (maximum value must be greater than or equal to 0)                                                            |
| `circularSize`                    | The size of the wave circle percentage, default value is 100                                                                                               |
| `backgroundColor`                 | The background color of the circle, default value is White                                                                                                 |
| `waveColor`                       | The color of the wave, default value is Green                                                                                                              |
| `percentageAnimationDuration`     | The duration of the animation, default value is 1500ms                                                                                                     |
| `waveFrequency`                   | The frequency of the wave, default value is 1.5                                                                                                            |
| `waveAmplitude`                   | The amplitude of the wave, default value is 10                                                                                                             |
| `waveAnimationDuration`           | The duration of the wave animation, default value is 500ms                                                                                                 |
| `waveAnimationAmplitudeFactor`    | The factor to multiply the wave amplitude, default value is 0.2f                                                                                           |
| `continuousWaveAnimationDuration` | The duration of the continuous wave animation, default value is 2000ms                                                                                     |
| `centerTextStyle`                 | The text style of the center text                                                                                                                          |

### Example

```Kotlin
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nicos.percentageswithanimationcompose.CirclePercentage
import com.nicos.percentageswithanimationcompose.CircularPercentage
import com.nicos.percentageswithanimationcompose.GradientCirclePercentage
import com.nicos.percentageswithanimationcompose.LinearPercentage
import com.nicos.percentageswithanimationcompose.WavePercentage
import com.nicos.percentageswithanimationcompose.enums.LeftAndRightText
import com.nicos.percentageswithanimationcomposeexample.ui.theme.PercentagesWithAnimationComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PercentagesWithAnimationComposeTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                ) { innerPadding ->
                    PercentagesWithAnimationCompose(innerPadding = innerPadding)
                }
            }
        }
    }
}

@Composable
fun PercentagesWithAnimationCompose(innerPadding: PaddingValues) {
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
            .verticalScroll(
                state = rememberScrollState()
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(90.dp))
        Text(text = "Linear Percentage", style = TextStyle(color = Color.Black, fontSize = 25.sp))
        Spacer(modifier = Modifier.size(15.dp))
        LinearPercentage(
            currentPercentage = 50F,
            maxPercentage = 100F,
            heightPercentageBackground = 50,
            heightPercentage = 50,
            roundedCornerShapeValue = 21,
            horizontalPadding = 15,
            colorPercentageBackground = Color.Red,
            colorPercentage = Color.Blue,
            startTextStyle = TextStyle(color = Color.Blue, fontSize = 15.sp),
            endTextStyle = TextStyle(color = Color.Red, fontSize = 15.sp),
            leftAndRightText = LeftAndRightText.BOTH,
        )

        Spacer(modifier = Modifier.size(70.dp))
        Text(text = "Circular Percentage", style = TextStyle(color = Color.Black, fontSize = 25.sp))
        Spacer(modifier = Modifier.size(15.dp))
        CircularPercentage(
            currentPercentage = 70F,
            maxPercentage = 100F,
            centerTextStyle = TextStyle(color = Color.Red, fontSize = 15.sp),
        )

        Spacer(modifier = Modifier.size(70.dp))
        Text(text = "Circle Percentage", style = TextStyle(color = Color.Black, fontSize = 25.sp))
        Spacer(modifier = Modifier.size(15.dp))
        CirclePercentage(
            currentPercentage = 80F,
            maxPercentage = 100F,
            centerTextStyle = TextStyle(color = Color.Red, fontSize = 15.sp),
        )

        Spacer(modifier = Modifier.size(70.dp))
        Text(
            text = "Gradient Circle Percentage",
            style = TextStyle(color = Color.Black, fontSize = 25.sp)
        )
        Spacer(modifier = Modifier.size(15.dp))
        GradientCirclePercentage(
            currentPercentage = 70F,
            maxPercentage = 100F,
            listOfColors = mutableListOf(
                Color.Green,
                (Color.Green.copy(alpha = 0.3f)),
                Color.White
            ),
            centerTextStyle = TextStyle(color = Color.Red, fontSize = 15.sp),
        )

        Spacer(modifier = Modifier.size(70.dp))
        Text(
            text = "Wave Percentage",
            style = TextStyle(color = Color.Black, fontSize = 25.sp)
        )
        Spacer(modifier = Modifier.size(15.dp))
        WavePercentage(
            currentPercentage = 70F,
            maxPercentage = 100F,
            percentageAnimationDuration = 5_000,
            centerTextStyle = TextStyle(color = Color.Red, fontSize = 15.sp),
        )
        Spacer(modifier = Modifier.size(70.dp))
    }
}
```