package com.example.matthewtaila.redditstockx.feed.model

import android.view.View

data class UIPost(
    val subreddit: String,
    val thumbnail: String?,
    var score: Int,
    val title: String
) {
    fun scoreAsString(): String = "$score"

    fun thumbnailVisiblity(): Int {
        return thumbnail?.let { View.VISIBLE } ?: View.GONE
    }
}