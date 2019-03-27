package finkmoritz.conwaysgameoflifegame.board

import finkmoritz.conwaysgameoflifegame.cell.Cell

class TriangularBoard(var _cells : List<Cell>, val _width : Int, val _height : Int) : AbstractBoard(_cells,_width,_height) {

    override fun getNeighbours(cell: Cell): List<Cell> {
        var neighbours = mutableListOf<Cell>()
        val index = cells.indexOf(cell)

        val pointsUp = index%2 == 0
        val hasNbrAbove = index-width >= 0
        val hasNbrBelow = index < cells.size-width
        val hasNbrRight = index%width != width-1
        val hasNbrLeft = index%width != 0

        if(hasNbrLeft) {
            neighbours.add(cells.get(index-1))
        }
        if(hasNbrRight) {
            neighbours.add(cells.get(index+1))
        }
        if(pointsUp && hasNbrBelow) {
            neighbours.add(cells.get(index+width))
        } else if(!pointsUp && hasNbrAbove) {
            neighbours.add(cells.get(index-width))
        }

        return neighbours
    }
}