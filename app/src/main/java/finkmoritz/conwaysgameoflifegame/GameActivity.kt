package finkmoritz.conwaysgameoflifegame

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import finkmoritz.conwaysgameoflifegame.config.Config
import finkmoritz.conwaysgameoflifegame.game.Game
import finkmoritz.conwaysgameoflifegame.game.view.GameView

class GameActivity : AppCompatActivity() {

    lateinit var mAdView : AdView
    lateinit var menuLayout : View
    lateinit var gameLayout : View
    lateinit var menuButton : Button
    lateinit var initialConfig : Config
    lateinit var gameView : GameView
    lateinit var game: Game

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        menuLayout = findViewById(R.id.gameMenu)
        gameLayout = findViewById(R.id.gameLayout)

        menuButton = findViewById(R.id.gameMenuButton)

        gameView = findViewById(R.id.gameView)

        initialConfig = intent.getSerializableExtra("initialConfig") as Config

        game = Game(initialConfig)
        gameView.initialize(game.board)
    }

    override fun onBackPressed() {
        showMenu(menuButton)
    }

    fun startConfigActivity(view: View) {
        val intent = Intent(this, ConfigActivity::class.java).apply {}
        startActivity(intent)
    }

    fun showMenu(view: View) {
        hideGame()
        menuLayout.visibility = View.VISIBLE
    }

    fun hideMenu(view: View) {
        menuLayout.visibility = View.GONE
        showGame()
    }

    private fun showGame() {
        gameLayout.visibility = View.VISIBLE
    }

    private fun hideGame() {
        gameLayout.visibility = View.INVISIBLE
    }

    fun nextTurn(view: View) {
        game.nextTurn()
        gameView.clearSelection()
        gameView.redraw()
    }
}
