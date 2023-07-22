package gol

import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.terminal.Terminal

fun Terminal.renderGol(gameOfLife: GameOfLife) {
    val columns = gameOfLife.size.second

    printHeader(columns)

    gameOfLife.asListOfCells.forEachIndexed { index, cell ->
        printFirstColumnCharacterIfNeeded(index, columns)

        printCell(cell)

        printLastColumnIfNeeded(index, columns)
    }

    printFooter(columns)
}

fun Terminal.resetCursor(gameSize: Int) {
    cursor.move { left(gameSize + 2) }
    cursor.move { up(gameSize + 2) }
}

private fun Terminal.printCell(cell: Cell) {
    if (cell) {
        print(TextColors.red("O"))
    } else {
        print(" ")
    }
}

private fun printLastColumnIfNeeded(index: Int, columns: Int) {
    if (index % columns == columns - 1) {
        if (index < columns * columns - 1) {
            println("|")
        } else {
            print("|")
        }
    }
}

private fun printFirstColumnCharacterIfNeeded(index: Int, columns: Int) {
    if (index % columns == 0) {
        print("|")
    }
}

private fun Terminal.printFooter(columns: Int) {
    println()
    print(" ")
    (1..columns).forEach { _ -> print(TextColors.red("¯")) }
    println()
}

private fun Terminal.printHeader(columns: Int) {
    print(" ")
    (1..columns).forEach { _ -> print(TextColors.red("_")) }
    println()
}