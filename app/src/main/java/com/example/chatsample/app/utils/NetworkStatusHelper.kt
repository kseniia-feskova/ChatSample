package com.example.chatsample.app.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network

class NetworkStatusHelper(private val context: Context) {

    fun registerNetworkCallback(isNetworkConnected: (Boolean) -> Unit) {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.registerDefaultNetworkCallback(object :
            ConnectivityManager.NetworkCallback() {
            override fun onLost(network: Network) {
                super.onLost(network)
                isNetworkConnected(false)
            }

            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                isNetworkConnected(true)
            }
        })
    }
}
