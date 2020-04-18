package app.antran.androidcourse.basic.photogallery

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.GsonBuilder

class GalleryFragment : Fragment(), ImageListFragmentInterface {

    private val URL = "https://pixabay.com/api/?key=107764-f19c20d5ca4d545d9b0a09de3&page=%d"
    private val NUMBER_OF_COLUMN = 3

    private val imageList: MutableList<Image> = ArrayList()
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ImageListAdapter

    var page = 1
    var isLastPage = false
    var isLoading = false

    companion object {
        private const val TAG = "GalleryFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_gallery, container, false)

        progressBar = root.findViewById(R.id.progressBar)
        recyclerView = root.findViewById(R.id.recyclerView)

        setupListView()

        loadImageList()

        return root
    }

    override fun onItemClicked(position: Int) {
        showImage(position)
    }

    override fun onItemLongClicked(position: Int) {
        Log.i(TAG, "position =  $position")
        val image = imageList[position]

        this.activity?.getSharedPreferences("favourites", Context.MODE_PRIVATE)?.let {
            val gson = GsonBuilder().create()
            var favouriteList: MutableList<Image> = ArrayList()

            try {
                // Get the current saved favourite list
                it.getString("images", null)?.let {
                    val currentImageList = gson.fromJson(it, Array<Image>::class.java)
                    favouriteList.addAll(currentImageList)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            // Add the new favorited image
            favouriteList.add(image)

            // Save the new list back to SharedPreferences
            val editor = it.edit()
            try {
                val jsonString = gson.toJson(favouriteList)
                editor.putString("images", jsonString)
            } finally {
                editor.apply()
                Toast.makeText(this.activity, "Image is added into your favourite list", Toast.LENGTH_SHORT).show()
            }
        }

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

        recyclerView.addOnScrollListener(object : PaginationScrollListener(layoutManager) {
            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

            override fun loadMoreItems() {
                page++
                loadImageList()
            }
        })

    }

    private fun loadImageList() {
        progressBar.visibility = View.VISIBLE
        val stringRequest = StringRequest(URL.format(page),
            Response.Listener { response ->
                val gsonBuilder = GsonBuilder()
                val gson = gsonBuilder.create()
                val currentImageList = gson.fromJson(response, ImageList::class.java)
                imageList.addAll(currentImageList.hits)
                adapter.notifyDataSetChanged()
                progressBar.visibility = View.GONE
            }, Response.ErrorListener {
                progressBar.visibility = View.GONE
                Log.e("ERROR", it.toString())
            })

        val requestQueue = Volley.newRequestQueue(this.context)
        requestQueue.add(stringRequest)
    }
}
