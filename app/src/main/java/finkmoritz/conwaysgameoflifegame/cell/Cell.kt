package finkmoritz.conwaysgameoflifegame.cell

interface Cell {
    enum class State {
        VOID,
        DEAD,
        ALIVE
    }

    enum class Transition {
        PERSIST,
        DIE,
        LIVE
    }

    fun setState(state: State)
    fun getState() : State

    fun transform(transition: Transition) {
        if(transition == Transition.DIE) {
            setState(State.DEAD)
        } else if(transition == Transition.LIVE) {
            setState(State.ALIVE)
        }
    }

    fun transitionToString(transition: Transition) : String {
        if(transition == Transition.PERSIST) {
            return "persist"
        } else if(transition == Transition.DIE) {
            return "die"
        } else if (transition == Transition.LIVE) {
            return "live"
        } else {
            return ""
        }
    }
}