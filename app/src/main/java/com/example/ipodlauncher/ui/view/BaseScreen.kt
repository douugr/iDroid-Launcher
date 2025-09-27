package com.example.ipodlauncher.ui.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun BaseScreen(
    navController: NavController,
    title: String,
    onCenterClick: () -> Unit = {},
    onScroll: (Float) -> Unit = {},
    onForwardClick: () -> Unit = {},
    onBackClick: () -> Unit = {},
    onPlayPauseClick: () -> Unit = {},
    content: @Composable (Modifier) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth().weight(1f).padding(16.dp),
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(2.dp, Color.Black),
        ) {
            Column(modifier = Modifier.background(Color.LightGray)) {
                Text(text = title, modifier = Modifier.padding(8.dp).background(Color.Gray).fillMaxWidth(), color = Color.White)
                content(Modifier)
            }
        }
        ClickWheel(
            modifier = Modifier.padding(bottom = 60.dp),
            onMenuClick = { navController.navigateUp() },
            onCenterClick = onCenterClick,
            onScroll = onScroll,
            onForwardClick = onForwardClick,
            onBackClick = onBackClick,
            onPlayPauseClick = onPlayPauseClick
        )
    }
}