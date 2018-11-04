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

        val subredditList: ArrayList<Subreddit> = arrayListOf()

        jsonObject?.forEach {
            val subredditJson = it.asJsonObject.getAsJsonObject("data")
            var thumbnailUrl: String? = null
            try {
                thumbnailUrl = subredditJson.get("community_icon")?.asString
            } catch (e: Exception) {
                Log.e("subredditDeserializer", e.localizedMessage, e)
            }
            val sub = Subreddit(
                prefixed_name = subredditJson.get("display_name_prefixed").asString,
                numberOfSubscribers = subredditJson.get("subscribers").asInt,
                thumbnail = thumbnailUrl
            )
            subredditList.add(sub)
        }
        val subredditSearchResult = SubredditSearchResult()
        subredditSearchResult.dataList = subredditList
        return subredditSearchResult
    }
}