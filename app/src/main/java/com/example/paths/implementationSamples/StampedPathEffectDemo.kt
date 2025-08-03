package com.example.paths.implementationSamples

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StampedPathEffectStyle
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview

@Composable
@Preview(showBackground = true)
fun StampedPathEffectDemo() {
    val path = remember {
        Path().apply {
            moveTo(100f, 100f)
            quadraticTo(300f, 0f, 500f, 300f)
        }
    }
    val stampShape = remember {
        Path().apply {
            addOval(Rect(Offset.Zero, Size(width = 30f, height = 30f)))

        }
    }

    val infiniteTransition = rememberInfiniteTransition()
    val phase by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 200f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    Canvas(modifier = Modifier.fillMaxSize()) {
        drawPath(
            path = path,
            color = Color.LightGray,
            style = Stroke(width = 2f)
        )
        drawPath(
            path = path,
            color = Color.Red,
            style = Stroke(
                width = 2f,
                pathEffect = PathEffect.stampedPathEffect(
                    shape = stampShape,
                    advance = 50f,
                    phase = phase,
                    style = StampedPathEffectStyle.Rotate// Try Morph or Translate too!
                )
            )
        )
    }
}
