package com.cngal.app.extension

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter


fun String.getImageSource(): String
{
    return this.split("?").last()
}


fun String.toDate(): Instant
{
    return Instant.parse(this)
}