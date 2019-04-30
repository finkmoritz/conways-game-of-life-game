package finkmoritz.conwaysgameoflifegame.rules

import finkmoritz.conwaysgameoflifegame.board.Board
import finkmoritz.conwaysgameoflifegame.cell.Cell

class ConwayRulesFactory {
    fun createStandardConwayRules(topology: Board.Topology): ConwayRules {
        when(topology) {
            Board.Topology.TRIANGULAR -> return createStandardTriangularRules()
            Board.Topology.HEXAGONAL -> return createStandardHexagonalRules()
            else -> return createStandardQuadrangularRules()
        }
    }

    private fun createStandardTriangularRules(): ConwayRules {
        var rules = ConwayRules()
        rules.setTransition(0, Cell.Transition.DIE)
        rules.setTransition(1, Cell.Transition.DIE)
        rules.setTransition(2, Cell.Transition.PERSIST)
        rules.setTransition(3, Cell.Transition.LIVE)
        return rules
    }

    private fun createStandardHexagonalRules(): ConwayRules {
        var rules = ConwayRules()
        rules.setTransition(0, Cell.Transition.DIE)
        rules.setTransition(1, Cell.Transition.DIE)
        rules.setTransition(2, Cell.Transition.PERSIST)
        rules.setTransition(3, Cell.Transition.LIVE)
        rules.setTransition(4, Cell.Transition.DIE)
        rules.setTransition(5, Cell.Transition.DIE)
        rules.setTransition(6, Cell.Transition.DIE)
        return rules
    }

    private fun createStandardQuadrangularRules(): ConwayRules {
        var rules = ConwayRules()
        rules.setTransition(0, Cell.Transition.DIE)
        rules.setTransition(1, Cell.Transition.DIE)
        rules.setTransition(2, Cell.Transition.PERSIST)
        rules.setTransition(3, Cell.Transition.LIVE)
        rules.setTransition(4, Cell.Transition.DIE)
        rules.setTransition(5, Cell.Transition.DIE)
        rules.setTransition(6, Cell.Transition.DIE)
        rules.setTransition(7, Cell.Transition.DIE)
        rules.setTransition(8, Cell.Transition.DIE)
        return rules
    }
}