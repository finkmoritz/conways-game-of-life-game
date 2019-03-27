package finkmoritz.conwaysgameoflifegame.board

import finkmoritz.conwaysgameoflifegame.cell.Cell

class HexagonalBoard(var cells : List<Cell>, val width : Int, val height : Int) : Board {

    init {
        assert(width*height + (width-1)*(height-1) == cells.size, { println("Number of cells (" + cells.size + ") does not equal w*h+(w-1)*(h-1) with w="+width+" and h="+height+" !") })
    }

    override fun getAllCells(): List<Cell> = cells

    override fun getNeighbours(cell: Cell): List<Cell> {
        var neighbours = mutableListOf<Cell>()
        val index = cells.indexOf(cell)

        val hasNbrAbove = index-2*width >= 0
        val hasNbrBelow = index < cells.size-2*width
        val hasNbrRight = index%(2*width-1) != 2*width-2
        val hasNbrLeft = index%(2*width-1) != 0

        if(hasNbrAbove) {
            neighbours.add(cells.get(index-(2*width-1)))
        }
        if(hasNbrBelow) {
            neighbours.add(cells.get(index+(2*width-1)))
        }
        if(hasNbrLeft) {
            neighbours.add(cells.get(index-width))
            neighbours.add(cells.get(index+width-1))
        }
        if(hasNbrRight) {
            neighbours.add(cells.get(index-width+1))
            neighbours.add(cells.get(index+width))
        }

        return neighbours
    }
}