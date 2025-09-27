package com.example.ipodlauncher.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun <T> SelectableList(
    modifier: Modifier = Modifier,
    items: List<T>,
    selectedItemIndex: Int,
    itemText: (T) -> String
) {
    LazyColumn(modifier = modifier) {
        itemsIndexed(items) { index, item ->
            val backgroundColor = if (index == selectedItemIndex) Color.Blue else Color.Transparent
            val textColor = if (index == selectedItemIndex) Color.White else Color.Black
            Text(
                text = itemText(item),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(backgroundColor)
                    .padding(8.dp),
                color = textColor
            )
        }
    }
}