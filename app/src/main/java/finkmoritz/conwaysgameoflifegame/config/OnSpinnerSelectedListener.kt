package finkmoritz.conwaysgameoflifegame.config

import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.core.content.ContextCompat
import finkmoritz.conwaysgameoflifegame.R


class OnSpinnerSelectedListener : AdapterView.OnItemSelectedListener {
    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        var color: Int
        when(position) {
            2 -> color = ContextCompat.getColor(view.context, R.color.colorAccent)
            1 -> color = ContextCompat.getColor(view.context, R.color.colorGreen)
            else -> color = ContextCompat.getColor(view.context, R.color.colorRed)
        }
        (view as TextView).setTextColor(color)
    }

    override fun onNothingSelected(parent: AdapterView<*>) {}
}