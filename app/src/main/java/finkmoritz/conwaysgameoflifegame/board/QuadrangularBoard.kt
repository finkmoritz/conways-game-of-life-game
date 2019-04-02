package finkmoritz.conwaysgameoflifegame.board

import finkmoritz.conwaysgameoflifegame.cell.Cell

class QuadrangularBoard(val _width : Int, val _height : Int) : AbstractBoard(_width,_height) {

    override fun getNeighbours(cell: Cell): List<Cell> {
        var neighbours = mutableListOf<Cell>()
        val index = cells.indexOf(cell)

        val hasNbrAbove = index-width >= 0
        val hasNbrBelow = index < cells.size-width
        val hasNbrRight = index%width != width-1
        val hasNbrLeft = index%width != 0

        if(hasNbrAbove) {
            neighbours.add(cells.get(index-width))
            if(hasNbrLeft) {
                neighbours.add(cells.get(index-width-1))
            }
            if(hasNbrRight) {
                neighbours.add(cells.get(index-width+1))
            }
        }
        if(hasNbrBelow) {
            neighbours.add(cells.get(index+width))
            if(hasNbrLeft) {
                neighbours.add(cells.get(index+width-1))
            }
            if(hasNbrRight) {
                neighbours.add(cells.get(index+width+1))
            }
        }
        if(hasNbrLeft) {
            neighbours.add(cells.get(index-1))
        }
        if(hasNbrRight) {
            neighbours.add(cells.get(index+1))
        }

        return neighbours
    }

    override fun clone() : Board {
        var newBoard = QuadrangularBoard(width(),height())
        for(x in 0..width()-1) {
            for(y in 0..height()-1) {
                newBoard.setCellState(x,y,getCell(x,y).getState())
            }
        }
        return newBoard
    }
}