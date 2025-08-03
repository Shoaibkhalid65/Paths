package com.example.paths.implementationSamples

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview

@Composable
@Preview(showBackground = true)
fun AbsoluteStarShapeShifted() {
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val xOffset = 100f
        val yOffset = 100f

        val star = listOf(
            Offset(250f, 0f),
            Offset(200f, 200f),
            Offset(0f, 200f),
            Offset(150f, 300f),
            Offset(100f, 500f),
            Offset(250f, 350f),
            Offset(400f, 500f),
            Offset(350f, 300f),
            Offset(500f, 200f),
            Offset(300f, 200f),
            Offset(250f, 0f),
        ).map { offset -> Offset(offset.x + xOffset, offset.y + yOffset) }

        val path = Path().apply {
            moveTo(star[0].x, star[0].y)
            for (i in 1 until star.size) {
                lineTo(star[i].x, star[i].y)
            }
            close()
        }

        drawPath(
            path = path,
            color = Color.Blue,
            style = Stroke(
                width = 10f
            )
        )
    }
}

