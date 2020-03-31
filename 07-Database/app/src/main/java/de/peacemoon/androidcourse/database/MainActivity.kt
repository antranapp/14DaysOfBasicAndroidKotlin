package de.peacemoon.androidcourse.database

import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import kotlin.Comparator

class MainActivity : AppCompatActivity() {

    enum class Comparator(val value: String) {
        BIGGER(">"),
        EQUAL("="),
        SMALLER("<");

        companion object {
            operator fun invoke(rawValue: String) = Comparator.values().find { it.value == rawValue }
        }
    }

    private lateinit var editTextName: EditText
    private lateinit var editTextAge: EditText
    private lateinit var searchEditTextAge: EditText
    private lateinit var searchSpinnerComparator: Spinner
    private lateinit var listView: ListView

    private val comparatorItems = arrayOf(">", "=", "<")

    private lateinit var databaseManager: DatabaseManager

    private var itemList = ArrayList<Item>()
    private lateinit var itemAdapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        databaseManager = DatabaseManager(this)

        editTextName = findViewById(R.id.editTextName)
        editTextAge = findViewById(R.id.editTextAge)
        searchSpinnerComparator = findViewById(R.id.searchSpinnerComparator)
        searchEditTextAge = findViewById(R.id.searchEditTextAge)
        listView = findViewById(R.id.listView)

        // Setup
        setupSearchSpinner()
        setupListView()

        loadData()
    }

    fun onAddButtonClicked(v: View) {
        val name = editTextName.text.toString()
        val age = editTextAge.text.toString().toInt()
        val item = Item(name, age)
        databaseManager.insert(item)

        itemList.add(item)
        itemAdapter.notifyDataSetChanged()

        resetEditTexts()

        Toast.makeText(this, "Inserted successfully!", Toast.LENGTH_SHORT).show()
    }

    fun onSearchButtonClicked(v: View) {
        val comparator = Comparator(searchSpinnerComparator.selectedItem as String)
        val age = searchEditTextAge.text.toString().toInt()
        loadData(comparator, age)
    }

    private fun setupSearchSpinner() {
        val spinnerAdapter = ArrayAdapter(this, R.layout.spinner_item, comparatorItems)
        searchSpinnerComparator.adapter = spinnerAdapter
    }

    private fun setupListView() {
        itemAdapter = ItemAdapter(this, itemList)
        listView.adapter = itemAdapter
    }

    private fun loadData(comparator: Comparator? = null, age: Int? = null) {
        var cursor: Cursor
        if (comparator == null || age == null) {
            cursor = databaseManager.selectAll()
        } else {
            cursor = databaseManager.searchAge(comparator!!.value, age!!)
        }

        itemList.clear()

        // Loop through the results in the Cursor
        while (cursor.moveToNext()) {
            itemList.add(Item(cursor.getString(1), cursor.getInt(2)))
        }

        itemAdapter.notifyDataSetInvalidated()
    }

    private fun resetEditTexts() {
        editTextAge.text = null
        editTextName.text = null
    }
}
