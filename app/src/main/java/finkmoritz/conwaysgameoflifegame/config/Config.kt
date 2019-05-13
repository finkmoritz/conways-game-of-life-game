package finkmoritz.conwaysgameoflifegame.config

import com.google.gson.annotations.SerializedName
import finkmoritz.conwaysgameoflifegame.board.Board
import finkmoritz.conwaysgameoflifegame.rules.ConwayRules
import finkmoritz.conwaysgameoflifegame.rules.ConwayRulesFactory
import java.io.Serializable

class Config(
        @SerializedName("boardTopology")
        var boardTopology: Board.Topology = Board.Topology.QUADRANGULAR,
        @SerializedName("rules")
        var rules: String = "Standard",
        @SerializedName("customRules")
        var customRules: ConwayRules = ConwayRulesFactory().createStandardConwayRules(Board.Topology.QUADRANGULAR),
        @SerializedName("boardSize")
        var boardSize: Int = 10,
        @SerializedName("voidPercentage")
        var voidPercentage: Int = 10,
        @SerializedName("playerColors")
        var playerColors: List<Int> = mutableListOf()
) : Serializable