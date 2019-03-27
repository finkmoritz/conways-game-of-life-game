package finkmoritz.conwaysgameoflifegame.cell

interface Cell {
    enum class State {
        VOID,
        DEAD,
        ALIVE
    }

    fun setState(state: State)
    fun getState() : State
}