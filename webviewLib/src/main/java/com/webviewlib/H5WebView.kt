package com.webviewlib

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
class H5WebView : WebView {
    private var lw: LW? = null
    private var activity: Activity? = null

    interface LW {
        fun onProgressChanged(progress: Int)
    }
    fun setActivity(activity: Activity?, lw: LW){
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
            addJavascriptInterface(this, "cg")
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
}