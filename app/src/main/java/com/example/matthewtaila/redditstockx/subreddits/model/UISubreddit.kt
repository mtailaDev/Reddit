package com.example.matthewtaila.redditstockx.subreddits.model

data class UISubreddit(val name: String,
                       val thumbnail: String?,
                       var subscribers: Int) {

    fun subscribersAsString() = "$subscribers"

}