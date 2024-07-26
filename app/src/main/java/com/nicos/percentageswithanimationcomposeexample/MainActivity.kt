package com.nicos.percentageswithanimationcomposeexample

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
import com.nicos.percentageswithanimationcompose.LinearPercentage
import com.nicos.percentageswithanimationcompose.CircularPercentage
import com.nicos.percentageswithanimationcompose.GradientCirclePercentage
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
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(90.dp))
        Text(text = "Linear Percentage", style = TextStyle(color = Color.Black, fontSize = 25.sp))
        Spacer(modifier = Modifier.size(15.dp))
        LinearPercentage(
            currentValue = 50F,
            maximumValue = 100F,
            heightPercentageBackground = 50,
            heightPercentage = 50,
            roundedCornerShapeValue = 21,
            horizontalPadding = 15,
            colorPercentageBackground = Color.Red,
            colorPercentage = Color.Blue,
            startTextStyle = TextStyle(color = Color.Blue, fontSize = 15.sp),
            endTextStyle = TextStyle(color = Color.Red, fontSize = 15.sp),
            leftAndRightText = LeftAndRightText.BOTH
        )

        Spacer(modifier = Modifier.size(70.dp))
        Text(text = "Circular Percentage", style = TextStyle(color = Color.Black, fontSize = 25.sp))
        Spacer(modifier = Modifier.size(15.dp))
        CircularPercentage(
            currentValue = 70F,
            maximumValue = 100F,
            centerTextStyle = TextStyle(color = Color.Red, fontSize = 15.sp),
        )

        Spacer(modifier = Modifier.size(70.dp))
        Text(text = "Circle Percentage", style = TextStyle(color = Color.Black, fontSize = 25.sp))
        Spacer(modifier = Modifier.size(15.dp))
        CirclePercentage(
            currentValue = 80F,
            maximumValue = 100F,
            centerTextStyle = TextStyle(color = Color.Red, fontSize = 15.sp),
        )

        Spacer(modifier = Modifier.size(70.dp))
        Text(
            text = "Gradient Circle Percentage",
            style = TextStyle(color = Color.Black, fontSize = 25.sp)
        )
        Spacer(modifier = Modifier.size(15.dp))
        GradientCirclePercentage(
            currentValue = 70F,
            maximumValue = 100F,
            listOfColors = mutableListOf(
                Color.Green,
                (Color.Green.copy(alpha = 0.3f)),
                Color.White
            ),
            centerTextStyle = TextStyle(color = Color.Red, fontSize = 15.sp),
        )
    }
}