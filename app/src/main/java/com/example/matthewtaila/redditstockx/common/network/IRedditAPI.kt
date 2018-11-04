package com.example.matthewtaila.redditstockx.common.network

import com.example.matthewtaila.redditstockx.feed.model.PostFeed
import com.example.matthewtaila.redditstockx.subreddits.model.SubredditSearchResult
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IRedditAPI {

    @GET("/{ordering}/.json")
    fun getFeed(@Path("ordering") order: String): Single<PostFeed>

    @GET("/{subreddit}/{ordering}/.json")
    fun getSubFeed(@Path("subreddit") subreddit: String, @Path("ordering") order: String): Single<PostFeed>

    @GET("/subreddits/search/.json")
    fun searchSubReddits(@Query("q") input: String): Single<SubredditSearchResult>

}