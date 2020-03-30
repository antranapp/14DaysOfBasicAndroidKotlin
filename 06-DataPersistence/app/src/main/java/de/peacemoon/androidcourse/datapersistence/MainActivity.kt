package de.peacemoon.androidcourse.datapersistence

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    enum class Keys(var rawValue: String) {
        SETTING("settings"),
        EDITTEXT("editText"),
        SWITCH("switch"),
        SEEKBAR("seekBar")
    }

    private lateinit var editText: EditText
    private lateinit var switch: Switch
    private lateinit var seekBar: SeekBar
    private lateinit var seekBarLabel: TextView
    private var seekBarValue = 0

    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prefs = getSharedPreferences(Keys.SETTING.rawValue, Context.MODE_PRIVATE)

        editText = findViewById(R.id.prefEditText)
        switch = findViewById(R.id.prefSwitch)
        seekBar = findViewById(R.id.seekBar)
        seekBarLabel = findViewById(R.id.seekBarLabel)

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                updateSeekBar(i)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}

            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        loadPrefs()
    }

    fun onSaveButtonClicked(v: View) {
        savePrefs()
        Toast.makeText(this, "Saved successfully", Toast.LENGTH_SHORT).show()
    }

    fun onLoadButtonClicked(v: View) {
        loadPrefs()
        Toast.makeText(this, "Loaded successfully", Toast.LENGTH_SHORT).show()
    }

    private fun savePrefs() {
        val editor = prefs.edit()
        editor.putString(Keys.EDITTEXT.rawValue, editText.text.toString())
        editor.putBoolean(Keys.SWITCH.rawValue, switch.isChecked)
        editor.putInt(Keys.SEEKBAR.rawValue, seekBarValue)
        editor.apply()
    }

    private fun loadPrefs() {
        editText.setText(prefs.getString(Keys.EDITTEXT.rawValue, null))
        switch.isChecked = prefs.getBoolean(Keys.SWITCH.rawValue, false)
        seekBarValue = prefs.getInt(Keys.SEEKBAR.rawValue, 0)
        seekBar.progress = seekBarValue
    }

    private fun updateSeekBar(value: Int) {
        seekBarValue = value
        seekBarLabel.text = "$value"
    }
}
