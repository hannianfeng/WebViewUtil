package com.webviewlib.networkRequests

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class Client {
    fun getClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS) // 设置连接超时时间
            .readTimeout(60, TimeUnit.SECONDS) // 设置读取超时时间
            .writeTimeout(60, TimeUnit.SECONDS) // 设置写入超时时间
            .build()
    }
}