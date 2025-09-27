package com.example.ipodlauncher.ui.view

import android.content.Intent
import android.os.Environment
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.FileProvider
import androidx.navigation.NavController
import com.example.ipodlauncher.data.Document

@Composable
fun DocumentsScreen(navController: NavController) {
    val context = LocalContext.current
    val documentsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
    val files = documentsDir.listFiles()?.map { Document(it) } ?: emptyList()

    BaseListScreen(
        navController = navController,
        title = "Documents",
        items = files,
        itemText = { it.file.name },
        onItemClick = { document ->
            val uri = FileProvider.getUriForFile(context, context.applicationContext.packageName + ".provider", document.file)
            val intent = Intent(Intent.ACTION_VIEW)
            val mimeType = context.contentResolver.getType(uri)
            intent.setDataAndType(uri, mimeType)
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            context.startActivity(intent)
        }
    )
}