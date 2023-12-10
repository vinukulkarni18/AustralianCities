package com.vinod.australiancitieslibrary.utils

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

const val ZERO = 0
const val DEFAULT_ONE = 1
const val DEFAULT_TWO = 2

const val EMPTY = ""
const val JSON_FILE_NAME = "au_cities.json"
const val MaterialIconDimension = 24f
const val DECIMAL_TWO = 0.2f
const val DECIMAL_ONE = 1f

inline fun <reified T> Gson.fromJson(json: String) =
    fromJson<T>(json, object : TypeToken<T>() {}.type)

/*
 *  This function will read any local json file from assets
 */
fun String.getJsonDataFromAsset(context: Context): String? {
    val jsonString: String
    try {
        jsonString = context.assets.open(this).bufferedReader().use { it.readText() }
    } catch (exp: IOException) {
        exp.printStackTrace()
        return null
    }
    return jsonString
}
