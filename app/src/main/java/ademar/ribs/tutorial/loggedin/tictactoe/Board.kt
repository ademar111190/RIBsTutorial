package ademar.ribs.tutorial.loggedin.tictactoe

import javax.inject.Inject

const val ROWS = 3
const val COLS = 3

class Board @Inject constructor() {

    var currentRow = 0
    var currentCol = 0
    var cells: Array<Array<MarkerType?>> = Array(ROWS) { arrayOfNulls(COLS) }

    fun isDraw(): Boolean {
        if (cells.any { it.any { it == null } }) return false
        return !hasWon(MarkerType.CROSS) && !hasWon(MarkerType.NOUGHT)
    }

    fun hasWon(theSeed: MarkerType): Boolean =
        cells[currentRow][0] == theSeed && cells[currentRow][1] == theSeed && cells[currentRow][2] == theSeed
                || cells[0][currentCol] == theSeed && cells[1][currentCol] == theSeed && cells[2][currentCol] == theSeed
                || currentRow == currentCol && cells[0][0] == theSeed && cells[1][1] == theSeed && cells[2][2] == theSeed
                || currentRow + currentCol == 2 && cells[0][2] == theSeed && cells[1][1] == theSeed && cells[2][0] == theSeed

}
