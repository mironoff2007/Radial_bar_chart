package ru.mironov.radialbarchart

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.graphics.RectF

import android.graphics.CornerPathEffect




class CustomView(
    context: Context,
    attributesSet: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int
) : View(context, attributesSet, defStyleAttr, defStyleRes) {

    var radius=100
    val mX=radius*1F
    val mY=radius*1F

    var sweepAngle=0F

    var strokeWidth=20F

    constructor(context: Context, attributesSet: AttributeSet?, defStyleAttr: Int) : this(context, attributesSet, defStyleAttr, 0)

    constructor(context: Context, attributesSet: AttributeSet?) : this(context, attributesSet, 0)

    constructor(context: Context) : this(context, null)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        setMeasuredDimension(radius*2,radius*2)
    }


    fun setAngle(sweepAngle:Float){
        this.sweepAngle=sweepAngle
        this.invalidate()
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        val paint= Paint()
        paint.color = Color.BLUE

        canvas.drawCircle(radius.toFloat(),radius.toFloat(),radius.toFloat(),paint)

        paint.style = Paint.Style.STROKE
        paint.strokeWidth = strokeWidth

        paint.color = Color.BLUE

        paint.color = Color.RED
        paint.isDither = true // set the dither to true

        paint.style = Paint.Style.STROKE // set to STOKE

        paint.strokeJoin = Paint.Join.ROUND // set the join to round you want

        paint.strokeCap = Paint.Cap.ROUND // set the paint cap to round too

        //paint.setPathEffect(CornerPathEffect(50F)) // set the path effect when they join.

        paint.isAntiAlias = true

        val radiusArc=(radius-strokeWidth/2)

        val oval = RectF(mX - radiusArc, mY - radiusArc, mX + radiusArc, mY +radiusArc)
        canvas.drawArc(oval, -180F, sweepAngle*90/100, false, paint)

        //canvas.drawArc(oval, -90F, 89F, false, paint)

    }

}