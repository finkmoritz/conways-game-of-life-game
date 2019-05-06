package finkmoritz.conwaysgameoflifegame.game.view.path

import android.graphics.Path

class TriangularPath(x: Float, y: Float, width: Float, height: Float, upward: Boolean): Path() {

    init {
        if(upward) {
            moveTo(x,y+height)
            lineTo(x+0.5f*width,y)
            lineTo(x+width,y+height)
            lineTo(x,y+height)
        } else {
            moveTo(x,y)
            lineTo(x+width,y)
            lineTo(x+0.5f*width,y+height)
            lineTo(x,y)
        }
        close()
    }
}