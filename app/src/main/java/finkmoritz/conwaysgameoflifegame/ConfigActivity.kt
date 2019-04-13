package finkmoritz.conwaysgameoflifegame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import finkmoritz.conwaysgameoflifegame.board.AbstractBoard
import finkmoritz.conwaysgameoflifegame.board.Board
import finkmoritz.conwaysgameoflifegame.config.*
import finkmoritz.conwaysgameoflifegame.persistence.AppDatabase
import android.widget.Spinner
import finkmoritz.conwaysgameoflifegame.cell.Cell
import finkmoritz.conwaysgameoflifegame.rules.ConwayRules
import finkmoritz.conwaysgameoflifegame.rules.Rules


class ConfigActivity : AppCompatActivity() {

    lateinit var cellsSpinner: Spinner
    lateinit var rulesSpinner: Spinner
    lateinit var rows: List<TableRow>
    lateinit var spinners: List<Spinner>
    private lateinit var sizeLabel : TextView
    private lateinit var sizeSeekBar : SeekBar
    private lateinit var voidLabel : TextView
    private lateinit var voidSeekBar : SeekBar
    private lateinit var onCellsSelectedListener : OnCellsSelectedListener
    private lateinit var onRulesSelectedListener : OnRulesSelectedListener

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

        onCellsSelectedListener = OnCellsSelectedListener(this)
        onRulesSelectedListener = OnRulesSelectedListener(this)

        cellsSpinner.onItemSelectedListener = onCellsSelectedListener
        rulesSpinner.onItemSelectedListener = onRulesSelectedListener

        sizeLabel = findViewById(R.id.sizeLabel)
        sizeSeekBar = findViewById(R.id.sizeSeekBar)
        sizeSeekBar.setOnSeekBarChangeListener(OnSizeSeekBarChangeListener(sizeLabel))

        voidLabel = findViewById(R.id.voidLabel)
        voidSeekBar = findViewById(R.id.voidSeekBar)
        voidSeekBar.setOnSeekBarChangeListener(OnVoidSeekBarChangeListener(voidLabel))

        enableAllSpinners(spinners, false)
    }

    override fun onPause() {
        saveConfig()
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        loadConfig()
    }

    fun startMainActivity(view: View) {
        val intent = Intent(this, MainActivity::class.java).apply {}
        startActivity(intent)
    }

    fun enableAllSpinners(spinners : List<Spinner>, isEnabled : Boolean) {
        for(spinner in spinners) {
            spinner.isEnabled = isEnabled
        }
    }

    private fun loadConfig() {
        val config = ConfigManagerImpl(this).load()
        selectValue(cellsSpinner,config.boardTopology)
        selectValue(rulesSpinner,config.rules)
        setSpinnersFromRules(Rules.stringToRules(config.customRules))
        sizeSeekBar.progress = config.boardSize
        voidSeekBar.progress = config.voidPercentage
    }

    private fun saveConfig() {
        var config = ConfigDO()
        config.boardTopology = cellsSpinner.selectedItem.toString()
        config.rules = rulesSpinner.selectedItem.toString()
        config.customRules = Rules.rulesToString(getRulesFromSpinners(spinners))
        config.boardSize = sizeSeekBar.progress
        config.voidPercentage = voidSeekBar.progress
        ConfigManagerImpl(this).save(config)
    }

    private fun selectValue(spinner: Spinner, value: Any) {
        for (i in 0 until spinner.count) {
            if (spinner.getItemAtPosition(i) == value) {
                spinner.setSelection(i)
                break
            }
        }
    }

    private fun getRulesFromSpinners(spinners: List<Spinner>) : Rules {
        var rules = ConwayRules()
        var nNeighbours = 0
        for(spinner in spinners) {
            if(spinner.selectedItem.toString() == "Live") {
                rules.addTransition(nNeighbours++, Cell.Transition.LIVE)
            } else if(spinner.selectedItem.toString() == "Persist") {
                rules.addTransition(nNeighbours++, Cell.Transition.PERSIST)
            } else {
                rules.addTransition(nNeighbours++, Cell.Transition.DIE)
            }
        }
        return rules
    }

    fun setSpinnersFromRules(rules: Rules) {
        val nNeighbours = rules.getMaxNumberOfNeighbours()
        for(i in 0 until rows.size) {
            if(i<=nNeighbours) {
                rows[i].visibility = View.VISIBLE
                setSpinnerFromTransition(spinners[i],rules.getTransition(i))
            } else {
                rows[i].visibility = View.INVISIBLE
            }
        }
    }

    private fun setSpinnerFromTransition(spinner : Spinner, transition : Cell.Transition?) {
        var id = 0
        if(transition != null) {
            if(transition == Cell.Transition.PERSIST) {
                id=2
            } else if(transition == Cell.Transition.LIVE) {
                id=1
            }
        }
        spinner.setSelection(id)
    }
}
