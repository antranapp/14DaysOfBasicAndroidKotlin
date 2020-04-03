package de.peacemoon.androidcourse.fragmentnavigation

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class LogoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logout)

        val logoutButton = findViewById<Button>(R.id.logoutButton)
        logoutButton.setOnClickListener {
            val prefs = getSharedPreferences("settings", Context.MODE_PRIVATE)
            val editor = prefs.edit()
            editor.putBoolean("isLoggedIn", false)
            editor.apply()

            finish()
        }
    }
}
