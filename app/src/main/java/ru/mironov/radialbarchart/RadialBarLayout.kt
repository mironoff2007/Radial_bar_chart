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


class RadialBarLayout(
    context: Context,
    attributesSet: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int
) : ConstraintLayout(context, attributesSet, defStyleAttr, defStyleRes) {

    //private lateinit var binding: CustomViewBinding

    var radius=100
    val mX=radius*1F
    val mY=radius*1F

    var sweepAngle=0F

    var strokeWidth=15F

    var maxValue=180F

    val angleStart=30F

    constructor(context: Context, attributesSet: AttributeSet?, defStyleAttr: Int) : this(context, attributesSet, defStyleAttr, 0)

    constructor(context: Context, attributesSet: AttributeSet?) : this(context, attributesSet, 0)

    constructor(context: Context) : this(context, null)

    lateinit var barView:RadialBar

    lateinit var maxValueText:TextView

    lateinit var currentValueText:TextView

    init{
        //binding = CustomViewBinding.inflate(layoutInflater).also { setContentView(it.root) }
        val inflater=LayoutInflater.from(context)
        inflater.inflate(R.layout.radial_bar_chart,this,true)

        barView=findViewById(R.id.radialBar)
        maxValueText=findViewById(R.id.maxValueText)
        currentValueText=findViewById(R.id.currentValueText)

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

}