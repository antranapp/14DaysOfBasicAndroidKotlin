package de.peacemoon.androidcourse.day1.layoutexplorer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class LinearLayoutHorizontalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_linear_layout_horizontal)

        setTitle("LinearLayout (Horizontal)")
    }
}
