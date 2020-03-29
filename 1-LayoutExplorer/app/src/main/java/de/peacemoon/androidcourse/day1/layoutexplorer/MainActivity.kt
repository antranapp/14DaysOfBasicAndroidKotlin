package de.peacemoon.androidcourse.day1.layoutexplorer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val linearLayoutVerticalButton = findViewById<Button>(R.id.id_linearlayout_vertical_button)
        linearLayoutVerticalButton.setOnClickListener {
            val intent = Intent(this, LinearLayoutVerticalActivity::class.java)
            startActivity(intent)
        }

        val linearLayoutHorizontalButton = findViewById<Button>(R.id.id_linearlayout_horizontal_button)
        linearLayoutHorizontalButton.setOnClickListener {
            val intent = Intent(this, LinearLayoutHorizontalActivity::class.java)
            startActivity(intent)
        }

        val relativeLayoutButton = findViewById<Button>(R.id.id_relativelayout_button)
        relativeLayoutButton.setOnClickListener {
            val intent = Intent(this, RelativeLayoutActivity::class.java)
            startActivity(intent)
        }

        val constraintLayoutButton = findViewById<Button>(R.id.id_constraintlayout_button)
        constraintLayoutButton.setOnClickListener {
            val intent = Intent(this, ConstraintLayoutActivity::class.java)
            startActivity(intent)
        }

        val frameLayoutButton = findViewById<Button>(R.id.id_framelayout_button)
        frameLayoutButton.setOnClickListener {
            val intent = Intent(this, FrameLayoutActivity::class.java)
            startActivity(intent)
        }

        val tableLayoutButton = findViewById<Button>(R.id.id_tablelayout_button)
        tableLayoutButton.setOnClickListener {
            val intent = Intent(this, TableLayoutActivity::class.java)
            startActivity(intent)
        }
    }
}
