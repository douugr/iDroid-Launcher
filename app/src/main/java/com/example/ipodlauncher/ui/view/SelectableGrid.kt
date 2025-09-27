package com.example.ipodlauncher.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.ipodlauncher.data.Photo

@Composable
fun SelectableGrid(
    modifier: Modifier = Modifier,
    items: List<Photo>,
    selectedItemIndex: Int,
    gridState: LazyGridState = rememberLazyGridState()
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = modifier,
        state = gridState
    ) {
        itemsIndexed(items) { index, item ->
            val backgroundColor = if (index == selectedItemIndex) Color.Blue else Color.Transparent
            AsyncImage(
                model = item.uri,
                contentDescription = item.title,
                modifier = Modifier
                    .aspectRatio(1f)
                    .background(backgroundColor)
                    .padding(2.dp)
            )
        }
    }
}