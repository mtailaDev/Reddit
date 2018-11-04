package com.example.matthewtaila.redditstockx.common.network

import android.content.Context
import android.net.ConnectivityManager
import okhttp3.Interceptor
import okhttp3.Response
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class ConnectivityInterceptor : Interceptor, KoinComponent {

    val context: Context by inject()

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!hasConnection()) throw NoConnectionError("No Internet Connection")
        return chain.proceed(chain.request())
    }

    private fun hasConnection(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo.isConnected
    }

    inner class NoConnectionError(message: String) : Throwable(message)
}