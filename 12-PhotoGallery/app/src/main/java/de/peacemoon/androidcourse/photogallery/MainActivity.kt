package de.peacemoon.androidcourse.photogallery

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.WindowManager.*
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.*
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.GsonBuilder

class MainActivity : AppCompatActivity() {

    private val URL = "https://pixabay.com/api/?key=107764-f19c20d5ca4d545d9b0a09de3&page=%d"
    private val NUMBER_OF_COLUMN = 3

    private val imageList: MutableList<Image> = ArrayList<Image>()
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ImageListAdapter

    var page = 1
    var isLastPage = false
    var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.window.setFlags(LayoutParams.FLAG_FULLSCREEN, LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main)

        progressBar = findViewById(R.id.progressBar)

        setupListView()

        loadImageList()
    }

    fun showImage(position: Int) {
        val image = imageList[position]
        val intent = Intent(this, DetailActivity::class.java).apply {
            val bundle = Bundle()
            bundle.putSerializable("image", image)
            putExtras(bundle)
        }

        startActivity(intent)
    }

    private fun setupListView() {
        recyclerView = findViewById(R.id.recyclerView)

        adapter = ImageListAdapter(this, imageList)

        val layoutManager = GridLayoutManager(this@MainActivity, NUMBER_OF_COLUMN)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = ImageListAdapter(this@MainActivity, imageList.toList())

        recyclerView.itemAnimator = DefaultItemAnimator()

        // set the adapter
        recyclerView.adapter = adapter

        recyclerView?.addOnScrollListener(object : PaginationScrollListener(layoutManager) {
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
            Response.Listener<String?> { response ->
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

        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(stringRequest)
    }
}
