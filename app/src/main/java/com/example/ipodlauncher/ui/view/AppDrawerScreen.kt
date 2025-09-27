package com.example.ipodlauncher.ui.view

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController

@Composable
fun AppDrawerScreen(navController: NavController) {
    val context = LocalContext.current
    val packageManager = context.packageManager
    val apps = packageManager.queryIntentActivities(Intent(Intent.ACTION_MAIN, null).addCategory(Intent.CATEGORY_LAUNCHER), 0)

    BaseListScreen(
        navController = navController,
        title = "Extras",
        items = apps,
        itemText = { it.loadLabel(packageManager).toString() },
        onItemClick = { app ->
            val launchIntent = packageManager.getLaunchIntentForPackage(app.activityInfo.packageName)
            context.startActivity(launchIntent)
        }
    )
}