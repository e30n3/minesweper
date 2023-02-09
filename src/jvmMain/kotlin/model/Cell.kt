package model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class Cell(val isBomb: Boolean = false, initialStatus: CellState = CellState.UNKNOWN) {
    var status by mutableStateOf(initialStatus)
    fun onClick() {
        status = CellState.OPEN
    }

    fun open(){
        status = CellState.OPEN
    }

    fun onLongClick() {
        when (status) {
            CellState.UNKNOWN -> status = CellState.FLAG
            CellState.QUESTION -> status = CellState.UNKNOWN
            CellState.FLAG -> status = CellState.QUESTION
            CellState.OPEN -> {}
        }
    }
}