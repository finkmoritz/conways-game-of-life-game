package finkmoritz.conwaysgameoflifegame.board

import android.util.Log
import finkmoritz.conwaysgameoflifegame.cell.Cell
import finkmoritz.conwaysgameoflifegame.cell.SimpleCell
import finkmoritz.conwaysgameoflifegame.rules.Rules
import java.util.*

abstract class AbstractBoard(protected val width : Int, private val height : Int) : Board {

    protected var cells = mutableListOf<Cell>()

    init {
        for(i in 0 until width*height) {
            cells.add(SimpleCell(Cell.State.VOID))
        }
    }

    override fun iterate(rules: Rules) {
        val boardClone = this.clone() as AbstractBoard
        for(x in 0 until width()) {
            for(y in 0 until height()) {
                val cell = boardClone.getCell(x,y)
                val nNeighbours = boardClone.getNumberOfLivingNeighbours(cell)
                val newState = rules.getNewState(nNeighbours,cell.getState())
                this.setCellState(x,y,newState)
            }
        }
    }

    override fun getAllCells(): List<Cell> = cells
    override fun getCell(x : Int, y: Int) = cells[y*width()+x]
    override fun getCellState(x: Int, y: Int): Cell.State = getCell(x,y).getState()
    override fun setCellState(x: Int, y: Int, state: Cell.State) = getCell(x,y).setState(state)
    override fun width() = width
    override fun height() = height
    override fun getSize(): Int = width()*height()

    override fun randomize(voidPercentage: Int, colors: List<Int>) {
        Log.d("randomize","$colors")
        val random = Random()
        val living = 0.5
        val livePercentage = living * (100-voidPercentage)
        for(cell in cells) {
            val r = random.nextInt(100)
            if(r < voidPercentage) {
                cell.setState(Cell.State.VOID)
            } else {
                if(r < voidPercentage+livePercentage) {
                    cell.setState(Cell.State.ALIVE)
                    if(Random().nextDouble() < 0.5) { //TODO
                        cell.setColor(SimpleCell.colors[colors[0]])
                    } else {
                        cell.setColor(SimpleCell.colors[colors[1]])
                    }
                } else {
                    cell.setState(Cell.State.DEAD)
                }
            }
        }
    }

    protected fun getNumberOfLivingNeighbours(cell: Cell): Int {
        val neighbours = getNeighbours(cell)
        return neighbours.count { it.getState() == Cell.State.ALIVE }
    }
}