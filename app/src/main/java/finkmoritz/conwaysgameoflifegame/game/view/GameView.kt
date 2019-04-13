package finkmoritz.conwaysgameoflifegame.game.view

import android.annotation.TargetApi
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.os.Build
import android.util.AttributeSet
import android.view.View
import finkmoritz.conwaysgameoflifegame.board.Board
import finkmoritz.conwaysgameoflifegame.board.HexagonalBoard
import finkmoritz.conwaysgameoflifegame.board.QuadrangularBoard
import finkmoritz.conwaysgameoflifegame.board.TriangularBoard
import finkmoritz.conwaysgameoflifegame.cell.Cell
import finkmoritz.conwaysgameoflifegame.config.ConfigDO


class GameView @TargetApi(Build.VERSION_CODES.LOLLIPOP)
constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
    : View(context, attrs, defStyleAttr, defStyleRes) {

    private val BORDER_RADIUS: Float = 20.0f
    private val BACKGROUND_COLOR = Color.BLACK

    private lateinit var initialConfig: ConfigDO
    private lateinit var board: Board

    private val borderPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        color = BACKGROUND_COLOR
        setBackgroundColor(BACKGROUND_COLOR)
    }
    private val voidPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = Color.BLACK
        setBackgroundColor(BACKGROUND_COLOR)
    }
    private val deadPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = Color.WHITE
        setBackgroundColor(BACKGROUND_COLOR)
    }
    private val alivePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = Color.rgb(50,50,225)
        setBackgroundColor(BACKGROUND_COLOR)
    }


    @JvmOverloads
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
            : this(context, attrs, defStyleAttr, 0)

    fun initialize(config: ConfigDO) {
        initialConfig = config

        when(Board.topologyFromString(config.boardTopology)) {
            Board.Topology.TRIANGULAR -> board = TriangularBoard(config.boardSize,config.boardSize)
            Board.Topology.HEXAGONAL -> board = HexagonalBoard(config.boardSize,config.boardSize)
            else -> board = QuadrangularBoard(config.boardSize,config.boardSize)
        }
        board.randomize(config.voidPercentage)

        invalidate() //calls onDraw method
    }

    override fun onDraw(canvas: Canvas) {
        val cellWidth : Float = width.toFloat()/board.width()
        val cellHeight : Float = height.toFloat()/board.height()

        canvas.apply {
            if(board is TriangularBoard) {

            } else if(board is HexagonalBoard) {

            } else {
                for(x in 0 until board.width()) {
                    for(y in 0 until board.height()) {
                        var paint = voidPaint
                        if(board.getCellState(x,y) == Cell.State.DEAD) {
                            paint = deadPaint
                        } else if(board.getCellState(x,y) == Cell.State.ALIVE) {
                            paint = alivePaint
                        }
                        val cellRect = RectF(x*cellWidth,y*cellHeight,(x+1)*cellWidth,(y+1)*cellHeight)
                        drawRoundRect(cellRect,BORDER_RADIUS,BORDER_RADIUS,paint)
                        drawRoundRect(cellRect,BORDER_RADIUS,BORDER_RADIUS,borderPaint)
                    }
                }
            }
        }
    }
}