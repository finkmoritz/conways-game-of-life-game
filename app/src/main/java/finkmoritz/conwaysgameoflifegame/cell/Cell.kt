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

    fun setColor(color: Int)
    fun getColor(): Int
}