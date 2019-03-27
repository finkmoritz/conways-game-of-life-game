package finkmoritz.conwaysgameoflifegame.board

import finkmoritz.conwaysgameoflifegame.cell.Cell

interface Board {
    fun getAllCells() : List<Cell>
    fun getNeighbours(cell : Cell) : List<Cell>
}