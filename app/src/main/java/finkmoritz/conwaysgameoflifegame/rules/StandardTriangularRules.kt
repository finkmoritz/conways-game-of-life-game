package finkmoritz.conwaysgameoflifegame.rules

import finkmoritz.conwaysgameoflifegame.cell.Cell

class StandardTriangularRules : ConwayRules() {

    init {
        addTransition(0, Cell.Transition.DIE)
        addTransition(1, Cell.Transition.DIE)
        addTransition(2, Cell.Transition.PERSIST)
        addTransition(3, Cell.Transition.LIVE)
    }

    override fun getMaxNumberOfNeighbours() = 3
}