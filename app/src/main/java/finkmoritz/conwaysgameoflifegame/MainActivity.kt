package finkmoritz.conwaysgameoflifegame

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.MobileAds

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MobileAds.initialize(this,"ca-app-pub-3567921551904880~7515809312")
    }

    override fun onBackPressed() {
        //prevent actions
        //super.onBackPressed()
    }

    fun startConfigActivity(view: View) {
        val intent = Intent(this, ConfigActivity::class.java).apply {}
        startActivity(intent)
    }
}
