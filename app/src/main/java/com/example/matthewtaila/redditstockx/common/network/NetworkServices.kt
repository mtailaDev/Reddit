package com.example.matthewtaila.redditstockx.common.network

import com.example.matthewtaila.redditstockx.RedditApp
import com.example.matthewtaila.redditstockx.common.IRedditAPI
import com.example.matthewtaila.redditstockx.feed.model.PostFeed
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
// todo - introduce Koin for DI
object NetworkServices {

    private const val NO_INTERNET_CONNECTION_ERROR = "No Internet Connection"
    private const val Base_URL = "https://www.reddit.com/"

    fun getRedditServices(): IRedditAPI {

        val customGson = GsonBuilder().registerTypeAdapter(PostFeed::class.java, PostFeedDeserializer()).create()

        return Retrofit.Builder()
            .baseUrl(Base_URL)
            .addConverterFactory(GsonConverterFactory.create(customGson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(createOkHttpClient())
            .build()
            .create(IRedditAPI::class.java)
    }

    private fun createOkHttpClient(): OkHttpClient {

        val clientBuilder = OkHttpClient.Builder()
        clientBuilder.addInterceptor(ConnectivityInterceptor())

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        clientBuilder.addInterceptor(loggingInterceptor)
        return clientBuilder.build()
    }

    class ConnectivityInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            if (!RedditApp.instance.hasConnection()!!) throw NoConnectionError(
                NO_INTERNET_CONNECTION_ERROR
            )
            return chain.proceed(chain.request())
        }

        class NoConnectionError(message: String) : Throwable(message)
    }


}