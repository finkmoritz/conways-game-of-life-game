package finkmoritz.conwaysgameoflifegame

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import finkmoritz.conwaysgameoflifegame.cell.Cell
import finkmoritz.conwaysgameoflifegame.config.*
import finkmoritz.conwaysgameoflifegame.rules.ConwayRules
import finkmoritz.conwaysgameoflifegame.rules.Rules
import kotlinx.android.synthetic.main.activity_config.*


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
    private lateinit var backButton : Button

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
        sizeSeekBar.setOnSeekBarChangeListener(OnSizeSeekBarChangeListener(this))

        voidLabel = findViewById(R.id.voidLabel)
        voidSeekBar = findViewById(R.id.voidSeekBar)
        voidSeekBar.setOnSeekBarChangeListener(OnVoidSeekBarChangeListener(this))

        backButton = findViewById(R.id.configBackButton)

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
        startMainActivity(configBackButton)
    }

    fun startMainActivity(view: View) {
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
        val config = ConfigManagerImpl(this).load()
        selectValue(cellsSpinner,config.boardTopology)
        selectValue(rulesSpinner,config.rules)
        setSpinnersFromRules(Rules.stringToRules(config.customRules))
        sizeSeekBar.progress = config.boardSize
        voidSeekBar.progress = config.voidPercentage
    }

    private fun saveConfig() : ConfigDO {
        val config = ConfigDO()
        config.boardTopology = cellsSpinner.selectedItem.toString()
        config.rules = rulesSpinner.selectedItem.toString()
        config.customRules = Rules.rulesToString(getRulesFromSpinners(spinners))
        config.boardSize = sizeSeekBar.progress
        config.voidPercentage = voidSeekBar.progress
        ConfigManagerImpl(this).save(config)
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

    private fun getRulesFromSpinners(spinners: List<Spinner>) : Rules {
        var rules = ConwayRules()
        var nNeighbours = 0
        for(spinner in spinners) {
            if(rows[nNeighbours].visibility == View.VISIBLE) {
                when {
                    spinner.selectedItem.toString() == "Live" -> rules.addTransition(nNeighbours++, Cell.Transition.LIVE)
                    spinner.selectedItem.toString() == "Persist" -> rules.addTransition(nNeighbours++, Cell.Transition.PERSIST)
                    else -> rules.addTransition(nNeighbours++, Cell.Transition.DIE)
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
