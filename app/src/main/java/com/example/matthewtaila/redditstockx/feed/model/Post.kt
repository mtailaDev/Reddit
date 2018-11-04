package com.example.matthewtaila.redditstockx.feed.model

import com.example.matthewtaila.redditstockx.common.network.PostFeedDeserializer
import com.google.gson.annotations.JsonAdapter

@JsonAdapter(PostFeedDeserializer::class)
data class Post(
    var subreddit: String? = "",
    var url: String? = "",
    var title: String = "",
    var score: Int = 0,
    var thumbnail: String? = "",
    var thumbnailControl: Int? = 0
) {

    fun map(): UIPost {
        return UIPost(subreddit = "r/$subreddit", thumbnail = thumbnail, score = score, title = title)
    }

}