package com.example.paths.bezierMotion

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathMeasure
import androidx.compose.ui.graphics.addSvg
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
@Preview(showBackground = true)
fun PathAnimation3(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Magenta.copy(0.3f))
    ){
        val scaleAnimation = remember { Animatable(0f) }
        val progress = remember { Animatable(0f) }
        var alpha by remember { mutableFloatStateOf(1f) }
        LaunchedEffect(Unit) {
            while (true) {
                progress.snapTo(0f)
                delay(1000)
                alpha = 1f
                launch {
                    delay(18_000)
                    scaleAnimation.animateTo(
                        targetValue = 1f,
                        animationSpec = tween(900)
                    )
                    alpha = 0f
                    scaleAnimation.animateTo(
                        targetValue = 0f,
                        animationSpec = tween(300)
                    )
                }
                progress.animateTo(
                    targetValue = 1f,
                    animationSpec = tween(19_000, easing = CubicBezierEasing(.5f, .0f, .5f, .3f))
                )
                progress.animateTo(0f)
                delay(2000)
            }
        }
        Box(
            modifier = Modifier
                .alpha(alpha)
                .scale(lerp(1f,3.5f,scaleAnimation.value))
        ){
            gradientsLists
                .forEach { color ->
                    SingleShape1(
                        progress=progress,
                        colors = color
                    )
                }
        }
    }
}

@Composable
fun SingleShape1(
    modifier: Modifier= Modifier,
    progress: Animatable<Float, AnimationVector1D>,
    colors: List<Color>
){
    var coord by remember { mutableStateOf(Offset.Zero) }
    val position by animateOffsetAsState(
        targetValue = coord,
        animationSpec = spring(
            stiffness = Spring.StiffnessVeryLow
        )
    )
    Box(
        modifier=modifier
            .fillMaxSize()
            .drawWithCache{
                val canvasSize = size
                val path=createPath(
                    points = buildList {
                        add(Offset(0.5f, 0.5f))
                        add(Offset(0.5f, 0.2f))
                        add(Offset(0.8f, 0.2f))
                        add(Offset(0.8f, 0.5f))
                        add(Offset(Random.nextFloat(0.5f, 0.6f), Random.nextFloat(0.8f, 0.9f)))
                        repeat(20) {
                            add(
                                Offset(
                                    Random.nextFloat(-0.1f, 1.1f), // clamp within screen
                                    Random.nextFloat(-0.1f, 1.1f)
                                )
                            )
                        }
                    }.map {
                        it*canvasSize
                    }
                ).first

                val pathMeasure= PathMeasure()
                pathMeasure.setPath(path,false)

                val length= 18_000f
                val segment = Path()
                pathMeasure.getSegment(0f,length,segment)
                pathMeasure.setPath(segment,false)

                onDrawBehind {
                    val distance = pathMeasure.length * progress.value
                    coord= pathMeasure.getPosition(distance) - center
                }
            }
    ){
        Box(
            modifier= Modifier
                .size(64.dp)
                .align(Alignment.Center)
                .offset{
                    position.toIntOffset()
                }
                .drawBehind{
                    val path =Path().apply {
                        addSvg("M75,75 L75,175 L175,175 l-50,-50 l50,-50 z")
                    }
                    drawPath(
                        path=path,
                        brush = Brush.verticalGradient(
                            colors=colors,
                        )
                    )
                }
        )
    }
}