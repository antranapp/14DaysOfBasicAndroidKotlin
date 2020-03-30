package de.peacemoon.androidcourse.widgetexplorer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Switch
import android.widget.Toast

class SwitchActivity : AppCompatActivity() {

    private lateinit var switch: Switch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_switch)

        title = "Switch"

        switch = findViewById(R.id.id_switch_switch)
    }

    fun onButtonClicked(v: View) {
        val text = if (switch.isChecked) "On" else "Off"
        Toast.makeText(this, "State = $text", Toast.LENGTH_SHORT).show()
    }
}
