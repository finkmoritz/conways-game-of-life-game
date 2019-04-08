package finkmoritz.conwaysgameoflifegame.cell

class SimpleCell(private var currentState : Cell.State) : Cell {

    override fun setState(state : Cell.State) {
        currentState = state
    }

    override fun getState() = currentState
}