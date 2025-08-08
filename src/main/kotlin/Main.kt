
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.safi.launcher.JsonHandler
import com.safi.launcher.LauncherItem
import com.safi.ui.AppTheme
import com.safi.ui.ThemeController

@Composable
@Preview
fun App(_count: Int) {
    var count = _count
    var text by remember { mutableStateOf("List has $count item(s)!") }

    Row {
        Button(onClick = {
            val l = LauncherItem("testName", "testFile", "testIcon")
            JsonHandler.appendLauncherItem(l)
            count = JsonHandler.readLauncherItems().size
            text = "List has $count item(s)!"
        }) {
            Text(text)
        }
        Button(onClick = {
            JsonHandler.clearAllItems()
            count = JsonHandler.readLauncherItems().size
            text = "List has $count item(s)!"
        }) {
            Text("Clear all?")
        }
    }
}

@Composable
fun topBarComponent() {

}

fun main() = application {
    JsonHandler.verifySaveFileExists()
    val list = JsonHandler.readLauncherItems()

    Window(onCloseRequest = ::exitApplication,
        transparent = true,
        undecorated = true,
        state = rememberWindowState(width = 525.dp, height = 600.dp),
        title = "Safi Launcher",
        resizable = false) {

        AppTheme {
            Column {
                WindowDraggableArea {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .background(ThemeController.DarkColorPalette.primaryVariant)
                            .padding(end = 10.dp)
                    ) {
                        Text(
                            text = "Safi Launcher",
                            color = ThemeController.DarkColorPalette.onPrimary,
                            fontSize = 30.sp,
                            fontStyle = FontStyle.Italic,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .align(Alignment.CenterStart)
                                .padding(start = 10.dp)
                            )
                        Row(modifier = Modifier.align(Alignment.CenterEnd)) {
                            Button(
                                onClick = { exitApplication() },
                                modifier = Modifier
                                    .padding(end = 10.dp)
                            ) {
                                Text("Add")
                            }
                            Button(
                                onClick = { exitApplication() },
                                modifier = Modifier
                                    .width(40.dp)
                            ) {
                                Text("X", modifier = Modifier.padding(horizontal = 0.dp))
                            }
                        }
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(ThemeController.DarkColorPalette.background.copy(alpha = 0.9f))
                ) {
                    App(list.size)
                }
            }
        }
    }
}
