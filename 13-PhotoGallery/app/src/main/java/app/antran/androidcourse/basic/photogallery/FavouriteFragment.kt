package app.antran.androidcourse.basic.photogallery

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.GsonBuilder

class FavouriteFragment : Fragment(), ImageListFragmentInterface {

    private val NUMBER_OF_COLUMN = 3

    private val imageList: MutableList<Image> = ArrayList()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ImageListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_favourite, container, false)

        recyclerView = root.findViewById(R.id.recyclerView)

        setupListView()

        loadImageList()

        return root
    }

    override fun onItemClicked(position: Int) {
        showImage(position)
    }

    override fun onItemLongClicked(position: Int) {
        // Do nothing
    }

    private fun showImage(position: Int) {
        val image = imageList[position]
        val intent = Intent(this.context, DetailActivity::class.java).apply {
            val bundle = Bundle()
            bundle.putSerializable("image", image)
            putExtras(bundle)
        }

        startActivity(intent)
    }

    private fun setupListView() {
        adapter = ImageListAdapter(this, imageList)
        val layoutManager = GridLayoutManager(this.context, NUMBER_OF_COLUMN)
        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = adapter
    }

    private fun loadImageList() {
        this.activity?.getSharedPreferences("favourites", Context.MODE_PRIVATE)?.let { sharedPreferences ->
            val gson = GsonBuilder().create()
            sharedPreferences.getString("images", null)?.let { jsonString ->
                val currentImageList = gson.fromJson(jsonString, Array<Image>::class.java)
                imageList.addAll(currentImageList)
                adapter.notifyDataSetChanged()
            }

        }

    }
}
