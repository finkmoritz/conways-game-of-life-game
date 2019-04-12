package finkmoritz.conwaysgameoflifegame.board

import finkmoritz.conwaysgameoflifegame.cell.Cell

class TriangularBoard(_width : Int, _height : Int) : AbstractBoard(_width,_height) {

    override fun getNeighbours(cell: Cell): List<Cell> {
        val neighbours = mutableListOf<Cell>()
        val index = cells.indexOf(cell)

        val pointsUp = index%2 == 0
        val hasNbrAbove = index-width >= 0
        val hasNbrBelow = index < cells.size-width
        val hasNbrRight = index%width != width-1
        val hasNbrLeft = index%width != 0

        if(hasNbrLeft) {
            neighbours.add(cells[index-1])
        }
        if(hasNbrRight) {
            neighbours.add(cells[index+1])
        }
        if(pointsUp && hasNbrBelow) {
            neighbours.add(cells[index+width])
        } else if(!pointsUp && hasNbrAbove) {
            neighbours.add(cells[index-width])
        }

        return neighbours
    }

    override fun getTopology(): Board.Topology {
        return Board.Topology.TRIANGULAR
    }

    override fun clone() : Board {
        val newBoard = TriangularBoard(width(),height())
        for(x in 0 until width()) {
            for(y in 0 until height()) {
                newBoard.setCellState(x,y,getCell(x,y).getState())
            }
        }
        return newBoard
    }
}