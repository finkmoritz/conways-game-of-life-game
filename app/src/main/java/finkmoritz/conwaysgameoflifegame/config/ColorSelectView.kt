package finkmoritz.conwaysgameoflifegame.config

import android.annotation.TargetApi
import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import finkmoritz.conwaysgameoflifegame.cell.SimpleCell
import finkmoritz.conwaysgameoflifegame.game.view.paint.CellPaint


class ColorSelectView @TargetApi(Build.VERSION_CODES.LOLLIPOP)
constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
    : View(context, attrs, defStyleAttr, defStyleRes) {

    private val backgroundColor = Color.WHITE
    private val borderPaint = CellPaint(Paint.Style.STROKE,backgroundColor)

    var currentColorIndex = 0

    @JvmOverloads
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
            : this(context, attrs, defStyleAttr, 0)

    fun initialize(colorIndex: Int) {
        setBackgroundColor(backgroundColor)
        setColor(colorIndex)

        redraw()

        setOnTouchListener { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> onTouch(event.x,event.y)
            }
            v?.onTouchEvent(event) ?: true
        }
    }

    fun redraw() = invalidate()

    private fun setColor(index: Int) {
        currentColorIndex = index
    }

    fun onTouch(x: Float, y: Float) {
        toggleSelection()
        redraw()
    }

    override fun onDraw(canvas: Canvas) {
        val viewRect = Rect(0,0,width,height)
        val cellPaint = CellPaint(Paint.Style.FILL,backgroundColor)
        canvas.apply {
            drawRect(viewRect,cellPaint)
            val cellColor = SimpleCell.colors[currentColorIndex]
            var cellPaint = CellPaint(Paint.Style.FILL_AND_STROKE,cellColor)
            val cellColorTransparent = Color.argb(122,Color.red(cellColor)/2,Color.green(cellColor)/2,Color.blue(cellColor)/2)
            cellPaint.apply { shader = RadialGradient(0.25f*width, 0.25f*height, 0.5f*width, cellColor, cellColorTransparent, Shader.TileMode.MIRROR) }
            drawCircle(0.5f*width,0.5f*height,0.25f*width, cellPaint)
            drawRect(viewRect,borderPaint)
        }
    }

    private fun toggleSelection() {
        val nextIndex = (++currentColorIndex)%SimpleCell.colors.size
        setColor(nextIndex)
    }
}