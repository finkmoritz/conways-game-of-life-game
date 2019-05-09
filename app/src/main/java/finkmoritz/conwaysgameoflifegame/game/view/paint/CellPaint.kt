package finkmoritz.conwaysgameoflifegame.game.view.paint

import android.graphics.CornerPathEffect
import android.graphics.Paint

class CellPaint(style: Style, color: Int): Paint(ANTI_ALIAS_FLAG) {

    private val BORDER_RADIUS: Float = 20.0f

    init {
        this.style = style
        this.color = color
        this.pathEffect = CornerPathEffect(BORDER_RADIUS)
    }
}