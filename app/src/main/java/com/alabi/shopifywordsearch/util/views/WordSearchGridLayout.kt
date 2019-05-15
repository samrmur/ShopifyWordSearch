package com.alabi.shopifywordsearch.util.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.GridLayout
import kotlin.math.floor

/**
 * Modifies grid layout for word search purposes (drawing lines over found words
 * @author Samer Alabi
 */
class WordSearchGridLayout: GridLayout {
    // Declare drawing attributes
    private val lines: ArrayList<Pair<Pair<Int, Int>, Pair<Int, Int>>> = ArrayList()
    private val permanentPaint = Paint()
    private val temporaryPaint = Paint()
    private var touchFinished: (startRow: Int, startColumn: Int, endRow: Int, endColumn: Int) -> Unit = { _, _, _, _ -> }

    // Declare initial rows
    private var initialRow = -1
    private var initialColumn = -1
    private var currentRow = -1
    private var currentColumn = -1

    constructor(context: Context): super(context)

    constructor(context: Context, attributeSet: AttributeSet): super(context, attributeSet)

    constructor(context: Context, attributeSet: AttributeSet, defStyle: Int): super(context, attributeSet, defStyle)

    init {
        // Allow drawing (set to true by default
        setWillNotDraw(false)

        // Set parameters for permanent paint
        permanentPaint.color = Color.GREEN
        permanentPaint.strokeWidth = 60f
        permanentPaint.isAntiAlias = true
        permanentPaint.strokeCap = Paint.Cap.ROUND

        // Set parameters for temporary paint
        temporaryPaint.color = Color.LTGRAY
        temporaryPaint.strokeWidth = 60f
        temporaryPaint.isAntiAlias = true
        temporaryPaint.strokeCap = Paint.Cap.ROUND
    }

    @Suppress("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        // Check which event has been called
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                // Calculate initial row and column
                initialRow = floor(event.x / (width / rowCount)).toInt()
                initialColumn = floor(event.y / (height / columnCount)).toInt()

                // Set current row and column to initial
                currentRow = initialRow
                currentColumn = initialColumn
            }
            MotionEvent.ACTION_MOVE -> {
                // Calculate current row and column
                currentRow = floor(event.x / (width / rowCount)).toInt()
                currentColumn = floor(event.y / (height / columnCount)).toInt()
            }
            MotionEvent.ACTION_UP -> {
                // Run on touch finished
                touchFinished(initialRow, initialColumn, currentRow, currentColumn)

                // Reset variables
                initialRow = -1
                initialColumn = -1
                currentRow = -1
                currentColumn = -1
            }
        }

        // Invalidate (for re-drawing)
        invalidate()

        // Return true (to activate other motion events)
        return true
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        // Add all permanent lines to the canvas
        for (line in lines) {
            // Calculate the middle positions of the elements
            val middleFirst = calculateLinePoints(line.first.first, line.first.second)
            val middleSecond = calculateLinePoints(line.second.first, line.second.second)

            // Draw line
            canvas?.drawLine(middleFirst.first, middleFirst.second, middleSecond.first, middleSecond.second, permanentPaint)
        }

        // Add temporary line if inside the canvas
        if (currentColumn >= 0 && currentRow >= 0 && currentColumn < columnCount && currentRow < rowCount) {
            // Calculate the middle positions of the elements
            val middleFirst = calculateLinePoints(initialRow, initialColumn)
            val middleSecond = calculateLinePoints(currentRow, currentColumn)

            // Draw line
            canvas?.drawLine(middleFirst.first, middleFirst.second, middleSecond.first, middleSecond.second, temporaryPaint)
        }
    }

    /**
     * Adds a permanently drawn line to be drawn on the canvas
     * @param startRow starting row of the line
     * @param startColumn starting column of the line
     * @param endRow ending row of the line
     * @param endColumn ending column of the line
     */
    fun addPermanentLine(startRow: Int, startColumn: Int, endRow: Int, endColumn: Int) = lines.add(Pair(Pair(startRow, startColumn), Pair(endRow, endColumn)))

    /**
     * Sets a listener for when the user finishes a gesture {@link #MotionEvent.ACTION_UP ACTION_UP}
     * @param touchFinished function to run when user finishes gesture
     */
    fun setOnTouchFinished(touchFinished: (startRow: Int, startColumn: Int, endRow: Int, endColumn: Int) -> Unit) {
        this.touchFinished = touchFinished
    }

    /**
     * Calculates points that can be used as a point for drawing a line on a canvas
     * @param column column number of the point
     * @param row row number of the point
     * @return pair containing x and y coordinates
     */
    private fun calculateLinePoints(row: Int, column: Int): Pair<Float, Float> {
        // Get child
        val child = getChildAt(column * rowCount + row) as WordSearchTextView

        // Get middle of element
        val rowMiddle = (child.left + child.right).toFloat() / 2
        val columnMiddle = (child.top + child.bottom).toFloat() / 2

        // Return result
        return Pair(rowMiddle, columnMiddle)
    }
}