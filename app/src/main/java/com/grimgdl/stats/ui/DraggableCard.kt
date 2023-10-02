package com.grimgdl.stats.ui

import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.SpringSpec
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue
import kotlin.math.roundToInt


@Composable
fun DraggableCard(
    modifier: Modifier= Modifier,
    cardViewModel: CardViewModel,
    cardContent: @Composable () -> Unit
) {
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }
    var angle by remember { mutableStateOf(0f) }
    var endMove by remember { mutableStateOf(false) }
    var pivot by remember { mutableStateOf(0f) }

    val animatableX = remember { Animatable(0f)}
    val animatableY = remember { Animatable(0f)}

    val animatableAngle = remember { Animatable(0f) }

    var visible by remember { mutableStateOf(true) }

    val animationSpec = remember {
        SpringSpec<Float>(
            dampingRatio = Spring.DampingRatioLowBouncy,
            stiffness = Spring.StiffnessLow
        )
    }

    Log.i("Composable Draggable", "enter $visible position $offsetX")

    if (visible) {
        Box(
            modifier = modifier
                .offset { IntOffset(animatableX.value.roundToInt(), animatableY.value.roundToInt()) }
                .pointerInput(Unit) {

                    detectDragGestures(
                        onDragEnd = {

                            endMove = true
                            offsetX = if(offsetX.absoluteValue > 100) offsetX * 10f else 0f
                            offsetY = 0f
                            angle = 0f

                        }

                    ) { _, dragAmount ->
                        endMove = false
                        offsetX += dragAmount.x * 0.50f
                        offsetY += dragAmount.y * 0.30f

                        angle -= dragAmount.x * 0.030f

                        pivot = if (dragAmount.x >= 0) 1f else 0f

                        cardViewModel.setOffset(dragAmount)
                        cardViewModel.setAngle(angle)
                    }
                }
                .graphicsLayer(
                    rotationZ = animatableAngle.value,
                    transformOrigin = TransformOrigin(
                        pivotFractionX = pivot,
                        pivotFractionY = 0f
                    )

                )
                .background(color = Color.Transparent)

        ){
            cardContent()
        }
    }



    LaunchedEffect(offsetX, offsetY, angle){
        if (endMove){
            awaitAll(
                async { animatableX.animateTo(targetValue = offsetX, animationSpec = animationSpec) },
                async { animatableY.animateTo(targetValue = offsetY, animationSpec = animationSpec) },
                async { animatableAngle.animateTo(targetValue = angle, animationSpec = animationSpec) }
            )

            if (offsetX.absoluteValue > 100) visible = false
        }else {
            animatableX.snapTo(offsetX)
            animatableY.snapTo(offsetY)
            animatableAngle.snapTo(angle)

        }
    }

}

