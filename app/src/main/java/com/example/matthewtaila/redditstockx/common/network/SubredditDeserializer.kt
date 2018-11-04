package com.example.matthewtaila.redditstockx.common.network

import android.util.Log
import com.example.matthewtaila.redditstockx.subreddits.model.Subreddit
import com.example.matthewtaila.redditstockx.subreddits.model.SubredditSearchResult
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class SubredditDeserializer : JsonDeserializer<SubredditSearchResult> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): SubredditSearchResult {
        val jsonObject = json?.asJsonObject?.get("data")?.asJsonObject?.get("children")?.asJsonArray

        var subredditList: MutableList<Subreddit> = mutableListOf()

        jsonObject?.forEach {
            var thumbnailUrl: String? = null
            try {
                thumbnailUrl = it.asJsonObject.get("data")?.asJsonObject?.get("community_icon")?.asString
            } catch (e: Exception) {
                Log.e("subredditDeserializer", e.localizedMessage, e)
            }
            val sub = Subreddit(
                prefixed_name = it.asJsonObject?.get("data")?.asJsonObject?.get("display_name_prefixed")?.asString,
                numberOfSubscribers = it.asJsonObject?.get("data")?.asJsonObject?.get("subscribers")?.asInt,
                thumbnail = thumbnailUrl
            )
            subredditList.add(sub)
        }
        val subredditSearchResult = SubredditSearchResult()
        subredditSearchResult.dataList = subredditList
        return subredditSearchResult
    }
}