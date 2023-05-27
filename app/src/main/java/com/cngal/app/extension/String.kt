package com.cngal.app.extension

import java.time.Instant


fun String.getImageSource(): String
{
    return this.split("?").last()
}


fun String.toDate(): Instant
{
    return Instant.parse(this)
}