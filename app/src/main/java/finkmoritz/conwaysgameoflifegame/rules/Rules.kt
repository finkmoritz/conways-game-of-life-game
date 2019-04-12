package finkmoritz.conwaysgameoflifegame.rules

import finkmoritz.conwaysgameoflifegame.cell.Cell

interface Rules {

    fun addTransition(nNeighbours : Int, transition : Cell.Transition)
    fun getTransition(nNeighbours: Int) : Cell.Transition?
    fun getNewState(nNeighbours: Int, oldState : Cell.State) : Cell.State

    companion object {
        fun rulesToString(rules: Rules): String {
            var rulesString = ""
            var nNeighbours = 0
            var transition = rules.getTransition(nNeighbours++)
            while (transition != null) {
                if(transition == Cell.Transition.LIVE) {
                    rulesString += "1"
                } else if(transition == Cell.Transition.PERSIST) {
                    rulesString += "0"
                } else {
                    rulesString += "2"
                }
                transition = rules.getTransition(nNeighbours++)
            }
            return rulesString
        }

        fun stringToRules(string: String, rules: Rules) {
            for(i in 0 until string.length) {
                if(string[i] == '1') {
                    rules.addTransition(i,Cell.Transition.LIVE)
                } else if(string[i] == '0') {
                    rules.addTransition(i,Cell.Transition.PERSIST)
                } else {
                    rules.addTransition(i,Cell.Transition.DIE)
                }
            }
        }
    }
}