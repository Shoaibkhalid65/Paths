package com.example.paths.frasierLogo

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.PathMeasure
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.addSvg
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.tooling.preview.Preview

@Composable
@Preview(showBackground = true)
fun FrasierPath(){
    val path= Path().apply {
        addSvg("M60,1000 l25,0 l0,-250 l50,0 l0,250 l16,0 l0,-100 l40,0 l0,100 l24,0 l0,-340 l10,-20 l30,0 l10,20 l0,290 l30,0 l0,-200 l80,0 l0,120 l30,0 l0,-240 l80,0 l0,320 l20,0 l0,-240 l40,-40 l40,40 l0,90 l45,0 q30,-130 5,-250 c-7,-16 -20,0 -40,-20 q-4,-13 -25,-15 q21,2 25,-15 c7,-16 20,0 40,-20 c11,-23 31,-18 31,-55 c0,33 20,23 31,55 c7,16 20,0 40,20 q4,13 25,15 q-21,-2 -25,15 c-7,16 -20,0 -40,20 q-30,90 -5,160 l40,0 l0,40 l120,0 l0,170 l30,0 l0,-240,l20,-20,l0,-40 l20,0 l10,-60 l10,60,l20,0 l0,40 l20,20 l0,340,l20,0 l0,-200,l10,0")
    }
    val pathMeasure= PathMeasure()
    pathMeasure.setPath(path = path, forceClosed = false)
    val infiniteTransition= rememberInfiniteTransition()
    val phase by infiniteTransition.animateFloat(
        initialValue = pathMeasure.length,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(8000, easing = LinearEasing)
        )
    )
    Canvas(
        modifier = Modifier.fillMaxSize()
    ) {
        scale(0.7f) {
            drawPath(
                path = path,
                color = Color.Black,
                style = Stroke(
                    width = 7f,
                    cap = StrokeCap.Round,
                    pathEffect = PathEffect.dashPathEffect(
                        intervals = floatArrayOf(pathMeasure.length,pathMeasure.length),
                        phase=phase
                    )
                )
            )
        }
    }
}