package de.peacemoon.androidcourse.networking

import java.io.Serializable

data class RSSItem(var title: String = "",
                   var link: String = "",
                   var description: String = "",
                   var pubDate: String = "",
                   var image: String = ""): Serializable