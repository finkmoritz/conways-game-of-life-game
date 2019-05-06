package finkmoritz.conwaysgameoflifegame.game.view

import android.annotation.TargetApi
import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.view.View
import finkmoritz.conwaysgameoflifegame.board.Board
import finkmoritz.conwaysgameoflifegame.board.HexagonalBoard
import finkmoritz.conwaysgameoflifegame.board.QuadrangularBoard
import finkmoritz.conwaysgameoflifegame.board.TriangularBoard
import finkmoritz.conwaysgameoflifegame.cell.Cell
import finkmoritz.conwaysgameoflifegame.config.ConfigSerializable
import finkmoritz.conwaysgameoflifegame.game.view.path.TriangularPath


class GameView @TargetApi(Build.VERSION_CODES.LOLLIPOP)
constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
    : View(context, attrs, defStyleAttr, defStyleRes) {

    private val BORDER_RADIUS: Float = 20.0f
    private val BACKGROUND_COLOR = Color.BLACK

    private lateinit var initialConfig: ConfigSerializable
    private lateinit var board: Board

    private val borderPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        color = BACKGROUND_COLOR
        setBackgroundColor(BACKGROUND_COLOR)
        pathEffect = CornerPathEffect(BORDER_RADIUS)
    }
    private val voidPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = Color.BLACK
        setBackgroundColor(BACKGROUND_COLOR)
        pathEffect = CornerPathEffect(BORDER_RADIUS)
    }
    private val boardPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = Color.WHITE
        setBackgroundColor(BACKGROUND_COLOR)
        pathEffect = CornerPathEffect(BORDER_RADIUS)
    }
    private val cellPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL_AND_STROKE
        color = Color.rgb(50,50,225)
        setBackgroundColor(BACKGROUND_COLOR)
        pathEffect = CornerPathEffect(BORDER_RADIUS)
    }


    @JvmOverloads
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
            : this(context, attrs, defStyleAttr, 0)

    fun initialize(config: ConfigSerializable) {
        initialConfig = config

        when(config.boardTopology) {
            Board.Topology.TRIANGULAR -> board = TriangularBoard(config.boardSize,config.boardSize)
            Board.Topology.HEXAGONAL -> board = HexagonalBoard(config.boardSize,config.boardSize)
            else -> board = QuadrangularBoard(config.boardSize,config.boardSize)
        }
        board.randomize(config.voidPercentage)

        invalidate() //calls onDraw method
    }

    override fun onDraw(canvas: Canvas) {
        if(board is TriangularBoard) {
            drawTriangularBoard(canvas)
        } else if(board is HexagonalBoard) {
            drawHexagonalBoard(canvas)
        } else {
            drawQuadrangularBoard(canvas)
        }
    }

    private fun drawTriangularBoard(canvas: Canvas) {
        canvas.apply {
            val cellWidth : Float = 2*width.toFloat()/(board.width()+1)
            val cellHeight : Float = height.toFloat()/board.height()
            for(x in 0 until board.width()) {
                for(y in 0 until board.height()) {
                    var offsetY : Float
                    var path: TriangularPath
                    if(x%2 != y%2) { //upward
                        path = TriangularPath(x/2.0f*cellWidth,y*cellHeight,cellWidth,cellHeight,true)
                        offsetY = 0.1f
                    } else { //downward
                        path = TriangularPath(x/2.0f*cellWidth,y*cellHeight,cellWidth,cellHeight,false)
                        offsetY = -0.1f
                    }
                    if(board.getCellState(x,y) == Cell.State.DEAD) {
                        drawPath(path,boardPaint)
                    } else if(board.getCellState(x,y) == Cell.State.ALIVE) {
                        drawPath(path,boardPaint)
                        cellPaint.apply { shader = RadialGradient((x/2.0f+0.25f)*cellWidth, (y+0.25f+offsetY)*cellHeight, 0.3f*cellWidth, Color.rgb(50,50,225), Color.argb(180,50,50,225), Shader.TileMode.MIRROR) }
                        drawCircle((x/2.0f+0.5f)*cellWidth,(y+0.5f+offsetY)*cellHeight,0.15f*cellWidth, cellPaint)
                    } else {
                        drawPath(path,voidPaint)
                    }
                    drawPath(path,borderPaint)
                }
            }
        }
    }

    private fun drawHexagonalBoard(canvas: Canvas) {
        canvas.apply {
            //TODO
        }
    }

    private fun drawQuadrangularBoard(canvas: Canvas) {
        canvas.apply {
            val cellWidth : Float = width.toFloat()/board.width()
            val cellHeight : Float = height.toFloat()/board.height()
            for(x in 0 until board.width()) {
                for(y in 0 until board.height()) {
                    val cellRect = RectF(x*cellWidth,y*cellHeight,(x+1)*cellWidth,(y+1)*cellHeight)
                    if(board.getCellState(x,y) == Cell.State.DEAD) {
                        drawRect(cellRect,boardPaint)
                    } else if(board.getCellState(x,y) == Cell.State.ALIVE) {
                        drawRect(cellRect,boardPaint)
                        cellPaint.apply { shader = RadialGradient((x+0.25f)*cellWidth, (y+0.25f)*cellHeight, 0.5f*cellWidth, Color.rgb(50,50,225), Color.argb(122,50,50,225), Shader.TileMode.MIRROR) }
                        drawCircle((x+0.5f)*cellWidth,(y+0.5f)*cellHeight,0.25f*cellWidth, cellPaint)
                    } else {
                        drawRect(cellRect,voidPaint)
                    }
                    drawRect(cellRect,borderPaint)
                }
            }
        }
    }
}