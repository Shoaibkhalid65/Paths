package com.example.paths.bezierMotion

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path

fun Path.moveTo(offset: Offset) = moveTo(offset.x, offset.y)
fun Path.lineTo(offset: Offset) = lineTo(offset.x, offset.y)
fun Path.cubicTo(
    control1: Offset,
    control2: Offset,
    offset: Offset,
) = cubicTo(
    control1.x, control1.y,
    control2.x, control2.y,
    offset.x, offset.y,
)
fun Path.quadraticTo(
    control1: Offset,
    offset: Offset,
) = quadraticTo(
    control1.x, control1.y,
    offset.x, offset.y,
)
fun createPath(
    points: List<Offset>,
    curvature: Float=1f
): Pair<Path, List<Pair<Offset, Offset>>>
{
    if(points.size < 2){
        return Path() to listOf()
    }
    if(curvature<=0.001f){
        return Path().apply {
            points.forEachIndexed { index, offset ->
                when(index){
                    0-> moveTo(offset)
                    else -> lineTo(offset)
                }
            }
        } to points.map { it to it }
    }
    val controlPoints = mutableListOf<Pair<Offset, Offset>>()
    val path=Path().apply {
        moveTo(points[0])
        var nextControl1 = Offset.Zero
        for (i in 1..<points.lastIndex){
            val mid1=((points[i]+points[i-1])/2f)*curvature
            val mid2=((points[i]+points[i+1])/2f)*curvature
            val mid3=(mid1+mid2)/2f
            val delta=mid3-points[i]
            if(i==1){
                val control1=points[0] -delta
                val control2=mid1 - delta
                cubicTo(
                    control1=control1,
                    control2 = control2,
                    offset = points[i]
                )
                nextControl1=mid2-delta
                controlPoints.add(control2 to nextControl1)
            }else{
                val control2= mid1 - delta
                cubicTo(
                    control1 = nextControl1,
                    control2 = control2,
                    offset = points[i]
                )
                nextControl1=mid2-delta
                controlPoints.add(control2 to nextControl1)
            }
        }
        quadraticTo(
            nextControl1,
            points.last()
        )
    }
    return path to controlPoints
}
