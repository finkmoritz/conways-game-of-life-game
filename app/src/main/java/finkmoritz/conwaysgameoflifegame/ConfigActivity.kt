package finkmoritz.conwaysgameoflifegame

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import android.widget.Spinner
import android.widget.TableRow
import android.widget.TextView
import finkmoritz.conwaysgameoflifegame.config.OnCellsSelectedListener
import finkmoritz.conwaysgameoflifegame.config.OnRulesSelectedListener
import finkmoritz.conwaysgameoflifegame.config.OnVoidSeekBarChangeListener
import finkmoritz.conwaysgameoflifegame.config.OnSizeSeekBarChangeListener

class ConfigActivity : AppCompatActivity() {

    private lateinit var cellsSpinner: Spinner
    private lateinit var rulesSpinner: Spinner
    private lateinit var rows: List<TableRow>
    private lateinit var spinners: List<Spinner>
    private lateinit var sizeLabel : TextView
    private lateinit var sizeSeekBar : SeekBar
    private lateinit var voidLabel : TextView
    private lateinit var voidSeekBar : SeekBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config)

        cellsSpinner = findViewById(R.id.cells_spinner)
        rulesSpinner = findViewById(R.id.rules_spinner)

        rows = listOf(findViewById(R.id.neighbours0_row),
                findViewById(R.id.neighbours1_row),
                findViewById(R.id.neighbours2_row),
                findViewById(R.id.neighbours3_row),
                findViewById(R.id.neighbours4_row),
                findViewById(R.id.neighbours5_row),
                findViewById(R.id.neighbours6_row),
                findViewById(R.id.neighbours7_row),
                findViewById(R.id.neighbours8_row))

        spinners = listOf(findViewById(R.id.neighbours0_spinner),
                findViewById(R.id.neighbours1_spinner),
                findViewById(R.id.neighbours2_spinner),
                findViewById(R.id.neighbours3_spinner),
                findViewById(R.id.neighbours4_spinner),
                findViewById(R.id.neighbours5_spinner),
                findViewById(R.id.neighbours6_spinner),
                findViewById(R.id.neighbours7_spinner),
                findViewById(R.id.neighbours8_spinner))

        cellsSpinner.onItemSelectedListener = OnCellsSelectedListener(cellsSpinner, rows, spinners)
        rulesSpinner.onItemSelectedListener = OnRulesSelectedListener(rulesSpinner, cellsSpinner, rows, spinners)

        sizeLabel = findViewById(R.id.sizeLabel)
        sizeSeekBar = findViewById(R.id.sizeSeekBar)
        sizeSeekBar.setOnSeekBarChangeListener(OnSizeSeekBarChangeListener(sizeLabel))

        voidLabel = findViewById(R.id.voidLabel)
        voidSeekBar = findViewById(R.id.voidSeekBar)
        voidSeekBar.setOnSeekBarChangeListener(OnVoidSeekBarChangeListener(voidLabel))

        enableAllSpinners(spinners, false)
    }

    fun startMainActivity(view: View) {
        val intent = Intent(this, MainActivity::class.java).apply {}
        startActivity(intent)
    }

    private fun enableAllSpinners(spinners : List<Spinner>, isEnabled : Boolean) {
        for(spinner in spinners) {
            spinner.isEnabled = isEnabled
        }
    }
}
