package finkmoritz.conwaysgameoflifegame.config

import android.widget.SeekBar
import finkmoritz.conwaysgameoflifegame.ConfigActivity
import finkmoritz.conwaysgameoflifegame.R
import kotlinx.android.synthetic.main.activity_config.*

class OnVoidSeekBarChangeListener(private val configActivity: ConfigActivity) : SeekBar.OnSeekBarChangeListener {
    override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
        var label = configActivity.voidLabel
        label.text = configActivity.getString(R.string.voidPercentTextTemplate,progress)
    }

    override fun onStartTrackingTouch(seekBar: SeekBar) {

    }

    override fun onStopTrackingTouch(seekBar: SeekBar) {

    }
}