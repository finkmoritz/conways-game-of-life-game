package finkmoritz.conwaysgameoflifegame.game

import finkmoritz.conwaysgameoflifegame.board.Board
import finkmoritz.conwaysgameoflifegame.board.HexagonalBoard
import finkmoritz.conwaysgameoflifegame.board.QuadrangularBoard
import finkmoritz.conwaysgameoflifegame.board.TriangularBoard
import finkmoritz.conwaysgameoflifegame.config.Config

class Game(var config: Config) {

    private val initialConfig: Config = config
    var board: Board

    init {
        when(config.boardTopology) {
            Board.Topology.TRIANGULAR -> board = TriangularBoard(config.boardSize,config.boardSize)
            Board.Topology.HEXAGONAL -> board = HexagonalBoard(config.boardSize,config.boardSize)
            else -> board = QuadrangularBoard(config.boardSize,config.boardSize)
        }
        board.randomize(config.voidPercentage)
    }

    fun nextTurn() {
        board.iterate(config.customRules)
    }

    fun reset() {
        config = initialConfig
    }
}