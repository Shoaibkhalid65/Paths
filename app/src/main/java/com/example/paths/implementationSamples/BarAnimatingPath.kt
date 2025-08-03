package com.example.paths.implementationSamples

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.addSvg
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
@Preview(showBackground = true)
fun BarPathSample1(){


    val path1= Path().apply {
        addSvg("M3,20L3,9C3,7.895 3.895,7 5,7L6,7C7.105,7 8,7.895 8,9L8,20L9,20L9,4C9,2.895 9.895,2 11,2L12,2C13.105,2 14,2.895 14,4L14,20L15,20L15,12C15,10.895 15.895,10 17,10L18,10C19.105,10 20,10.895 20,12L20,20L22,20C22.552,20 23,20.448 23,21C23,21.552 22.552,22 22,22L2,22C1.448,22 1,21.552 1,21C1,20.448 1.448,20 2,20L3,20ZM11,4L11,20L12,20L12,4L11,4ZM5,9L5,20L6,20L6,9L5,9ZM17,12L17,20L18,20L18,12L17,12Z")
    }
    val pathBounds= path1.getBounds()

    val infiniteTransition= rememberInfiniteTransition()
    val phase by infiniteTransition.animateFloat(
        initialValue = 200f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(5000, easing = LinearEasing)
        )
    )

    Canvas(
        modifier = Modifier.size(500.dp)
    ) {
        withTransform({
            scale(10.5f, pivot = pathBounds.center)
            inset(50f)
        })
        {
            drawPath(
                path1,
                color = Color.Red,
                style = Stroke(
                    width = 1.5f,
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(115f,75f),phase)
                )
            )
        }
    }
}