package de.peacemoon.androidcourse.database

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ItemAdapter(private val context: Context,
                  private val dataSource: ArrayList<Item>) : BaseAdapter() {

    private val inflater: LayoutInflater  = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val itemLayout =  inflater.inflate(R.layout.list_item, parent, false)
        val textViewDescription = itemLayout.findViewById(R.id.textViewDescription) as TextView

        val item = getItem(position) as Item
        textViewDescription.text = "${item.name} (${item.age})"

        return itemLayout
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return dataSource.size
    }
}