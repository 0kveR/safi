
package com.safi.launcher

import kotlinx.serialization.json.Json
import java.io.File

object JsonHandler {
    private val saveDir = File(System.getProperty("user.home"), ".safi-launcher")
    private val saveFile = File(saveDir, "launcherItems.json")

    fun writeLauncherItems(items: List<LauncherItem>) {
        val file = getSaveFile()
        val json = Json.encodeToString(items)
        file.writeText(json)
    }

    fun appendLauncherItem(item: LauncherItem) {
        verifySaveFileExists()
        val list = readLauncherItems().toMutableList()
        list.add(item)
        writeLauncherItems(list)
    }

    fun readLauncherItems(): List<LauncherItem> {
        val file = getSaveFile()
        return if(file.exists()) {
            Json.decodeFromString(file.readText())
        } else emptyList()
    }

    fun clearAllItems() {
        writeLauncherItems(emptyList())
    }

    private fun getSaveFile(): File {
        verifySaveFileExists()
        return saveFile
    }

    fun verifySaveFileExists() {
        if (!saveDir.exists()) saveDir.mkdirs()
        if (!saveFile.exists()) saveFile.writeText("[]")
    }
}