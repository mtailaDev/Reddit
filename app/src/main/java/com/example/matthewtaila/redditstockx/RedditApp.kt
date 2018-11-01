package com.example.matthewtaila.redditstockx

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.example.matthewtaila.redditstockx.di.networkServiceModule
import org.koin.android.ext.android.startKoin

class RedditApp : Application(){

    companion object {
        lateinit var instance: RedditApp
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin(this, listOf(networkServiceModule))
    }

    fun hasConnection(): Boolean? {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo.isConnected
    }
}