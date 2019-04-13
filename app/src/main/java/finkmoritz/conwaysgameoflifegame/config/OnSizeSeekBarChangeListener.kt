package finkmoritz.conwaysgameoflifegame.config

import android.widget.SeekBar
import finkmoritz.conwaysgameoflifegame.ConfigActivity
import finkmoritz.conwaysgameoflifegame.R
import kotlinx.android.synthetic.main.activity_config.*

class OnSizeSeekBarChangeListener(private val configActivity: ConfigActivity) : SeekBar.OnSeekBarChangeListener {
    override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
        var label = configActivity.sizeLabel
        var s = configActivity.getString(R.string.sizePercentTextTemplate,progress,progress)
        if(progress < 10) {
            s += "  "
        }
        label.text = s
    }

    override fun onStartTrackingTouch(seekBar: SeekBar) {

    }

    override fun onStopTrackingTouch(seekBar: SeekBar) {

    }
}