package de.peacemoon.androidcourse.fragmentnavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

class LoginFragment : Fragment() {

    private var callback: OnLoggingInListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var loginButton = view.findViewById<Button>(R.id.loginButton)
        var userNameEditText = view.findViewById<EditText>(R.id.userNameEditText)

        loginButton.setOnClickListener {
            callback?.onLoggingIn(userNameEditText?.text.toString())
        }
    }

    fun setOnLoggingInListener(callback: OnLoggingInListener) {
        this.callback = callback
    }

    companion object {
        @JvmStatic
        fun newInstance() = LoginFragment()
    }

    interface OnLoggingInListener {
        fun onLoggingIn(username: String?)
    }
}
