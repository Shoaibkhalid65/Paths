package com.example.paths.implementationSamples

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.tooling.preview.Preview

@Composable
@Preview(showBackground = true)
fun RelativeBackArrow() {
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val path = Path().apply {
            moveTo(100f, 300f)
            relativeLineTo(200f, -200f)
            relativeLineTo(0f, 150f)
            relativeLineTo(300f, 0f)
            relativeLineTo(0f, 100f)
            relativeLineTo(-300f, 0f)
            relativeLineTo(0f, 150f)
            relativeLineTo(-200f, -200f)
            close()
        }

        drawPath(
            path = path,
            color = Color.Cyan,
            style = Fill
        )
    }
}
