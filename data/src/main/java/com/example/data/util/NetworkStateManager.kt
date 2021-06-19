package com.example.data.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build

object NetworkManager {
    fun hasNetworkConnection(context: Context): Boolean {
        var result = false
        val connectivityManager = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            connectivityManager?.run {
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                    ?.run {
                        result = when {
                            hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                            hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                            hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                            hasTransport(NetworkCapabilities.TRANSPORT_VPN) -> true
                            else -> false
                        }
                }
            }
        } else {
            connectivityManager?.run {
                val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
                return activeNetwork?.isConnectedOrConnecting == true
            }
        }
        return result
    }
}