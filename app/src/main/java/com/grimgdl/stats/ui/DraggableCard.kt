package com.grimgdl.stats.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTransformGestures
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.roundToInt


@Composable
fun DraggableCard(
    modifier: Modifier= Modifier,
    cardContent: @Composable () -> Unit
) {
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }
    var angle by remember { mutableStateOf(0f) }

    val animatableX = remember { Animatable(0f)}
    val animatableY = remember { Animatable(0f)}

    val animatableAngle = remember { Animatable(90f)}

    Box(
        modifier = modifier
            .offset { IntOffset(animatableX.value.roundToInt(), animatableY.value.roundToInt()) }
            .pointerInput(Unit) {

                detectDragGestures(
                    onDragEnd = {
                        CoroutineScope(Dispatchers.Main).launch {
                            offsetX = 0f
                            offsetY = 0f
                            angle = 0f
                            animatableX.snapTo(offsetX)
                            animatableY.snapTo(offsetY)
                            animatableAngle.snapTo(angle)

                        }
                    }

                ) { _, dragAmount ->

                    offsetX += dragAmount.x
                    offsetY += dragAmount.y * 0.030f
                    angle += dragAmount.x * 0.015f
                    CoroutineScope(Dispatchers.Main).launch {
                        animatableX.snapTo(offsetX)
                        animatableY.snapTo(offsetY)
                        animatableAngle.snapTo(angle)
                    }
                }
            }
            .rotate(angle)
    ){
            cardContent()
    }

}