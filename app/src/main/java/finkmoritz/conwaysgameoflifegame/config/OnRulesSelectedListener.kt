package finkmoritz.conwaysgameoflifegame.config

import android.view.View
import android.widget.AdapterView
import android.widget.TableRow
import android.widget.Spinner
import finkmoritz.conwaysgameoflifegame.cell.Cell
import finkmoritz.conwaysgameoflifegame.rules.Rules
import finkmoritz.conwaysgameoflifegame.rules.StandardHexagonalRules
import finkmoritz.conwaysgameoflifegame.rules.StandardQuadrangularRules
import finkmoritz.conwaysgameoflifegame.rules.StandardTriangularRules

class OnRulesSelectedListener(val cellsSpinner : Spinner, val rulesSpinner : Spinner, val rows : List<TableRow>, val spinners : List<Spinner>) : AdapterView.OnItemSelectedListener {
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val selectedCells = cellsSpinner.selectedItem.toString()
        val selectedRules = rulesSpinner.selectedItem.toString()

        val nNeighbours : Int
        val rules : Rules

        if(selectedCells == "Triangular") {
            nNeighbours = 3
            rules = StandardTriangularRules()
        } else if(selectedCells == "Quadrangular") {
            nNeighbours = 8
            rules = StandardQuadrangularRules()
        } else if(selectedCells == "Hexagonal") {
            nNeighbours = 6
            rules = StandardHexagonalRules()
        } else {
            nNeighbours = 0
            rules = StandardQuadrangularRules()
        }

        for(i in 0..rows.size-1) {
            if(i<=nNeighbours) {
                rows.get(i).setVisibility(View.VISIBLE)
                setSpinner(spinners.get(i),rules.getTransition(i))
            } else {
                rows.get(i).setVisibility(View.INVISIBLE)
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    private fun setSpinner(spinner : Spinner, transition : Cell.Transition?) {
        var id = 0
        if(transition != null) {
            if(transition == Cell.Transition.PERSIST) {
                id=2
            } else if(transition == Cell.Transition.LIVE) {
                id=1
            }
        }
        spinner.setSelection(id)
    }
}