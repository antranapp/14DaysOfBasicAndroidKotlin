package de.peacemoon.androidcourse.networking

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.io.IOException

class Utils{
    companion object {
        final fun getFeaturedImageLink(htmlText: String?): String? {
            var result: String? = null

            if (htmlText == null) {
                return null
            }

            try {
                val doc: Document = Jsoup.parse(htmlText)
                val images: Elements = doc.select("img")
                for (image in images) {
                    var src = image.attr("src")
                    result = src
                }
            } catch (e: IOException) {}

            return result
        }
    }
}