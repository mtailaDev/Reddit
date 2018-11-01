package com.example.matthewtaila.redditstockx.common.network

import com.example.matthewtaila.redditstockx.feed.model.PostFeed
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface IRedditAPI {

    @GET("/{ordering}/.json")
    @Headers("Content-Type: application/json")
    fun getFeed(@Path("ordering") order: String): Single<PostFeed>

}