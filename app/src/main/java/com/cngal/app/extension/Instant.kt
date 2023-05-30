package com.cngal.app.extension

import java.time.Instant
import java.time.ZoneId

import java.time.format.DateTimeFormatter




fun Instant.toTimeFromNowString(): String
{
    val mil = Instant.now().toEpochMilli() - this.toEpochMilli()

    if (mil * 0.001 / 60 / 60 / 24 > 365)
    {
        return "${mil / 1000 / 60 / 60 / 24 / 365}年前"
    }
    else if (mil * 0.001 / 60 / 60 / 24 > 30)
    {
        return "${mil / 1000 / 60 / 60 / 24 / 30}个月前"
    }
    else if (mil * 0.001 / 60 / 60 > 24)
    {
        return "${mil / 1000 / 60 / 60 / 24}天前"
    }
    else if (mil * 0.001 / 60 > 60)
    {
        return "${mil / 1000 / 60 / 60}小时前"
    }
    else if (mil * 0.001 > 60)
    {
        return "${mil / 1000 / 60}分钟前"
    }
    else
    {
        return "${mil / 1000}秒前"
    }
}

fun Instant.toString(format:String): String
{
    return DateTimeFormatter.ofPattern(format).withZone(ZoneId.systemDefault()).format(this)
}