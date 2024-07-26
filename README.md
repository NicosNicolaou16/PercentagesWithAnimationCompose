# Percentages with Animation Compose

This library built to give to other developers an easy way to implement <br />
Support me and I will appreciate if you provide me your feedback(s).<br />

The library contain/features:

-

### Versioning

Gradle Version 8.5.1 <br />
Kotlin Version 2.0.0 <br />
JDK Version 17 <br />
Minimum SDK 27 <br />
Target SDK 34 <br />
Build Tool Version 34 <br />

## IMPORTANT NOTE

THE BETA RELEASES MAYBE CONTAIN MAJOR/MINOR CHANGES

## Basic Configuration

### Groovy

```Groovy
implementation 'com.github.NicosNicolaou16:percentagesWithAnimationCompose:1.0.0'
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
implementation("com.github.NicosNicolaou16:percentagesWithAnimationCompose:1.0.0")
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
percentagesWithAnimationComposeVersion = "1.0.0"

[libraries]
# other libraries here...
image-picker-android = { group = "com.github.NicosNicolaou16", name = "percentagesWithAnimationCompose", version.ref = "percentagesWithAnimationComposeVersion" }
```

### Linear Percentage

| Parameters                    | Description                                                                                                                                                 |
|-------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `currentPercentage`           | The current value of the Linear Percentage (current value must be less than or equal to maximum value currentValue >= 0 && currentValue <= maximumValue)    |
| `maximumPercentage`           | The maximum value of the Linear Percentage (maximum value must be greater than or equal to current value maximumValue >= 0 && maximumValue >= currentValue) |
| `percentageAnimationDuration` | The duration of the percentage animation, default value is 1500ms                                                                                           |
| `heightPercentageBackground`  | The height of the background of the Linear Percentage                                                                                                       |
| `heightPercentage`            | The height of the Linear Percentage                                                                                                                         |
| `colorPercentageBackground`   | The color of the background of the Linear Percentage                                                                                                        |
| `colorPercentage`             | The color of the Linear Percentage                                                                                                                          |
| `startTextEndPadding`         | The padding of the start text, default value is 5                                                                                                           |
| `endTextStartPadding`         | The padding of the end text, default value is 5                                                                                                             |
| `roundedCornerShapeValue`     | The rounded corner shape value, default value is 0                                                                                                          |
| `horizontalPadding`           | The horizontal padding left and right of the Linear Percentage, default value is 0                                                                          |
| `startTextStyle`              | The style of the start/lest text                                                                                                                            |
| `endTextStyle`                | The style of the end/right text                                                                                                                             |
| `leftAndRightText`            | The left and right text, accepted values are LEFT_ONLY, RIGHT_ONLY, BOTH and NONE, default value is NONE                                                    |

### Circular Percentage

| Parameters                          | Description                                                                                                                                                 |
|-------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `currentPercentage`                 | The current value of the Linear Percentage (current value must be less than or equal to maximum value currentValue >= 0 && currentValue <= maximumValue)    |
| `maximumPercentage`                 | The maximum value of the Linear Percentage (maximum value must be greater than or equal to current value maximumValue >= 0 && maximumValue >= currentValue) |
| `circularSize`                      | The size of the circular percentage, default value is 100                                                                                                   |
| `percentageAnimationDuration`       | The duration of the animation in milliseconds, default value is 1500ms                                                                                      |
| `circularPercentageBackgroundColor` | The background color of the circular percentage, default value is LightGray                                                                                 |
| `circularPercentageColor`           | The color of the circular percentage, default value is Black                                                                                                |
| `circularStrokeBackgroundWidth`     | The width of the background stroke of the circular percentage, default value is 10                                                                          |
| `circularStrokeWidth`               | The width of the stroke of the circular percentage, default value is 10                                                                                     |
| `centerTextStyle`                   | The text style of the center of the circular percentage                                                                                                     |
