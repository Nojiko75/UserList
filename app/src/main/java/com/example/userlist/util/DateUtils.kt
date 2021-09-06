package com.example.userlist.util

import java.text.SimpleDateFormat
import java.util.*

fun parseDate(dateString: String?) : String {
    if (dateString == null) return "no data"
    val date: Date? = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
        .parse(dateString?.replace("Z$".toRegex(), "+0000"))
    return SimpleDateFormat("MMM dd yyyy").format(date)
}