package finkmoritz.conwaysgameoflifegame.config

import android.view.View
import android.widget.AdapterView
import finkmoritz.conwaysgameoflifegame.ConfigActivity

class OnRulesSelectedListener(_configActivity: ConfigActivity) :
        OnCellsOrRulesSelectedListener(_configActivity) {
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        super.onItemSelected(parent, view, position, id)

        val selectedRules = rulesSpinner.selectedItem.toString()
        if (selectedRules.contains("Custom")) {
            configActivity.enableAllSpinners(spinners,true)
        } else {
            configActivity.enableAllSpinners(spinners,false)
        }
    }
}