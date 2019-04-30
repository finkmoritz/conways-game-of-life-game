package finkmoritz.conwaysgameoflifegame

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import android.widget.Spinner
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import finkmoritz.conwaysgameoflifegame.board.Board
import finkmoritz.conwaysgameoflifegame.cell.Cell
import finkmoritz.conwaysgameoflifegame.config.*
import finkmoritz.conwaysgameoflifegame.persistence.AppSharedPreferences
import finkmoritz.conwaysgameoflifegame.rules.ConwayRules
import finkmoritz.conwaysgameoflifegame.rules.Rules


class ConfigActivity : AppCompatActivity() {

    lateinit var cellsSpinner: Spinner
    lateinit var rulesSpinner: Spinner
    private lateinit var rows: List<TableRow>
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

        for(spinner in spinners) {
            spinner.onItemSelectedListener = OnSpinnerSelectedListener()
        }

        onCellsSelectedListener = OnCellsSelectedListener(this)
        onRulesSelectedListener = OnRulesSelectedListener(this)

        cellsSpinner.onItemSelectedListener = onCellsSelectedListener
        rulesSpinner.onItemSelectedListener = onRulesSelectedListener

        sizeLabel = findViewById(R.id.sizeLabel)
        sizeSeekBar = findViewById(R.id.sizeSeekBar)
        sizeSeekBar.setOnSeekBarChangeListener(OnSizeSeekBarChangeListener(this))

        voidLabel = findViewById(R.id.voidLabel)
        voidSeekBar = findViewById(R.id.voidSeekBar)
        voidSeekBar.setOnSeekBarChangeListener(OnVoidSeekBarChangeListener(this))

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

    override fun onBackPressed() {
        startMainActivity()
    }

    fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java).apply {}
        startActivity(intent)
    }

    fun startGameActivity(view: View) {
        val config = saveConfig()
        val intent = Intent(this,GameActivity::class.java).apply {}
        intent.putExtra("initialConfig",config)
        startActivity(intent)
    }

    fun enableAllSpinners(spinners : List<Spinner>, isEnabled : Boolean) {
        for(spinner in spinners) {
            spinner.isEnabled = isEnabled
        }
    }

    private fun loadConfig() {
        var config = AppSharedPreferences(this).load("config",ConfigSerializable()) as ConfigSerializable?
        if(config == null) {
            config = ConfigSerializable()
        }
        selectValue(cellsSpinner,Board.topologyToString(config.boardTopology))
        selectValue(rulesSpinner,config.rules)
        setSpinnersFromRules(config.customRules)
        sizeSeekBar.progress = config.boardSize
        voidSeekBar.progress = config.voidPercentage
    }

    private fun saveConfig() : ConfigSerializable {
        val config = ConfigSerializable()
        config.boardTopology = Board.topologyFromString(cellsSpinner.selectedItem as String)
        config.rules = rulesSpinner.selectedItem.toString()
        config.customRules = getRulesFromSpinners(spinners)
        config.boardSize = sizeSeekBar.progress
        config.voidPercentage = voidSeekBar.progress
        AppSharedPreferences(this).save("config",config)
        return config
    }

    private fun selectValue(spinner: Spinner, value: Any) {
        for (i in 0 until spinner.count) {
            if (spinner.getItemAtPosition(i) == value) {
                spinner.setSelection(i)
                break
            }
        }
    }

    private fun getRulesFromSpinners(spinners: List<Spinner>) : ConwayRules {
        var rules = ConwayRules()
        var nNeighbours = 0
        for(spinner in spinners) {
            if(rows[nNeighbours].visibility == View.VISIBLE) {
                when {
                    spinner.selectedItem.toString() == "Live" -> rules.setTransition(nNeighbours++, Cell.Transition.LIVE)
                    spinner.selectedItem.toString() == "Persist" -> rules.setTransition(nNeighbours++, Cell.Transition.PERSIST)
                    else -> rules.setTransition(nNeighbours++, Cell.Transition.DIE)
                }
            } else {
                break
            }
        }
        return rules
    }

    fun setSpinnersFromRules(rules: Rules) {
        for(i in 0 until rows.size) {
            if(rules.getTransition(i) != null) {
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
