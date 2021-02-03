package com.example.ibeer

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.*
import org.json.JSONArray

class DownloadManager {


    companion object {
        fun downloadApiResults() : List<Cerveza>{
            val client = OkHttpClient()
            val url = "https://api.punkapi.com/v2/beers"
            val request = Request.Builder()
                .url(url)
                .build()
            val call = client.newCall(request).execute()
            var bodyInString = call.body?.string()
            bodyInString?.let {

                val results = JSONArray(it)
                results?.let {
                    val gson = Gson()

                    val itemType = object : TypeToken<List<Cerveza>>() {}.type

                    val cervezas = gson.fromJson<List<Cerveza>>(it.toString(), itemType)
                    val numCervezas = gson.fromJson<List<Cerveza>>(it.toString(), itemType).size
                    return cervezas
                }
            }
            return listOf()
        }


        fun downloadApiSingleResult(userChoice: String) : List<Cerveza>? {
            val userDefault :String
            if (userChoice.isNullOrEmpty()){
                userDefault = "26"
            }else{
                userDefault = userChoice
            }
            val client = OkHttpClient()
            val url = "https://api.punkapi.com/v2/beers/$userDefault"
            val request = Request.Builder()
                .url(url)
                .build()

            val call = client.newCall(request).execute()
            var bodyInString = call.body?.string()
            bodyInString?.let {
                if (userDefault.toInt() <= 25) {
                    val results = JSONArray(it)
                    results?.let {
                        val gson = Gson()

                        val itemType = object : TypeToken<List<Cerveza>>() {}.type

                        return gson?.fromJson<List<Cerveza>>(it.toString(), itemType)
                    }
                }
            }
            return listOf(Cerveza())
        }
    }
}
