package finkmoritz.conwaysgameoflifegame.board

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

    override fun randomize(voidPercentage: Float, colors: List<Int>) {
        val positions = IntArray(cells.size, {it})
        var maxIndex = positions.size-1
        val random = Random()

        val nVoid = (voidPercentage*cells.size).toInt()
        for(i in 0 until nVoid) {
            val randPos = random.nextInt(maxIndex+1)
            cells.get(positions[randPos]).setState(Cell.State.VOID)
            positions.swap(randPos,maxIndex)
            maxIndex--
        }

        val nAlive = 0.5*(cells.size-nVoid)
        val nAlivePerPlayer = (nAlive/colors.size).toInt()
        for(color in colors) {
            for(i in 0 until nAlivePerPlayer) {
                val randPos = random.nextInt(maxIndex+1)
                cells.get(positions[randPos]).setState(Cell.State.ALIVE)
                cells.get(positions[randPos]).setColor(SimpleCell.colors[color])
                positions.swap(randPos,maxIndex)
                maxIndex--
            }
        }

        for(i in 0..maxIndex) {
            cells.get(positions[i]).setState(Cell.State.DEAD)
        }
    }

    private fun IntArray.swap(i: Int, j: Int) {
        val tmp = this[i]
        this[i] = this[j]
        this[j] = tmp
    }

    protected fun getNumberOfLivingNeighbours(cell: Cell): Int {
        val neighbours = getNeighbours(cell)
        return neighbours.count { it.getState() == Cell.State.ALIVE }
    }
}