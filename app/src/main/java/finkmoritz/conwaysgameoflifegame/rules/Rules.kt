package finkmoritz.conwaysgameoflifegame.rules

import finkmoritz.conwaysgameoflifegame.cell.Cell

interface Rules {

    fun addTransition(nNeighbours : Int, transition : Cell.Transition)
    fun getTransition(nNeighbours: Int) : Cell.Transition?
    fun getNewState(nNeighbours: Int, oldState : Cell.State) : Cell.State
    fun getMaxNumberOfNeighbours() : Int
}