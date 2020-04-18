package app.antran.androidcourse.basic.navigationview.ui.display

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import app.antran.androidcourse.basic.navigationview.R

class DisplayFragment : Fragment() {

    private val prefs: SharedPreferences? by lazy {
        this.activity?.getSharedPreferences("app", Context.MODE_PRIVATE)
    }

    private lateinit var textView: TextView

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_display, container, false)
        textView = root.findViewById(R.id.text_gallery)
        return root
    }

    override fun onStart() {
        super.onStart()
        textView.text = prefs?.getString("note", "")
    }
}
