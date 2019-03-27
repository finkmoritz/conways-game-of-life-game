package finkmoritz.conwaysgameoflifegame.board

import finkmoritz.conwaysgameoflifegame.cell.Cell

abstract class AbstractBoard(protected var cells : List<Cell>, protected val width : Int, protected val height : Int) : Board {

    init {
        assert(width * height == cells.size, { println("Number of cells (" + cells.size + ") does not equal width (" + width + ") * height (" + height + ") !") })
    }

    override fun getAllCells(): List<Cell> = cells

}