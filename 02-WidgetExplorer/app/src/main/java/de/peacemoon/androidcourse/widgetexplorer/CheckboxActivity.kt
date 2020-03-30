package de.peacemoon.androidcourse.widgetexplorer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.Toast

class CheckboxActivity : AppCompatActivity() {

    private lateinit var checkbox: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkbox)

        title = "Checkbox"

        checkbox = findViewById(R.id.id_checkbox_checkbox)
    }

    fun onButtonClicked(v: View) {
        val text = if (checkbox.isChecked) "On" else "Off"

        Toast.makeText(this, "State = $text", Toast.LENGTH_SHORT).show()
    }
}
