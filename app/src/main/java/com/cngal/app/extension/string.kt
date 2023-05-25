package com.cngal.app.extension

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun String.getImageSource(): String
{
    return this.split("?").last()
}

fun String.toDate(): Date?
{
    val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z", Locale.getDefault())
    return dateFormat.parse(this)
}