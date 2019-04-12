package finkmoritz.conwaysgameoflifegame.board

import finkmoritz.conwaysgameoflifegame.cell.Cell
import finkmoritz.conwaysgameoflifegame.rules.Rules

interface Board {
    fun iterate(rules : Rules) : Board
    fun getAllCells() : List<Cell>
    fun getCell(x : Int, y : Int) : Cell
    fun getCellState(x : Int, y : Int) : Cell.State
    fun setCellState(x : Int, y : Int, state : Cell.State)
    fun getNeighbours(cell : Cell) : List<Cell>
    fun width() : Int
    fun height() : Int
    fun getSize() : Int
    fun getTopology() : Topology
    fun clone() : Board

    enum class Topology {
        TRIANGULAR,
        QUADRANGULAR,
        HEXAGONAL;
    }

    companion object {
        fun topologyFromString(string: String): Board.Topology {
            var topology = Board.Topology.QUADRANGULAR
            if(string == "Triangular") {
                topology = Board.Topology.TRIANGULAR
            } else if(string == "Hexagonal") {
                topology = Board.Topology.HEXAGONAL
            }
            return topology
        }

        fun topologyToString(topology: Board.Topology): String {
            var string = "Quadrangular"
            if(topology == Board.Topology.TRIANGULAR) {
                string = "Triangular"
            } else if(topology == Board.Topology.HEXAGONAL) {
                string = "Hexagonal"
            }
            return string
        }
    }
}