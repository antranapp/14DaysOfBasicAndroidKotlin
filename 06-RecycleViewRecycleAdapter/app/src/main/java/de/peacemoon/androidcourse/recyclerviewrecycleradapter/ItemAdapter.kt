package de.peacemoon.androidcourse.recyclerviewrecycleradapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(
    private val mainActivity: MainActivity,
    private val itemList: List<Item>): RecyclerView.Adapter<ItemAdapter.ListItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)

        return ListItemHolder(itemView)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ItemAdapter.ListItemHolder, position: Int) {
        val item = itemList[position]
        holder.title.text = item.title
        holder.description.text = item.description
    }

    inner class ListItemHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        internal var title = view.findViewById<TextView>(R.id.textViewTitle)
        internal var description = view.findViewById<TextView>(R.id.textViewDescription)

        init {
            view.isClickable = true
            view.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            mainActivity.showItem(adapterPosition)
        }
    }
}