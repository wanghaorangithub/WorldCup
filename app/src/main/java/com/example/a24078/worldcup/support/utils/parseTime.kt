package com.example.a24078.worldcup.support.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.text.ParseException
import java.util.*


/**
 * Created by haoran on 2018/11/4.
 */
@SuppressLint("SimpleDateFormat")
fun parseTime(strTime: String): Long {
    val format = SimpleDateFormat("yyyyMMddHHmmss")
    format.timeZone = TimeZone.getTimeZone("GMT+8")
    var time1 = 0L
    try {
        time1 = format.parse(strTime).time
    } catch (e: ParseException) {
        e.printStackTrace()
    }

    return time1
}