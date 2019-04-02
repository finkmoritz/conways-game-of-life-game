package finkmoritz.conwaysgameoflifegame.board

import finkmoritz.conwaysgameoflifegame.cell.Cell
import finkmoritz.conwaysgameoflifegame.rules.Rules

interface Board {
    fun iterate(rules : Rules) : Board
    fun getAllCells() : List<Cell>
    fun getCell(x : Int, y : Int) : Cell
    fun getCellState(x : Int, y : Int) : Cell.State
    fun setCellState(x : Int, y : Int, state : Cell.State)
    fun getNeighbours(cell : Cell) : List<Cell>
    fun width() : Int
    fun height() : Int
    fun getSize() : Int
    fun clone() : Board
}