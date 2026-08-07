package com.nicos.percentageswithanimationcomposeexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nicos.percentageswithanimationcompose.*
import com.nicos.percentageswithanimationcompose.enums.LeftAndRightText
import com.nicos.percentageswithanimationcomposeexample.ui.theme.PercentagesWithAnimationComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PercentagesWithAnimationComposeTheme {
                // Use rememberSaveable to keep the state across configuration changes
                var currentTargetPercentage by rememberSaveable { mutableFloatStateOf(70F) }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = Color(0xFFFAFAFA), // Very subtle off-white for a clean look
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = {
                                currentTargetPercentage = (10..100).random().toFloat()
                            },
                            containerColor = Color(0xFF1E1E1E), // Sleek black FAB
                            contentColor = Color.White,
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Icon(Icons.Rounded.Refresh, contentDescription = "Refresh Animations")
                        }
                    }
                ) { innerPadding ->
                    PercentagesList(
                        modifier = Modifier.padding(innerPadding),
                        percentage = currentTargetPercentage
                    )
                }
            }
        }
    }
}

@Composable
fun PercentagesList(modifier: Modifier = Modifier, percentage: Float) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(
                top = 40.dp,
                bottom = 100.dp,
                start = 24.dp,
                end = 24.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // --- Header ---
        Text(
            text = "Animations",
            style = TextStyle(
                color = Color(0xFF121212),
                fontSize = 34.sp,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = (-1).sp
            ),
            modifier = Modifier.padding(bottom = 50.dp)
        )

        // --- Linear Percentage ---
        SectionTitle("Linear Percentage")
        LinearPercentage(
            currentPercentage = percentage,
            maxPercentage = 100F,
            heightPercentageBackground = 50,
            heightPercentage = 50,
            roundedCornerShapeValue = 25,
            horizontalPadding = 0,
            colorPercentageBackground = Color(0xFFE9ECEF),
            colorPercentage = Color(0xFF3A86FF), // Vibrant Blue
            startTextStyle = TextStyle(
                color = Color(0xFF3A86FF),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            ),
            endTextStyle = TextStyle(
                color = Color.LightGray,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            ),
            leftAndRightText = LeftAndRightText.BOTH,
        )
        Spacer(modifier = Modifier.height(70.dp))

        // --- Circular Percentage ---
        SectionTitle("Circular Percentage")
        CircularPercentage(
            currentPercentage = percentage,
            maxPercentage = 100F,
            centerTextStyle = TextStyle(
                color = Color(0xFFFF006E), // Vibrant Pink
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            ),
        )
        Spacer(modifier = Modifier.height(70.dp))

        // --- Circle Percentage ---
        SectionTitle("Circle Percentage")
        CirclePercentage(
            currentPercentage = percentage,
            maxPercentage = 100F,
            centerTextStyle = TextStyle(
                color = Color(0xFF8338EC), // Deep Purple
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            ),
        )
        Spacer(modifier = Modifier.height(70.dp))

        // --- Gradient Circle Percentage ---
        SectionTitle("Gradient Circle")
        GradientCirclePercentage(
            currentPercentage = percentage,
            maxPercentage = 100F,
            listOfColors = mutableListOf(
                Color.Green,
                (Color.Green.copy(alpha = 0.3f)),
                Color.White
            ),
            centerTextStyle = TextStyle(
                color = Color(0xFFFB5607),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            ),
        )
        Spacer(modifier = Modifier.height(70.dp))

        // --- Wave Percentage ---
        SectionTitle("Wave Percentage")
        WavePercentage(
            currentPercentage = percentage,
            maxPercentage = 100F,
            percentageAnimationDuration = 4_000,
            centerTextStyle = TextStyle(
                color = Color(0xFF00B4D8), // Ocean Blue
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            ),
        )
    }
}

// Reusable clean title to keep code DRY and maintain consistent beautiful typography
@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        style = TextStyle(
            color = Color(0xFF2B2D42),
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 0.5.sp
        )
    )
    Spacer(modifier = Modifier.height(24.dp))
}