package finkmoritz.conwaysgameoflifegame.board

import finkmoritz.conwaysgameoflifegame.cell.Cell

class HexagonalBoard(val _width : Int, val _height : Int) : AbstractBoard(_width,_height) {

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

    override fun clone() : Board {
        var newBoard = HexagonalBoard(width(),height())
        for(x in 0..width()-1) {
            for(y in 0..height()-1) {
                newBoard.setCellState(x,y,getCell(x,y).getState())
            }
        }
        return newBoard
    }
}