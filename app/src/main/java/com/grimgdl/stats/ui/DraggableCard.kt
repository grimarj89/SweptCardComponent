package com.grimgdl.stats.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.spring
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlin.math.roundToInt


@Composable
fun DraggableCard(
    modifier: Modifier= Modifier,
    cardContent: @Composable () -> Unit
) {
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }
    var angle by remember { mutableStateOf(0f) }
    var endMove by remember { mutableStateOf(false) }

    val animatableX = remember { Animatable(0f)}
    val animatableY = remember { Animatable(0f)}

    val animatableAngle = remember { Animatable(0f) }



    val animationSpec = remember {
        SpringSpec<Float>(
            dampingRatio = Spring.DampingRatioLowBouncy,
            stiffness = Spring.StiffnessLow
        )
    }


    Box(
        modifier = modifier
            .offset { IntOffset(animatableX.value.roundToInt(), animatableY.value.roundToInt()) }
            .pointerInput(Unit) {

                detectDragGestures(
                    onDragEnd = {

                        offsetX = 0f
                        offsetY = 0f
                        angle = 0f
                        endMove = true

                    }

                ) { _, dragAmount ->

                    offsetX += dragAmount.x * 0.40f
                    offsetY += dragAmount.y * 0.40f
                    angle -= dragAmount.x * 0.025f
                    endMove = false

                }
            }
            .rotate(angle)
    ){
            cardContent()
    }


    LaunchedEffect(offsetX, offsetY, angle){
        if (endMove){
            awaitAll(
                async { animatableX.animateTo(targetValue = offsetX, animationSpec = animationSpec) },
                async { animatableY.animateTo(targetValue = offsetY, animationSpec = animationSpec) },
                async { animatableAngle.animateTo(targetValue = angle, animationSpec = animationSpec) }
            )
        }else {
            animatableX.snapTo(offsetX)
            animatableY.snapTo(offsetY)
            animatableAngle.snapTo(angle)
        }
    }

}