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
        val jsonObject = json?.asJsonObject?.getAsJsonObject("data")?.getAsJsonArray("children")

        var postDataList: ArrayList<Post> = arrayListOf()

        jsonObject?.forEach {
            val postJson = it.asJsonObject.getAsJsonObject("data")
            var thumbnailControl: Int? = null
            var thumbnail: String? = null
            try {
                thumbnailControl = postJson.get("thumbnail_width")?.asInt
                thumbnail = postJson.get("thumbnail")?.asString
            } catch (e: Exception) {
                Log.e("PostDeserializer", e.localizedMessage, e)
            }
            val post = Post(
                subreddit = postJson.get("subreddit")?.asString,
                url = postJson.get("url")?.asString,
                title = postJson.get("title").asString,
                score = postJson.get("score").asInt,
                thumbnail = thumbnail,
                thumbnailControl = thumbnailControl
            )
            postDataList.add(post)
        }
        val posting = PostFeed()
        posting.postDataList = postDataList
        return posting
    }
}