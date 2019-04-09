package finkmoritz.conwaysgameoflifegame.config

import android.widget.SeekBar
import android.widget.TextView

class OnSizeSeekBarChangeListener(private val label : TextView) : SeekBar.OnSeekBarChangeListener {
    override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
        if(progress < 10) {
            label.text = "Size: $progress\u292B$progress  "
        } else {
            label.text = "Size: $progress\u292B$progress"
        }
    }

    override fun onStartTrackingTouch(seekBar: SeekBar) {

    }

    override fun onStopTrackingTouch(seekBar: SeekBar) {

    }
}