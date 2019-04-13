package finkmoritz.conwaysgameoflifegame.config

import android.view.View
import android.widget.AdapterView
import finkmoritz.conwaysgameoflifegame.ConfigActivity
import finkmoritz.conwaysgameoflifegame.rules.Rules
import finkmoritz.conwaysgameoflifegame.rules.StandardHexagonalRules
import finkmoritz.conwaysgameoflifegame.rules.StandardQuadrangularRules
import finkmoritz.conwaysgameoflifegame.rules.StandardTriangularRules

open class OnCellsOrRulesSelectedListener(val configActivity: ConfigActivity)
    : AdapterView.OnItemSelectedListener {

    private var cellsSpinner = configActivity.cellsSpinner
    protected var rulesSpinner = configActivity.rulesSpinner
    protected var spinners = configActivity.spinners

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val selectedCells = cellsSpinner.selectedItem.toString()
        val rules : Rules
        if(selectedCells == "Triangular") {
            rules = StandardTriangularRules()
        } else if(selectedCells == "Hexagonal") {
            rules = StandardHexagonalRules()
        } else {
            rules = StandardQuadrangularRules()
        }
        configActivity.setSpinnersFromRules(rules)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}