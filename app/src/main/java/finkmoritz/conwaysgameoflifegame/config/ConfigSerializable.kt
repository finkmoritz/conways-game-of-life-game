package finkmoritz.conwaysgameoflifegame.config

import com.google.gson.annotations.SerializedName
import finkmoritz.conwaysgameoflifegame.board.Board
import finkmoritz.conwaysgameoflifegame.rules.ConwayRules
import finkmoritz.conwaysgameoflifegame.rules.StandardQuadrangularRules
import java.io.Serializable

class ConfigSerializable(
        @SerializedName("boardTopology")
        var boardTopology: Board.Topology = Board.Topology.QUADRANGULAR,
        @SerializedName("rules")
        var rules: String = "Standard",
        @SerializedName("customRules")
        var customRules: ConwayRules = StandardQuadrangularRules(),
        @SerializedName("boardSize")
        var boardSize: Int = 10,
        @SerializedName("voidPercentage")
        var voidPercentage: Int = 10
) : Serializable