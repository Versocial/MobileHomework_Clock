package com.example.mobilehomework_clock.clock_view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import java.util.*
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

/**
 * TODO: document your custom view class.
 */
class MyClock : View {
    private val widthOfCircumference:Float=0.08F
    private val lenOfLabelBig:Float= 0.13F
    private val lenOfLabelSmall:Float= 0.09F
    private val widthOfLabelBig:Float=0.06F
    private val widthOfLabelSmall:Float= 0.04F
    private val calendar = Calendar.getInstance()
    
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
        //init
        val paint: Paint= Paint()
        paint.isAntiAlias=true
        val viewRadius=(Math.min(width/2,height/2))
        val viewOX=(width/2).toFloat()
        val viewOY=(height/2).toFloat()
        //draw circle
        paint.color=Color.BLACK
        paint.strokeWidth= viewRadius*widthOfCircumference
        val radius=viewRadius-paint.strokeWidth
        paint.style=Paint.Style.STROKE
        canvas.drawCircle(viewOX,viewOY , radius,paint)
        //draw hour/minute/second degree label
        paint.style=Paint.Style.FILL_AND_STROKE
        var i=0
        while(i<60){
            val len=radius*(if(i%5==0){lenOfLabelBig}else{lenOfLabelSmall})
            val width=radius*(if(i%5==0){widthOfLabelBig}else{widthOfLabelSmall})
            paint.strokeWidth=width
            canvas.drawLine(
                viewOX+radius,viewOY,
                viewOX+radius-len,viewOY,paint
            )
            canvas.rotate(6F,viewOX,viewOY)
            i++
        }
        //draw needles
//        paint.strokeWidth=widthOfLabelSmall*0.7F
        calendar.timeInMillis = System.currentTimeMillis() //设置Calendar为当前时间
        val hours: Int = calendar.get(Calendar.HOUR) % 12 //获取当前的小时
        val minutes: Int = calendar.get(Calendar.MINUTE) //获取当前的分钟
        val seconds: Int = calendar.get(Calendar.SECOND) //获取当前的秒

        drawNeedle(hours*60*60+minutes*60+seconds,12*60*60,radius*0.3,radius*0.03,canvas,paint)
        drawNeedle(minutes*60+seconds,60*60,radius*0.45,radius*0.05,canvas,paint)
        drawNeedle(seconds,60,radius*0.65,radius*0.09,canvas,paint)
        super.onDraw(canvas)
        postInvalidateDelayed(500)
    }

    fun drawNeedle(time:Int,timeCycle:Int,len:Double,rLen:Double,canvas: Canvas,paint: Paint){
        val angle=time*2*PI/timeCycle.toDouble()
        Log.d("clock","$angle")
        val startX=width/2 - rLen* sin(angle)
        val startY=height/2 + rLen* cos(angle)
        val stopX=width/2 + len* sin(angle)
        val stopY=height/2 - len* cos(angle)
        canvas.drawLine(
            startX.toFloat(),startY.toFloat(),
            stopX.toFloat(),stopY.toFloat(),
            paint
        )
    }


}