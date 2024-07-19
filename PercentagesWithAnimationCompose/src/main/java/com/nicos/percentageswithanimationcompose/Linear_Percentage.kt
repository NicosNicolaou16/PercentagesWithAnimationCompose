package com.nicos.percentageswithanimationcompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

@Composable
fun LinearPercentage() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .width(100.dp)
                .height(50.dp)
                .background(Color.Red)

        )
        Box(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .width(50.dp)
                .height(50.dp)
                .zIndex(2f)
                .background(Color.Blue)
        )
    }
}

@Preview
@Composable
fun LinearPercentagePreview() {
    LinearPercentage()
}