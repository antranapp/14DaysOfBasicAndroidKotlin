package de.peacemoon.androidcourse.fragmentnavigation

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

class MainActivity : AppCompatActivity(), LoginFragment.OnLoggingInListener {

    private lateinit var prefs: SharedPreferences

    private companion object {
        val SETTING_KEY = "settings"
        val IS_LOGGED_IN_KEY = "isLoggedIn"
        val USERNAME_KEY = "username"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prefs = getSharedPreferences(MainActivity.SETTING_KEY, Context.MODE_PRIVATE)

        showMainFragment()
    }

    override fun onAttachFragment(fragment: Fragment) {
        super.onAttachFragment(fragment)

        if (fragment is LoginFragment) {
            fragment.setOnLoggingInListener(this)
        }
    }

    override fun onResume() {
        super.onResume()

        showMainFragment()
    }

    private var isLoggedIn: Boolean
        get() = prefs.getBoolean(MainActivity.IS_LOGGED_IN_KEY, false)
        set(value) {
            val editor = prefs.edit()
            editor.putBoolean(MainActivity.IS_LOGGED_IN_KEY, value)
            editor.apply()
        }

    var username: String?
        get() = prefs.getString(MainActivity.USERNAME_KEY, null)
        set(value) {
            val editor = prefs.edit()
            editor.putString(MainActivity.USERNAME_KEY, username)
            editor.apply()
        }

    override fun onLoggingIn(username: String?) {
        this.isLoggedIn = true
        this.username = username

        val fragmentTransaction = supportFragmentManager.beginTransaction().apply {
            val profileFragment = ProfileFragment.newInstance(username)
            replace(R.id.fragmentContainer, profileFragment)
        }

        fragmentTransaction.commit()
    }

    private fun showMainFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction().apply {
            val mainFragment = if (!isLoggedIn) LoginFragment.newInstance() else ProfileFragment.newInstance(username)
            replace(R.id.fragmentContainer, mainFragment)
        }

        fragmentTransaction.commit()
    }
}