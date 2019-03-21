package com.tracker.trackerv2.data.room.entities

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException

/*
 *   Created by Android Developer : Birsasteanu Bogdan Andrei Alexandru on Date: 12/04/2018
 */

fun Any.toJson(): String = Gson().toJson(this)

inline fun <reified T : Any> String.fromJson(): T? = try {
    Gson().fromJson(this, T::class.java)
} catch (e: JsonSyntaxException) {
    println("${this.javaClass.simpleName} - Error - ${e.localizedMessage}")
    null
}