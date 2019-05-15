package com.alabi.shopifywordsearch.util.views

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.TextView

/**
 * Creates a text view in square fashion using the width
 * @author Samer Alabi
 */
class WordSearchTextView: TextView {
    constructor(context: Context): super(context)

    constructor(context: Context, attrs: AttributeSet): super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyle: Int): super(context, attrs, defStyle)

    init {
        setParams()
        this.gravity = Gravity.CENTER
        this.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
        this.typeface = Typeface.DEFAULT_BOLD
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        // Set equal width and height
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
    }

    /**
     * Sets default layout params
     */
    private fun setParams() {
        // Create params
        val params = GridLayout.LayoutParams()

        // Set parameters
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT
        params.width = 0
        params.rightMargin = 5
        params.topMargin = 5
        params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)

        // Return params
        this.layoutParams = params
    }
}