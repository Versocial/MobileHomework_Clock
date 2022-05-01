package com.example.mobilehomework_clock.clock_view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * TODO: document your custom view class.
 */
class MyClock : View {
    private val widthOfCircle:Float=0.08f
    private val defaultWidth=200
    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init(attrs, defStyle)
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(measure(widthMeasureSpec), measure(heightMeasureSpec))
    }

    private fun measure(origin: Int): Int {
        var result: Int = defaultWidth
        val specMode = MeasureSpec.getMode(origin)
        val specSize = MeasureSpec.getSize(origin)
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize
        } else {
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize)
            }
        }
        return result
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        val paint: Paint= Paint()
        paint.color=Color.BLACK
        paint.isAntiAlias=true
        paint.strokeWidth= (widthOfCircle*Math.min(width/2,height/2))*widthOfCircle
        val r=(Math.min(width/2,height/2))-paint.strokeWidth
        paint.style=Paint.Style.STROKE
        canvas.drawCircle((width/2).toFloat(), (height/2).toFloat(),
                r,paint)


        super.onDraw(canvas)

    }
}