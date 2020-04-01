package de.peacemoon.androidcourse.networking

import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException
import java.io.InputStream

class RSSParser {
    private val rssItems = ArrayList<RSSItem>()
    private var rssItem: RSSItem? = null
    private var text: String? = null

    fun parse(inputStream: InputStream): List<RSSItem> {
        try {
            val factory = XmlPullParserFactory.newInstance()
            factory.isNamespaceAware = true
            val parser = factory.newPullParser()
            parser.setInput(inputStream, null)
            var eventType = parser.eventType
            var foundItem = false
            while (eventType != XmlPullParser.END_DOCUMENT) {
                val tagName = parser.name
                when (eventType) {
                    XmlPullParser.START_TAG -> if (tagName.equals("item", ignoreCase = true)) {
                        foundItem = true
                        rssItem = RSSItem()
                    }
                    XmlPullParser.TEXT -> text = parser.text
                    XmlPullParser.END_TAG -> if (tagName.equals("item", ignoreCase = true)) {
                        rssItem?.let { rssItems.add(it) }
                        foundItem = false
                    } else if (foundItem && tagName.equals("title", ignoreCase = true)) {
                        rssItem!!.title = text.toString()
                    } else if (foundItem && tagName.equals("link", ignoreCase = true)) {
                        rssItem!!.link = text.toString()
                    } else if (foundItem && tagName.equals("pubDate", ignoreCase = true)) {
                        rssItem!!.pubDate = text.toString()
                    } else if (foundItem && tagName.equals("description", ignoreCase = true)) {
                        rssItem!!.description = text.toString()
                    } else if (foundItem && tagName.equals("enclosure", ignoreCase = true)) {
                        rssItem!!.image = parser.getAttributeValue(null, "url")
                    }
                }
                eventType = parser.next()
            }

        } catch (e: XmlPullParserException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return rssItems
    }
}