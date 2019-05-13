package finkmoritz.conwaysgameoflifegame.cell

import android.graphics.Color

class SimpleCell(private var currentState : Cell.State) : Cell {

    var cellColor = colors[0]

    companion object {
        val colors = arrayOf(Color.TRANSPARENT, Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW)
    }

    override fun setState(state : Cell.State) {
        currentState = state
    }

    override fun getState() = currentState

    override fun setColor(color: Int) {
        this.cellColor = color
    }

    override fun getColor(): Int = cellColor
}