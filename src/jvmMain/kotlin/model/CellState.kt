package model

import androidx.compose.ui.graphics.Color

enum class CellState(val color: Color) {
    UNKNOWN(Color(0xffc7dcff)),
    QUESTION(Color(0xfffff8c7)),
    FLAG(Color(0xffffc4c7)),
    OPEN(Color(0xfff0f0f0));

    val isUnknown get() = this == UNKNOWN
    val isQuestion get() = this == QUESTION
    val isFlag get() = this == FLAG
    val isOpen get() = this == OPEN

    val isNotUnknown get() = this != UNKNOWN
    val isNotQuestion get() = this != QUESTION
    val isNotFlag get() = this != FLAG
    val isNotOpen get() = this != OPEN
}