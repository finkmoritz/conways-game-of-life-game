package finkmoritz.conwaysgameoflifegame.config

import android.widget.SeekBar
import android.widget.TextView

class OnVoidSeekBarChangeListener(private val label : TextView) : SeekBar.OnSeekBarChangeListener {
    override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
        label.text = "Void: $progress%"
    }

    override fun onStartTrackingTouch(seekBar: SeekBar) {

    }

    override fun onStopTrackingTouch(seekBar: SeekBar) {

    }
}