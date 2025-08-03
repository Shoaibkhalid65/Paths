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
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.tooling.preview.Preview

@Composable
@Preview(showBackground = true)
fun CustomPoints(){
//    triangle
    val triangle=listOf(
        Offset(0f,150f),
        Offset(150f,150f),
        Offset(75f,300f),
        Offset(0f,150f)

    )
//    diamond
    val diamond=listOf(
        Offset(0f,75f),
        Offset(150f,0f),
        Offset(300f,75f),
        Offset(150f,150f),
        Offset(0f,75f),

        )
//    trapezoid
    val trapezoid=listOf<Offset>(
        Offset(50f,0f),
        Offset(250f,0f),
        Offset(300f,150f),
        Offset(0f,150f),
        Offset(50f,0f)
    )
// polygon
    val polygon: List<Offset> = listOf(
        Offset(250f,0f),
        Offset(0f,250f),
        Offset(125f,500f),
        Offset(375f,500f),
        Offset(500f,250f),
        Offset(250f,0f),
    )
//    parallelogram
    val parallelogram=listOf(
        Offset(100f,0f),
        Offset(550f,0f),
        Offset(450f,200f),
        Offset(0f,200f),
        Offset(100f,0f),
    )
//    back arrow
    val backArrow=listOf(
        Offset(0f,200f),
        Offset(200f,0f),
        Offset(200f,150f),
        Offset(500f,150f),
        Offset(500f,250f),
        Offset(200f,250f),
        Offset(200f,400f),
        Offset(0f,200f),

        )
//    star
    val star=listOf(
        Offset(250f,0f),
        Offset(200f,200f),
        Offset(0f,200f),
        Offset(150f,300f),
        Offset(100f,500f),
        Offset(250f,350f),
        Offset(400f,500f),
        Offset(350f,300f),
        Offset(500f,200f),
        Offset(300f,200f),
        Offset(250f,0f),
    )
    val transition = rememberInfiniteTransition()
    val infiniteAnimatedPhase by transition.animateFloat(
        initialValue = 300f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 600, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )
    val brush = Brush.horizontalGradient(listOf(
        Color(0xFF40E0D0),
        Color(0xFFFF8C00),
        Color(0xFFFF0080),
    ))
    Canvas(
        modifier = Modifier
            .fillMaxSize()
    ) {
        withTransform({
            inset(200f)
            rotate(0f, pivot = Offset(250f,200f))
            scale(1f,1f, pivot = Offset(250f,200f))
        })
        {
            drawPoints(
                points = trapezoid,
                pointMode = PointMode.Polygon,
                brush=brush,
                strokeWidth = 5f,
                cap = StrokeCap.Round,
                pathEffect = PathEffect.dashPathEffect(floatArrayOf(400f,200f),infiniteAnimatedPhase)

            )
        }
    }
}