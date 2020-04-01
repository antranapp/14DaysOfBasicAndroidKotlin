package de.peacemoon.androidcourse.networking

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.io.IOException

class RSSItemListAdapter(
    private val context : MainActivity,
    private val mListener: OnListInteractionListener?,
    private val mValues: List<RSSItem>
) : RecyclerView.Adapter<RSSItemListAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as RSSItem
            mListener?.onListInteraction(item)
            Log.i("INFO", "${item.title} selected")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rss_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.title?.text = item.title
        holder.pubDate?.text = item.pubDate
        if (item.image.isNotEmpty()) {
            Picasso
                .get()
                .load(item.image)
                .fit()
                .centerInside()
                .into(holder.featuredImage)
        }
        holder.description?.text  =item.description

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val title: TextView? = mView.findViewById(R.id.txtTitle)
        val pubDate: TextView? = mView.findViewById(R.id.txtPubdate)
        val description: TextView? = mView.findViewById(R.id.txtDescription)
        val featuredImage: ImageView = mView.findViewById(R.id.featuredImage);
    }

}