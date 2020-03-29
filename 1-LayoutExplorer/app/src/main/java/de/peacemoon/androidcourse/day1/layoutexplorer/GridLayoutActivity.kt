package de.peacemoon.androidcourse.day1.layoutexplorer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class GridLayoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid_layout)

        setTitle("GridLayout")
    }
}
