package extention

import androidx.compose.ui.graphics.Color

val Int.minesweeperColor
    get() = when (this) {
        1 -> Color.Blue
        2 -> Color(0xff023020)
        3 -> Color.Red
        4 -> Color(0xff00008b)
        5 -> Color(0xff964B00)
        6 -> Color.Cyan
        7 -> Color.Black
        8 -> Color.Gray
        else -> Color.White
    }