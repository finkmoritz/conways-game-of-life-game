package finkmoritz.conwaysgameoflifegame.config

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import finkmoritz.conwaysgameoflifegame.board.Board
import finkmoritz.conwaysgameoflifegame.rules.Rules
import finkmoritz.conwaysgameoflifegame.rules.StandardQuadrangularRules

@Entity(tableName = "config")
data class ConfigDO(
        @PrimaryKey var id: Int = 0,
        @ColumnInfo(name = "board_topology") var boardTopology: String = Board.topologyToString(Board.Topology.QUADRANGULAR),
        @ColumnInfo(name = "rules") var rules: String = "Standard",
        @ColumnInfo(name = "custom_rules") var customRules: String = Rules.rulesToString(StandardQuadrangularRules()),
        @ColumnInfo(name = "board_size") var boardSize: Int = 10,
        @ColumnInfo(name = "void_percentage") var voidPercentage: Int = 10
)
