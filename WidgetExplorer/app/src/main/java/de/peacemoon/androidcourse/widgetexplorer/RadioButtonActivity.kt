package de.peacemoon.androidcourse.widgetexplorer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast

class RadioButtonActivity : AppCompatActivity() {

    private lateinit var radioGroup: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_radio_button)

        title = "RadioButton"

        radioGroup = findViewById(R.id.id_radiobutton_group)
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            Toast.makeText(this, "CheckID = $checkedId", Toast.LENGTH_SHORT).show()
        }
    }

    fun onButtonClicked(v: View) {
        val selectedID = radioGroup.checkedRadioButtonId
        if (selectedID != - 1) {
            val radioButton = findViewById<RadioButton>(selectedID)
            Toast.makeText(this, "Good ${radioButton.text}", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "No selection!", Toast.LENGTH_SHORT).show()
        }
    }
}
