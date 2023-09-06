package com.webviewlib

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.util.Log
import android.view.KeyEvent
import android.webkit.*
import com.appsflyer.AFInAppEventParameterName
import com.appsflyer.AppsFlyerLib
import java.util.*

class H5WebView : WebView {
    private var lw: LW? = null
    private var activity: Activity? = null

    interface LW {
        fun onProgressChanged(progress: Int)
    }

    fun setActivity(activity: Activity?, lw: LW) {
        this.activity = activity
        this.lw = lw
    }

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    @SuppressLint("JavascriptInterface")
    private fun init() {
        try {
            val settings = settings
            settings.javaScriptEnabled = true
            settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            settings.databaseEnabled = true
            settings.allowFileAccess = true
            settings.javaScriptCanOpenWindowsAutomatically = true
            settings.useWideViewPort = true
            settings.domStorageEnabled = true
            addJavascriptInterface(AppsFlyerEvent(),"jsBridge")
            addJavascriptInterface(AppsFlyerEvent(),"androidJS")
            webChromeClient = object : WebChromeClient() {
                override fun onProgressChanged(view: WebView, newProgress: Int) {
                    super.onProgressChanged(view, newProgress)
                    if (lw != null && newProgress > 70) lw!!.onProgressChanged(newProgress)
                }
            }
            setDownloadListener { url: String?, _: String?, _: String?, _: String?, _: Long ->
                try {
                    activity!!.startActivity(Intent.parseUri(url, Intent.URI_INTENT_SCHEME))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            webViewClient = object : WebViewClient() {
                @SuppressLint("LongLogTag")
                override fun shouldOverrideUrlLoading(
                    view: WebView,
                    request: WebResourceRequest
                ): Boolean {
                    return false
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && this.canGoBack()) {
            this.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
    inner class AppsFlyerEvent() {
        @JavascriptInterface
        fun tracker(name: String, params: String) {
            afEvent(name)
        }

        @JavascriptInterface
        fun postMessage(name: String, params: String) {
            afEvent(name)
        }

        private fun afEvent(name: String) {
            Log.e("---TAG---", "postMessage: $name")
            try {
                val eventValuesDefalut: MutableMap<String, Any> = HashMap()
                eventValuesDefalut[AFInAppEventParameterName.PARAM_1] = name
                AppsFlyerLib.getInstance().logEvent(
                    activity,
                    name, eventValuesDefalut
                )
            } catch (e: Exception) {
                Log.e("TAG", "afEvent: $e" )
            }
        }
    }
}