package de.peacemoon.androidcourse.widgetexplorer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textViewButton = findViewById<Button>(R.id.id_textview_button)
        textViewButton.setOnClickListener {
            val intent = Intent(this, TextViewActivity::class.java)
            startActivity(intent)
        }

        val buttonButton = findViewById<Button>(R.id.id_button_button)
        buttonButton.setOnClickListener {
            val intent = Intent(this, ButtonActivity::class.java)
            startActivity(intent)
        }

        val switchButton = findViewById<Button>(R.id.id_switch_button)
        switchButton.setOnClickListener {
            val intent = Intent(this, SwitchActivity::class.java)
            startActivity(intent)
        }

        val checkboxButton = findViewById<Button>(R.id.id_checkbox_button)
        checkboxButton.setOnClickListener {
            val intent = Intent(this, CheckboxActivity::class.java)
            startActivity(intent)
        }

        val radioButton = findViewById<Button>(R.id.id_radiobutton_button)
        radioButton.setOnClickListener {
            val intent = Intent(this, RadioButtonActivity::class.java)
            startActivity(intent)
        }

    }
}
