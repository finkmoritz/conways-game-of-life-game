package finkmoritz.conwaysgameoflifegame.rules

import finkmoritz.conwaysgameoflifegame.cell.Cell

class StandardHexagonalRules : ConwayRules() {

    init {
        addTransition(0, Cell.Transition.DIE)
        addTransition(1, Cell.Transition.DIE)
        addTransition(2, Cell.Transition.PERSIST)
        addTransition(3, Cell.Transition.LIVE)
        addTransition(4, Cell.Transition.DIE)
        addTransition(5, Cell.Transition.DIE)
        addTransition(6, Cell.Transition.DIE)
    }
}