package de.peacemoon.androidcourse.networking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private lateinit var txtTitle: TextView
    private lateinit var webViewDescription: WebView
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        imageView = findViewById(R.id.featuredImage)
        txtTitle = findViewById(R.id.txtTitle)
        webViewDescription = findViewById(R.id.webView)
        loadItem()
    }

    private fun loadItem() {
        val bundle = this.intent.extras
        val item = bundle?.get("item") as? RSSItem

        txtTitle.text = item?.title
        webView.loadData(item?.description, "text/html", "UTF-8")
        val url = item?.image.let {
            Picasso
                .get()
                .load(it)
                .fit()
                .centerInside()
                .into(imageView)
        }

    }
}
