package de.peacemoon.androidcourse.applifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import de.peacemoon.androidcourse.applifecycle.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Toast.makeText(this, "onCreate called", Toast.LENGTH_SHORT).show()

        Log.i("info", "onCreate called")

    }

    override fun onStart() {
        super.onStart();

        Toast.makeText(this, "onStart called", Toast.LENGTH_SHORT).show()

        Log.i("info", "onStart called")
    }

    override fun onResume() {
        // First call the "official” version of this method
        super.onResume();

        Toast.makeText(this, "onResume called", Toast.LENGTH_SHORT).show();

        Log.i("info", "onResume called");
    }

    override fun onPause() {
        super.onPause();

        Toast.makeText(this, "onPause called", Toast.LENGTH_SHORT).show()

        Log.i("info", "onPause called")
    }

    override fun onStop() {
        super.onStop();

        Toast.makeText(this, "onStop called", Toast.LENGTH_SHORT).show()

        Log.i("info", "onStop called")
    }

    override fun onDestroy() {
        super.onDestroy();

        Toast.makeText(this, "onDestroy called", Toast.LENGTH_SHORT).show()

        Log.i("info", "onDestroy called")
    }
}
