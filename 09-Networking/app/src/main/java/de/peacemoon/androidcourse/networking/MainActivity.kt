package de.peacemoon.androidcourse.networking

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.IOException
import java.io.InputStream
import java.lang.ref.WeakReference
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class MainActivity : AppCompatActivity(), OnListInteractionListener {

    private val RSS_FEED_LINK = "https://www.nasa.gov/rss/dyn/lg_image_of_the_day.rss";

    private lateinit var adapter: RSSItemListAdapter
    private var rssItems = ArrayList<RSSItem>()

    private lateinit var listView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupListView()
        loadFeed()
    }

    private fun setupListView() {
        listView = findViewById(R.id.listView)
        adapter = RSSItemListAdapter(this, this, rssItems)
        listView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        listView.adapter = adapter
    }

    private fun updateListView(newRSSItems: List<RSSItem>) {
        if (newRSSItems.isNotEmpty()) {
            rssItems.addAll(newRSSItems)
            adapter.notifyDataSetChanged()
        }
    }

    private fun loadFeed() {
        val url = URL(RSS_FEED_LINK)
        RSSFetcher(this).execute(url)
    }

    class RSSFetcher(private val context: MainActivity) : AsyncTask<URL, Void, List<RSSItem>>() {

        private val reference = WeakReference(context)
        private var stream: InputStream? = null;

        override fun doInBackground(vararg params: URL?): List<RSSItem>? {
            val connect = params[0]?.openConnection() as HttpsURLConnection
            connect.readTimeout = 8000
            connect.connectTimeout = 8000
            connect.requestMethod = "GET"
            connect.connect()

            val responseCode: Int = connect.responseCode
            var rssItems: List<RSSItem>? = null
            if (responseCode == 200) {
                stream = connect.inputStream
                try {
                    val parser = RSSParser()
                    rssItems = parser.parse(stream!!)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }

            return rssItems
        }

        override fun onPostExecute(result: List<RSSItem>?) {
            super.onPostExecute(result)
            if (result != null && result.isNotEmpty()) {
                reference.get()?.updateListView(result)
            }
        }
    }

    override fun onListInteraction(item: RSSItem) {
        val intent = Intent(this, DetailActivity::class.java).apply {
            val bundle = Bundle()
            bundle.putSerializable("item", item)
            putExtras(bundle)
        }

        startActivity(intent)
    }
}

interface OnListInteractionListener {
    fun onListInteraction(item: RSSItem)
}
