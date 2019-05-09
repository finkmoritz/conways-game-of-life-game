package finkmoritz.conwaysgameoflifegame.game.view

import android.annotation.TargetApi
import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.view.View
import finkmoritz.conwaysgameoflifegame.board.Board
import finkmoritz.conwaysgameoflifegame.board.HexagonalBoard
import finkmoritz.conwaysgameoflifegame.board.TriangularBoard
import finkmoritz.conwaysgameoflifegame.cell.Cell
import finkmoritz.conwaysgameoflifegame.game.view.paint.CellPaint
import finkmoritz.conwaysgameoflifegame.game.view.path.TriangularPath


class GameView @TargetApi(Build.VERSION_CODES.LOLLIPOP)
constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
    : View(context, attrs, defStyleAttr, defStyleRes) {

    private val backgroundColor = Color.BLACK
    private val boardColor = Color.WHITE
    private val selectedColor = Color.LTGRAY
    private val cellColor = Color.rgb(50,50,225)
    private val cellColorTransparent = Color.argb(122,50,50,225)
    private val borderPaint = CellPaint(Paint.Style.STROKE,backgroundColor)

    private var selectedCells = mutableListOf<Point>()

    lateinit var board: Board

    @JvmOverloads
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
            : this(context, attrs, defStyleAttr, 0)

    fun initialize(board: Board) {
        this.board = board

        setBackgroundColor(backgroundColor)
        redraw()

        setOnTouchListener(GameViewOnTouchListener(this))
    }

    fun redraw() = invalidate()

    fun onTouch(x: Float, y: Float) {
        val selectedCell = boardCoordFromTouchCoord(x,y)
        toggleSelection(selectedCell)
    }

    fun toggleSelection(coord: Point) {
        if(board.getCellState(coord.x,coord.y) != Cell.State.VOID) {
            if(selectedCells.contains(coord)) {
                selectedCells.remove(coord)
            } else {
                selectedCells.add(coord)
            }
            toggleCellState(coord)
            redraw()
        }
    }

    fun clearSelection() = selectedCells.clear()

    private fun toggleCellState(coord: Point) {
        when(board.getCellState(coord.x,coord.y)) {
            Cell.State.ALIVE -> board.setCellState(coord.x,coord.y,Cell.State.DEAD)
            Cell.State.DEAD -> board.setCellState(coord.x,coord.y,Cell.State.ALIVE)
        }
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
                    val cellState = board.getCellState(x,y)
                    var paint: CellPaint
                    if(selectedCells.contains(Point(x,y))) {
                        paint = CellPaint(Paint.Style.FILL,selectedColor)
                    } else if(cellState == Cell.State.DEAD || cellState == Cell.State.ALIVE) {
                        paint = CellPaint(Paint.Style.FILL,boardColor)
                    } else {
                        paint = CellPaint(Paint.Style.FILL,backgroundColor)
                    }
                    drawPath(path,paint)
                    if(cellState == Cell.State.ALIVE) {
                        val cellPaint = CellPaint(Paint.Style.FILL_AND_STROKE,cellColor)
                        cellPaint.apply { shader = RadialGradient((x/2.0f+0.25f)*cellWidth, (y+0.25f+offsetY)*cellHeight, 0.3f*cellWidth, cellColor, cellColorTransparent, Shader.TileMode.MIRROR) }
                        drawCircle((x/2.0f+0.5f)*cellWidth,(y+0.5f+offsetY)*cellHeight,0.15f*cellWidth, cellPaint)
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
                    var cellRect = RectF(x*cellWidth,y*cellHeight,(x+1)*cellWidth,(y+1)*cellHeight)
                    var paint: CellPaint
                    val cellState = board.getCellState(x,y)
                    if(selectedCells.contains(Point(x,y))) {
                        paint = CellPaint(Paint.Style.FILL,selectedColor)
                    } else if(cellState == Cell.State.DEAD || cellState == Cell.State.ALIVE) {
                        paint = CellPaint(Paint.Style.FILL,boardColor)
                    } else {
                        paint = CellPaint(Paint.Style.FILL,backgroundColor)
                    }
                    drawRect(cellRect,paint)
                    if(board.getCellState(x,y) == Cell.State.ALIVE) {
                        var cellPaint = CellPaint(Paint.Style.FILL_AND_STROKE,cellColor)
                        cellPaint.apply { shader = RadialGradient((x+0.25f)*cellWidth, (y+0.25f)*cellHeight, 0.5f*cellWidth, cellColor, cellColorTransparent, Shader.TileMode.MIRROR) }
                        drawCircle((x+0.5f)*cellWidth,(y+0.5f)*cellHeight,0.25f*cellWidth, cellPaint)
                    }
                    drawRect(cellRect,borderPaint)
                }
            }
        }
    }

    private fun boardCoordFromTouchCoord(touchX: Float, touchY: Float): Point {
        var boardCoord: Point
        when(board.getTopology()) {
            Board.Topology.TRIANGULAR -> boardCoord = triangularBoardCoordFromTouchCoord(touchX,touchY)
            Board.Topology.HEXAGONAL -> boardCoord = hexagonalBoardCoordFromTouchCoord(touchX,touchY)
            else -> boardCoord = quadrangularBoardCoordFromTouchCoord(touchX,touchY)
        }
        return boardCoord
    }

    private fun triangularBoardCoordFromTouchCoord(touchX: Float, touchY: Float): Point {
        var boardCoord = Point(0,0)
        val boardSize = board.width().toFloat()
        //TODO
        return boardCoord
    }

    private fun hexagonalBoardCoordFromTouchCoord(touchX: Float, touchY: Float): Point {
        var boardCoord = Point(0,0)
        //TODO
        return boardCoord
    }

    private fun quadrangularBoardCoordFromTouchCoord(touchX: Float, touchY: Float): Point {
        var boardCoord = Point(0,0)
        val boardSize = board.width().toFloat()
        boardCoord.x = (touchX/width.toFloat()*boardSize).toInt()
        boardCoord.y = (touchY/height.toFloat()*boardSize).toInt()
        return boardCoord
    }
}