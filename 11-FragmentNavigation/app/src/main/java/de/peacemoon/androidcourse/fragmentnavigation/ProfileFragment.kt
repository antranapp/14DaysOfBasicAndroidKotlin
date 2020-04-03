package de.peacemoon.androidcourse.fragmentnavigation

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

private const val ARG_USERNAME = "username"

class ProfileFragment : Fragment() {

    private var username: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            username = it.getString(ARG_USERNAME)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val greetingEditText = view.findViewById<TextView>(R.id.greetingEditText)
        greetingEditText.text = "Hello $username"

        val logoutButton = view.findViewById<Button>(R.id.showLogoutButton)
        logoutButton.setOnClickListener {
            val intent = Intent(activity, LogoutActivity::class.java)
            startActivity(intent)
        }
    }

    companion object {
        fun newInstance(username: String?) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_USERNAME, username)
                }
            }
    }
}
