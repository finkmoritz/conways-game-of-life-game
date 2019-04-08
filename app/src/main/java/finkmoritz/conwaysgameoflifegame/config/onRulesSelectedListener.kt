package finkmoritz.conwaysgameoflifegame.config

import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.TableRow

class onRulesSelectedListener(val rulesSpinner : Spinner, _cellsSpinner : Spinner, _rows : List<TableRow>, _spinners : List<Spinner>) :
        onCellsOrRulesSelectedListener(_cellsSpinner,_rows,_spinners) {
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        super.onItemSelected(parent, view, position, id)

        val selectedRules = rulesSpinner.selectedItem.toString()
        if (selectedRules.contains("Custom")) {
            for (spinner in spinners) {
                spinner.isEnabled = true
            }
        } else {
            for (spinner in spinners) {
                spinner.isEnabled = false
            }
        }
    }
}