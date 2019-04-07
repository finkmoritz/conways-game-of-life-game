package finkmoritz.conwaysgameoflifegame

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Spinner
import android.widget.TableRow
import finkmoritz.conwaysgameoflifegame.config.onCellsSelectedListener

class ConfigActivity : AppCompatActivity() {

    lateinit var cellsSpinner: Spinner
    lateinit var rulesSpinner: Spinner
    lateinit var rows: List<TableRow>
    lateinit var spinners: List<Spinner>

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

        cellsSpinner.onItemSelectedListener = onCellsSelectedListener(cellsSpinner, rows, spinners)
    }

    fun startMainActivity(view: View) {
        val intent = Intent(this, MainActivity::class.java).apply {}
        startActivity(intent)
    }
}
