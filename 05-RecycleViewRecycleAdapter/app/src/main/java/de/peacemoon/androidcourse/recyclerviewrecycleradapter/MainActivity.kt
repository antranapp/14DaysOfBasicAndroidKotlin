package de.peacemoon.androidcourse.recyclerviewrecycleradapter

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val itemList = ArrayList<Item>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab.setOnClickListener { _ ->
            val index = itemList.size + 1
            val item = Item("Item$index", "Description for Item$index")
            addItem(item)
        }

        recyclerView = findViewById<View>(R.id.recyclerView) as RecyclerView

        adapter = ItemAdapter(this, itemList)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()

        // Add a neat dividing line between items in the list
        recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

        // set the adapter
        recyclerView.adapter = adapter

        addItem(Item("Item1", "Description for Item1"))
    }

    fun showItem(position: Int) {
        Toast.makeText(this, itemList[position].title, Toast.LENGTH_SHORT).show()
    }

    private fun addItem(item: Item) {
        itemList.add(item)
        adapter.notifyDataSetChanged()
        Toast.makeText(this, "${item.title} added.", Toast.LENGTH_SHORT).show()
    }
}
