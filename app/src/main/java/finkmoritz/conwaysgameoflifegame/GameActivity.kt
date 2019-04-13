package finkmoritz.conwaysgameoflifegame

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import finkmoritz.conwaysgameoflifegame.config.ConfigDO

class GameActivity : AppCompatActivity() {

    lateinit var mAdView : AdView
    lateinit var menuView : View
    lateinit var gameView : View
    lateinit var menuButton : Button
    lateinit var initialConfig : ConfigDO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        menuView = findViewById(R.id.gameMenu)
        gameView = findViewById(R.id.gameLayout)

        menuButton = findViewById(R.id.gameMenuButton)

        initialConfig = intent.getSerializableExtra("initialConfig") as ConfigDO
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
        menuView.visibility = View.VISIBLE
    }

    fun hideMenu(view: View) {
        menuView.visibility = View.GONE
        showGame()
    }

    private fun showGame() {
        gameView.visibility = View.VISIBLE
    }

    private fun hideGame() {
        gameView.visibility = View.INVISIBLE
    }
}
