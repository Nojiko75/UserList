package com.example.data.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class NetworkStateManagerImpl(private val context: Context) : NetworkStateManager {
    override fun hasNetWorkConnection(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
        return networkInfo?.isConnectedOrConnecting == true
    }
}