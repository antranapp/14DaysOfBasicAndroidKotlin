package app.antran.androidcourse.basic.photogallery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ImageListAdapter(
    private val fragment: ImageListFragmentInterface,
    private val imageList: List<Image>): RecyclerView.Adapter<ImageListAdapter.ListItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_image_item, parent, false)
        return ListItemHolder(itemView)
    }

    override fun getItemCount(): Int = imageList.size

    override fun onBindViewHolder(holder: ListItemHolder, position: Int) {
        val image = imageList[position]

        image.previewURL?.let {
            Picasso
                .get()
                .load(it)
                .fit()
                .centerInside()
                .into(holder.imageView)
        }
    }

    inner class ListItemHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener, View.OnLongClickListener {

        internal var imageView = view.findViewById<ImageView>(R.id.imageView)

        init {
            view.isClickable = true
            view.setOnClickListener(this)
            view.setOnLongClickListener(this)
        }

        override fun onClick(view: View) {
            fragment.onItemClicked(adapterPosition)
        }

        override fun onLongClick(v: View?): Boolean {
            fragment.onItemLongClicked(adapterPosition)

            // Return true here as we are going to handle the long press event.
            // This will prevent the onClick method above to be called since
            return true
        }

    }
}