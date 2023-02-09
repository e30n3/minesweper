package model

import kotlin.math.roundToInt
import kotlin.random.Random

class Field(val width: Int, val height: Int, bombRate: Float = 0.2f) {
    val size = width * height
    private val bombCount = (size * bombRate).roundToInt()
    private val matrix =
        (List(bombCount) { true } + List(size - bombCount) { false }).shuffled().map { Cell(it) }.chunked(width)


    private fun List<List<Cell>>.safeGet(i: Int, j: Int) = kotlin.runCatching { this[i][j] }.getOrNull()

    private fun behindIndexes(i: Int, j: Int) = listOf(
        i - 1 to j - 1,
        i - 1 to j,
        i - 1 to j + 1,
        i to j - 1,
        i to j + 1,
        i + 1 to j - 1,
        i + 1 to j,
        i + 1 to j + 1
    )

    private val Pair<Int, Int>.behindCells: List<Cell>
        get() = behindIndexes(first, second).mapNotNull { (newI, newJ) -> matrix.safeGet(newI, newJ) }


    private val Int.findInMatrix get() = div(width) to mod(width)
    fun onCellClick(number: Int) {
        val (i, j) = number.findInMatrix.also { println(it) }
        val cell = matrix[i][j]
        if (cell.isBomb) onBombClicked()
        else {
            cell.onClick()
        }
    }

    fun getCellState(number: Int): CellState {
        val (i, j) = number.findInMatrix
        return matrix[i][j].status
    }

    fun getCellPoints(number: Int): String {
        val (i, j) = number.findInMatrix
        if (matrix[i][j].status.isNotOpen) return ""
        if (matrix[i][j].isBomb) return "\uD83D\uDCA3"
        return calcCellPoints(number).toString()
    }

    private fun calcCellPoints(number: Int): Int {
        val (i, j) = number.findInMatrix
        val behindCells = number.findInMatrix.behindCells
        return behindCells.fold(0) { acc, cell ->
            acc + if (cell.isBomb) 1 else 0
        }
    }

    fun onCellLongClick(number: Int) {
        val (i, j) = number.findInMatrix.also { println(it) }
        matrix[i][j].onLongClick()
    }

    private fun onBombClicked() {
        matrix.flatten().forEach {
            it.open()
        }
    }


}