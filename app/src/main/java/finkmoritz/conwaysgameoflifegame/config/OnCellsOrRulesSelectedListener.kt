package finkmoritz.conwaysgameoflifegame.config

import android.view.View
import android.widget.AdapterView
import finkmoritz.conwaysgameoflifegame.ConfigActivity
import finkmoritz.conwaysgameoflifegame.board.Board
import finkmoritz.conwaysgameoflifegame.rules.ConwayRulesFactory
import finkmoritz.conwaysgameoflifegame.rules.Rules

open class OnCellsOrRulesSelectedListener(val configActivity: ConfigActivity)
    : AdapterView.OnItemSelectedListener {

    private var cellsSpinner = configActivity.cellsSpinner
    protected var rulesSpinner = configActivity.rulesSpinner
    protected var spinners = configActivity.spinners

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val selectedCells = cellsSpinner.selectedItem.toString()
        val rules : Rules
        when (selectedCells) {
            "Triangular" -> rules = ConwayRulesFactory().createStandardConwayRules(Board.Topology.TRIANGULAR)
            "Hexagonal" -> rules = ConwayRulesFactory().createStandardConwayRules(Board.Topology.HEXAGONAL)
            else -> rules = ConwayRulesFactory().createStandardConwayRules(Board.Topology.QUADRANGULAR)
        }
        configActivity.setSpinnersFromRules(rules)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}