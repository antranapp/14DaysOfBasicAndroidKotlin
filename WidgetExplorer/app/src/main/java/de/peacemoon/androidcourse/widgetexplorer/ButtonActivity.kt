package de.peacemoon.androidcourse.widgetexplorer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class ButtonActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_button)

        title = "Button"
    }

    fun onDefaultButtonClicked(v: View) {
        Toast.makeText(this, "Default Button clicked", Toast.LENGTH_SHORT).show()
    }

    fun onLongClickableButtonClicked(v: View) {
        Toast.makeText(this, "Long-clickable Button clicked", Toast.LENGTH_LONG).show()
    }

}
