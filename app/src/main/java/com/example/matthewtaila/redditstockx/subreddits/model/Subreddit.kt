package com.example.matthewtaila.redditstockx.subreddits.model

import com.example.matthewtaila.redditstockx.common.network.SubredditDeserializer
import com.google.gson.annotations.JsonAdapter

@JsonAdapter(SubredditDeserializer::class)
data class Subreddit (val prefixed_name: String,
                      val thumbnail: String?,
                      val numberOfSubscribers: Int) {

    fun map() : UISubreddit {
        return UISubreddit(prefixed_name, thumbnail, numberOfSubscribers)
    }
}