package com.example.ipodlauncher.ui.view

import android.media.MediaPlayer
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.example.ipodlauncher.R
import kotlin.math.atan2

import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback

@Composable
fun ClickWheel(
    modifier: Modifier = Modifier,
    onScroll: (Float) -> Unit,
    onCenterClick: () -> Unit,
    onMenuClick: () -> Unit,
    onForwardClick: () -> Unit,
    onBackClick: () -> Unit,
    onPlayPauseClick: () -> Unit
) {
    var angle by remember { mutableStateOf(0f) }
    val context = androidx.compose.ui.platform.LocalContext.current
    val mediaPlayer = remember { MediaPlayer.create(context, R.raw.click) }
    val haptic = LocalHapticFeedback.current
    var lastDirection by remember { mutableStateOf(-1) }
    val directions = (0..7).map { it * 45f }

    Box(
        modifier = modifier
            .size(200.dp)
            .background(Color.Gray, shape = CircleShape)
            .pointerInput(Unit) {
                detectDragGestures {
                    change, dragAmount ->
                    change.consume()
                    val x = change.position.x - size.width / 2
                    val y = change.position.y - size.height / 2
                    val newAngle = (atan2(y, x) * (180f / Math.PI).toFloat() + 360) % 360

                    val currentDirection = directions.minByOrNull { kotlin.math.abs(it - newAngle) }?.let { directions.indexOf(it) } ?: -1

                    if (currentDirection != lastDirection) {
                        mediaPlayer?.start()
                        haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                        lastDirection = currentDirection
                    }

                    val angleDiff = newAngle - angle
                    onScroll(angleDiff)

                    angle = newAngle
                }
            },
        contentAlignment = Alignment.Center
    ) {
        // Menu Button
        Box(
            modifier = Modifier
                .offset(y = (-70).dp)
                .clickable { onMenuClick() },
            contentAlignment = Alignment.Center
        ) {
            Text(text = "MENU", color = Color.White)
        }

        // Forward Button
        Box(
            modifier = Modifier
                .offset(x = 70.dp)
                .clickable { onForwardClick() },
            contentAlignment = Alignment.Center
        ) {
            Text(text = ">>", color = Color.White)
        }

        // Back Button
        Box(
            modifier = Modifier
                .offset(x = (-70).dp)
                .clickable { onBackClick() },
            contentAlignment = Alignment.Center
        ) {
            Text(text = "<<", color = Color.White)
        }

        // Play/Pause Button
        Box(
            modifier = Modifier
                .offset(y = 70.dp)
                .clickable { onPlayPauseClick() },
            contentAlignment = Alignment.Center
        ) {
            Text(text = ">||", color = Color.White)
        }

        Box(
            modifier = Modifier
                .size(80.dp)
                .background(Color.DarkGray, shape = CircleShape)
                .clickable { onCenterClick() },
            contentAlignment = Alignment.Center
        ) {
        }
    }
}