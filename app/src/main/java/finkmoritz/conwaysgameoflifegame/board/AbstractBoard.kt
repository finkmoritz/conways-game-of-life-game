package finkmoritz.conwaysgameoflifegame.board

import finkmoritz.conwaysgameoflifegame.cell.Cell
import finkmoritz.conwaysgameoflifegame.cell.CellBE
import finkmoritz.conwaysgameoflifegame.rules.Rules

abstract class AbstractBoard(protected val width : Int, protected val height : Int) : Board {

    protected var cells = mutableListOf<Cell>()

    init {
        for(i in 0..(width*height-1)) {
            cells.add(CellBE(Cell.State.VOID))
        }
    }

    override fun iterate(rules: Rules): Board {
        var newBoard = this.clone()
        for(x in 0..width()-1) {
            for(y in 0..height()-1) {
                val cell = getCell(x,y)
                val nNeighbours = getNeighbours(cell).size
                val newState = rules.getNewState(nNeighbours,cell.getState())
                newBoard.setCellState(x,y,newState)
            }
        }
        return newBoard
    }

    override fun getAllCells(): List<Cell> = cells
    override fun getCell(x : Int, y: Int) = cells.get(y*width()+x)
    override fun getCellState(x: Int, y: Int): Cell.State = getCell(x,y).getState()
    override fun setCellState(x: Int, y: Int, state: Cell.State) = getCell(x,y).setState(state)
    override fun width() = width
    override fun height() = height
    override fun getSize(): Int = width()*height()
}