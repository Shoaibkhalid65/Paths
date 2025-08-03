package com.example.paths.implementationSamples

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.addSvg
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview

@Composable
@Preview(showBackground = true)
fun ComposeLogo1(){
    val path= Path().apply {
        addSvg("M10,75 L10,30 L30,20 M40,25 L20,35 L20,80 M50,30 L30,40 L30,85 L50,90 L90,70 M30,75 L50,80 L90,60 M30,65 L50,70 L90,50 L90,30 L50,10 M80,55 L80,35 L40,15 M70,60 L70,40 L30,20")
    }
    Canvas(modifier = Modifier.fillMaxSize()) {
        drawPath(
            path=path,
            color = Color.Red,
            style = Stroke(
                width = 3f,
                pathEffect = PathEffect.cornerPathEffect(
                    radius = 3f
                )
            )
        )
    }
}