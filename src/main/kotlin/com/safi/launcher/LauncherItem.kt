package com.safi.launcher

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import kotlinx.serialization.Serializable

@Serializable
data class LauncherItem(
    val itemName: String,
    val filePath: String,
    val iconPath: String? = null
) {
    fun save() {
        val list = listOf<LauncherItem>(LauncherItem(this.itemName, this.filePath, this.iconPath))
        JsonHandler.writeLauncherItems(list)
    }
}

@Composable
@Preview
fun displayLauncherItem(item: LauncherItem) {

}