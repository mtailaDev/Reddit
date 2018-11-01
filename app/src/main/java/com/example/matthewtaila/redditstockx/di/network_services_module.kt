package com.example.matthewtaila.redditstockx.di

import com.example.matthewtaila.redditstockx.common.IRedditAPI
import com.example.matthewtaila.redditstockx.common.network.ConnectivityInterceptor
import com.example.matthewtaila.redditstockx.common.network.PostFeedDeserializer
import com.example.matthewtaila.redditstockx.feed.model.PostFeed
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val baseUrl: String = "https://www.reddit.com/"

    val networkServiceModule = module {
        single { getLoggingInterceptor() }
        single { getConnectionInterceptor() }
        single { getOkHttpClient(get(), get()) }
        single { getCustomGson() }
        single { getRetrofit(get(), get(), baseUrl) }
        single { getRedditApiServices(get()) }
    }

    private fun getLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply { HttpLoggingInterceptor.Level.BODY }
    }

    private fun getConnectionInterceptor(): ConnectivityInterceptor {
        return ConnectivityInterceptor()
    }

    private fun getOkHttpClient(loggingInterceptor: HttpLoggingInterceptor, connectivityInterceptor: ConnectivityInterceptor): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
        clientBuilder.addInterceptor(loggingInterceptor)
        clientBuilder.addInterceptor(connectivityInterceptor)
        return clientBuilder.build()
    }

    private fun getCustomGson(): Gson {
        return GsonBuilder().registerTypeAdapter(PostFeed::class.java, PostFeedDeserializer()).create()
    }

    private fun getRetrofit(okHttpClient: OkHttpClient, gson: Gson, baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }

    private fun getRedditApiServices(retrofit: Retrofit): IRedditAPI {
        return retrofit.create(IRedditAPI::class.java)
    }
