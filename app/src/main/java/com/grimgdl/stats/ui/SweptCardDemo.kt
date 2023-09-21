package com.grimgdl.stats.ui

import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.roundToInt


@Composable
fun SweptCardDemo() {
    var isExpanded by remember { mutableStateOf(false) }
    var offsetY by remember { mutableStateOf(0f) }
    val cardHeight = 200.dp

    Log.i("offsetX", "$offsetY")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectHorizontalDragGestures { _, dragAmount ->
                    offsetY += dragAmount
                    offsetY = offsetY.coerceIn(-cardHeight.value, 0f)
                }

            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .offset { IntOffset(offsetY.roundToInt(), 0) }
        ) {
            Box(
                modifier = Modifier
                    .height(cardHeight)
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary)
            ) {
                Text(
                    text = "Tarjeta Barrida",
                    modifier = Modifier.padding(16.dp),

                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Contenido de la tarjeta"
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun SwipeableCard(
    modifier: Modifier = Modifier,
    content: @Composable (Modifier) -> Unit
) {
    var offsetX by remember { mutableStateOf(0f) }
    var isDismissed by remember { mutableStateOf(false) }
    val cardWidth = 300f // Ajusta el ancho de la tarjeta según tus necesidades
    val swipeThreshold = 0.5f // Umbral de deslizamiento para eliminar la tarjeta

    val animationSpec: AnimationSpec<Float> = spring()
    val offsetState = remember { Animatable(0f) }

    Box(
        modifier = modifier
            .pointerInput(Unit) {
                detectHorizontalDragGestures { _, dragAmount ->
                    if (!isDismissed) {
                        offsetX += dragAmount
                        val progress = (offsetX / cardWidth).coerceIn(-1f, 1f)
                        

                        CoroutineScope(Dispatchers.Main).launch {
                            offsetState.snapTo(progress)
                        }
                    }
                }
            }
    ) {
        Box(
            modifier = Modifier
                .offset { IntOffset(offsetX.roundToInt(), 0) }
                .fillMaxSize()
        ) {
            content(Modifier.fillMaxSize())
        }

        // Animación de eliminación
        DisposableEffect(isDismissed) {
            if (isDismissed) {
                CoroutineScope(Dispatchers.Main).launch{
                    offsetState.animateTo(1f, animationSpec)
                }

            }
            onDispose { }
        }
    }

    //La tarjeta se eliminará automáticamente si su posición supera el umbral de deslizamiento:

    DisposableEffect(offsetState.value) {
        if (offsetState.value >= swipeThreshold) {
            isDismissed = true
        }
        onDispose { }
    }
}