package com.matahari.test.apps.network

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by Irfan Sholeh Hermawan on 30/07/19.
 * @author
 */

class NetWorkConection {

    companion object {
        @Suppress("DEPRECATION")
        @SuppressLint("MissingPermission")
        fun isNEtworkConnected(context: Context): Boolean {

            var connectionManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectionManager.activeNetworkInfo

            return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting
        }
    }
}
