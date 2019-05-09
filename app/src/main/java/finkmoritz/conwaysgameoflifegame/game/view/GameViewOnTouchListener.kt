package finkmoritz.conwaysgameoflifegame.game.view

import android.view.MotionEvent
import android.view.View


class GameViewOnTouchListener(val gameView: GameView): View.OnTouchListener {
    override fun onTouch(v: View, event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            gameView.onTouch(event.x,event.y)
        }
        return true
    }
}