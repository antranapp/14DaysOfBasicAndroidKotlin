package de.peacemoon.androidcourse.day1.layoutexplorer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class TableLayoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table_layout)

        setTitle("TableLayout")
    }
}
