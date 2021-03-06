package finkmoritz.conwaysgameoflifegame.rules

import com.google.gson.annotations.SerializedName
import finkmoritz.conwaysgameoflifegame.cell.Cell
import java.io.Serializable

open class ConwayRules : Rules, Serializable {

    @SerializedName("nbrToTransition")
    private var nbrToTransition = HashMap<Int,Cell.Transition>()

    override fun setTransition(nNeighbours: Int, transition: Cell.Transition) {
        nbrToTransition[nNeighbours] = transition
    }

    override fun getTransition(nNeighbours: Int) = nbrToTransition[nNeighbours]

    override fun getNewState(nNeighbours: Int, oldState: Cell.State): Cell.State {
        val transition = getTransition(nNeighbours)
        return transform(oldState,transition)
    }

    private fun transform(oldState: Cell.State, transition: Cell.Transition?): Cell.State {
        var newState = oldState
        if(oldState != Cell.State.VOID) {
            if(transition != null) {
                if(transition == Cell.Transition.LIVE) {
                    newState = Cell.State.ALIVE
                } else if(transition == Cell.Transition.DIE) {
                    newState = Cell.State.DEAD
                }
            }
        }
        return newState
    }
}