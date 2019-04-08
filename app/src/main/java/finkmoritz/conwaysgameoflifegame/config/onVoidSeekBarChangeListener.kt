package finkmoritz.conwaysgameoflifegame.config

import android.widget.SeekBar
import android.widget.TextView

class onVoidSeekBarChangeListener(val label : TextView) : SeekBar.OnSeekBarChangeListener {
    override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
        label.setText("Void ($progress%)")
    }

    override fun onStartTrackingTouch(seekBar: SeekBar) {

    }

    override fun onStopTrackingTouch(seekBar: SeekBar) {

    }
}