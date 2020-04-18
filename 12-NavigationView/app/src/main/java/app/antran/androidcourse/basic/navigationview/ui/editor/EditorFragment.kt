package app.antran.androidcourse.basic.navigationview.ui.editor

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import app.antran.androidcourse.basic.navigationview.R

class EditorFragment : Fragment() {

    private val prefs: SharedPreferences? by lazy {
        this.activity?.getSharedPreferences("app", Context.MODE_PRIVATE)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_editor, container, false)
        val txtNote = root.findViewById<TextView>(R.id.txtNote)
        val btnAdd = root.findViewById<Button>(R.id.btnAdd)

        btnAdd.setOnClickListener {
            val editor = prefs?.edit()
            editor?.putString("note", txtNote.text.toString())
            editor?.apply()
            Toast.makeText(this.activity, "Note saved successfully", Toast.LENGTH_SHORT).show()
        }

        return root
    }
}
