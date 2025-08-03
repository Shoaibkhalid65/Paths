package com.example.paths.implementationSamples

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.foundation.Canvas
import androidx.compose.ui.tooling.preview.Preview

@Composable
@Preview(showBackground = true)
fun TrianglePath() {
    Canvas(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
    ) {
        val path = Path().apply {
            moveTo(175f, 100f)
            lineTo(100f, 250f)
            lineTo(250f, 250f)
            close()
        }

        drawPath(
            path = path,
            color = Color.Magenta
        )
    }
}



