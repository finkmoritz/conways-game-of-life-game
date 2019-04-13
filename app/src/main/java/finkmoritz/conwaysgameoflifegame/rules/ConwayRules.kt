package finkmoritz.conwaysgameoflifegame.rules

import finkmoritz.conwaysgameoflifegame.cell.Cell

open class ConwayRules : Rules {

    private var nbrToTransition = HashMap<Int,Cell.Transition>()

    override fun addTransition(nNeighbours: Int, transition: Cell.Transition) {
        nbrToTransition[nNeighbours] = transition
    }

    override fun getTransition(nNeighbours: Int) = nbrToTransition[nNeighbours]

    override fun getNewState(nNeighbours: Int, oldState: Cell.State): Cell.State {
        val transition = nbrToTransition[nNeighbours]
        return transform(oldState,transition)
    }

    override fun getMaxNumberOfNeighbours() = 0

    private fun transform(oldState: Cell.State, transition: Cell.Transition?): Cell.State {
        var newState = oldState
        if(transition != null) {
            if(transition == Cell.Transition.LIVE) {
                newState = Cell.State.ALIVE
            } else if(transition == Cell.Transition.DIE) {
                newState = Cell.State.DEAD
            }
        }
        return newState
    }
}