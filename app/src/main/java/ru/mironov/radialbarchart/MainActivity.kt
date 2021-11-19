package ru.mironov.radialbarchart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.SeekBar
import ru.mironov.radialbarchart.databinding.ActivityMainBinding

import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.core.widget.doOnTextChanged
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var radialBarLayout: RadialBarLayout

    private lateinit var editText: EditText

    private lateinit var bar: SeekBar

    var maxVal = 0F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        radialBarLayout = binding.customView
        bar = binding.seekBar
        editText = binding.editText

        seekBar.progress = 0
        radialBarLayout.setValue(seekBar.progress * 1F)

        maxVal = editText.text.toString().toFloat()
        radialBarLayout.setMaxVal(maxVal)

        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                maxVal = if (p0?.length != 0) {
                    editText.text.toString().toFloat()
                } else {
                    0F
                }
                radialBarLayout.setMaxVal(maxVal)
                radialBarLayout.setValue(seekBar.progress * 1F)
            }

        })

        bar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // TODO Auto-generated method stub
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // TODO Auto-generated method stub
            }

            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                radialBarLayout.setValue(progress * 1F)
            }
        })
    }
}

