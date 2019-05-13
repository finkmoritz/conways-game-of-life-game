package finkmoritz.conwaysgameoflifegame.game.player

import java.io.Serializable

class ConwayPlayer(private var color: Int): Serializable {

    fun setColor(color: Int) {
        this.color = color
    }

    fun getColor(): Int = color
}