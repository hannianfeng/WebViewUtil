package com.webviewlib

import android.content.Context
import com.appsflyer.AppsFlyer2dXConversionCallback
import com.appsflyer.AppsFlyerLib

class AF(private val context: Context) {
    fun initAF(af_key: String) {
        AppsFlyerLib.getInstance()
            .init(af_key, AppsFlyer2dXConversionCallback(), context)
            .start(context)
    }
}