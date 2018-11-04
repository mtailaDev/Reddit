package com.example.matthewtaila.redditstockx.common.network

import android.util.Log
import com.example.matthewtaila.redditstockx.feed.model.Post
import com.example.matthewtaila.redditstockx.feed.model.PostFeed
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class PostFeedDeserializer : JsonDeserializer<PostFeed> {

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): PostFeed {
        val jsonObject = json?.asJsonObject?.get("data")?.asJsonObject?.get("children")?.asJsonArray

        var postDataList: MutableList<Post> = mutableListOf()

        jsonObject?.forEach {
            var thumbnailControl: Int? = null
            try {
                thumbnailControl = it.asJsonObject.get("data")?.asJsonObject?.get("thumbnail_width")?.asInt
            } catch (e: Exception) {
                Log.e("PostDeserializer", e.localizedMessage, e)
            }
            val post = Post(
                subreddit = it.asJsonObject?.get("data")?.asJsonObject?.get("subreddit")?.asString,
                url = it.asJsonObject?.get("data")?.asJsonObject?.get("url")?.asString,
                title = it.asJsonObject?.get("data")?.asJsonObject?.get("title")?.asString,
                score = it.asJsonObject?.get("data")?.asJsonObject?.get("score")?.asInt,
                thumbnail = it.asJsonObject?.get("data")?.asJsonObject?.get("thumbnail")?.asString,
                thumbnailControl = thumbnailControl
            )
            postDataList.add(post)
        }
        val posting = PostFeed()
        posting.postDataList = postDataList
        return posting
    }
}