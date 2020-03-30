package de.peacemoon.androidcourse.day0.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var nameTextView: TextView
    private lateinit var textLabel: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameTextView = findViewById(R.id.nameTextView)
        textLabel = findViewById(R.id.textLabel)

        Toast.makeText(this, "App started", Toast.LENGTH_LONG).show()
    }

    fun sayHelloClick(v: View) {
        val helloText = "Hello " + nameTextView.text
        Log.i("INFO", helloText)
        textLabel.text = helloText

    }
}
