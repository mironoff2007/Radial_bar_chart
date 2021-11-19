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
import ru.mironov.radialbarchart.databinding.ActivityMainBinding


class RadialBar(
    context: Context,
    attributesSet: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int
) : View(context, attributesSet, defStyleAttr, defStyleRes) {

    //private lateinit var binding: CustomViewBinding

    var radius=100
    val mX=radius*1F
    val mY=radius*1F

    var sweepAngle=45F

    var strokeWidth=15F

    val maxValue=180F

    val angleStart=30F

    constructor(context: Context, attributesSet: AttributeSet?, defStyleAttr: Int) : this(context, attributesSet, defStyleAttr, 0)

    constructor(context: Context, attributesSet: AttributeSet?) : this(context, attributesSet, 0)

    constructor(context: Context) : this(context, null)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(radius*2,radius*2)
    }

    init{
        //binding = CustomViewBinding.inflate(layoutInflater).also { setContentView(it.root) }
    }

    fun setAngle(sweepAngle:Float){
        this.sweepAngle=sweepAngle
        this.invalidate()
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        val paint= Paint()
        paint.color = Color.parseColor("#FFA500")

        //canvas.drawCircle(radius.toFloat(),radius.toFloat(),radius.toFloat(),paint)

        paint.style = Paint.Style.STROKE
        paint.strokeWidth = strokeWidth

        paint.isDither = true // set the dither to true

        paint.style = Paint.Style.STROKE // set to STOKE

        paint.strokeJoin = Paint.Join.ROUND // set the join to round you want

        paint.strokeCap = Paint.Cap.ROUND// set the paint cap to round too

        //paint.setPathEffect(CornerPathEffect(50F)) // set the path effect when they join.

        paint.isAntiAlias = true

        val radiusArc=(radius-strokeWidth/2)

        val oval = RectF(mX - radiusArc, mY - radiusArc, mX + radiusArc, mY +radiusArc)
        val angleArc=sweepAngle*(360-2*angleStart)/100
        val angleEnd=360-angleStart-(angleStart+angleArc)

        canvas.drawArc(oval, -270F+angleStart, angleArc, false, paint)

        paint.color = Color.parseColor("#FFA500")
        paint.alpha=10

        canvas.drawArc(oval,-270F+angleStart+angleArc, angleEnd, false, paint)

        //canvas.drawArc(oval, -90F, 89F, false, paint)

    }

}