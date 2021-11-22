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
import android.view.LayoutInflater
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.radial_bar_chart.view.*
import ru.mironov.radialbarchart.databinding.ActivityMainBinding
import android.util.DisplayMetrics

import android.content.res.TypedArray
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin


class RadialBarLayout(
    context: Context,
    attributesSet: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int
) : ConstraintLayout(context, attributesSet, defStyleAttr, defStyleRes) {

    //private lateinit var binding: CustomViewBinding
    val dpi:Float

    var radius=100
    val mX=radius*1F
    val mY=radius*1F

    var sweepAngle=0F

    var strokeWidth=15F

    var maxValue=180F

    val angleStart=30F

    constructor(context: Context, attributesSet: AttributeSet?, defStyleAttr: Int) : this(context, attributesSet, defStyleAttr, 0){}

    constructor(context: Context, attributesSet: AttributeSet?) : this(context, attributesSet, 0){}

    constructor(context: Context) : this(context, null)

    lateinit var barView:RadialBar

    lateinit var maxValueText:TextView

    lateinit var currentValueText:TextView

    init{
        //binding = CustomViewBinding.inflate(layoutInflater).also { setContentView(it.root) }
        val inflater=LayoutInflater.from(context)
        inflater.inflate(R.layout.radial_bar_chart,this,true)


        val a = context.obtainStyledAttributes(attributesSet, R.styleable.RadialBarLayout, defStyleAttr, 0)

        val str = a.getString(R.styleable.RadialBarLayout_android_radius)

        radius= str?.filter { it.isDigit()|| it == '.' }?.toFloatOrNull()?.toInt() ?:radius

        a.recycle()

        barView=findViewById(R.id.radialBar)
        maxValueText=findViewById(R.id.maxValueText)
        currentValueText=findViewById(R.id.currentValueText)

        dpi = this.resources.displayMetrics.density
        radius *= dpi.toInt()
        barView.radius=radius

        val layoutParams = maxValueText.layoutParams
        val angle=Math.PI/180*barView.angleStart
        layoutParams.width = (radius*sin(angle)*2).roundToInt()
        maxValueText.textSize= layoutParams.width/maxValueText.text.length.toFloat()/2
        layoutParams.height = (maxValueText.textSize*1.2).toInt()
        maxValueText.layoutParams = layoutParams
    }



    fun setValue(value:Float){
        barView.setValue(value)
        currentValueText.text= "%.0f".format((maxValue*value/100))
    }

    fun setMaxVal(maxValue:Float){
        barView.setMaxValue(maxValue)
        this.maxValue=maxValue
        maxValueText.text ="из " +"%.0f".format(maxValue)
    }

    //fun pdToPixel(){val px: Float = dp * (metrics.densityDpi / 160f)}
}