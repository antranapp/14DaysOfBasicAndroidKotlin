package de.peacemoon.androidcourse.widgetexplorer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast

class SpinnerActivity : AppCompatActivity() {

    private lateinit var spinner: Spinner

    private val items = arrayOf("Item 1", "Item 2", "Item 3")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spinner)

        title = "Spinner"

        spinner = findViewById(R.id.id_spinner_spinner)

        val adapter = ArrayAdapter(this, R.layout.spinner_item, items)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object  : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                Toast.makeText(this@SpinnerActivity, parent?.getItemAtPosition(p2).toString(), Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }

    fun onButtonClicked(v: View) {
        val text = spinner.selectedItem as String
        Toast.makeText(this, "Selected Item: $text", Toast.LENGTH_SHORT).show()
    }
}
