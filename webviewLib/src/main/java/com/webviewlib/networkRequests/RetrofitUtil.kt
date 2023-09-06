package com.webviewlib.networkRequests

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

class RetrofitUtil {
    internal interface UrlService {
        @POST("api/{a}")
        fun postData(
            @Header("X-requested-with") pk: String?,
            @Path("a") end:String
        ): Call<ResponseBody?>?
    }

    private fun getCall(url: String, pkg: String, end: String): Call<ResponseBody?>? {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(url)
            .client(Client().getClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val urlService: UrlService = retrofit.create(UrlService::class.java)
//        val call = urlService.postData("com.freetime.parttime.jobs")
        return urlService.postData(pkg, end)
    }
    fun getData(url: String, pkg: String, end: String, callback: Callback<ResponseBody?>){
        getCall(url, pkg, end)!!.enqueue(callback)
    }
    fun getData(url: String, pkg: String, end: String): Response<ResponseBody?> {
        return getCall(url, pkg, end)!!.execute()
    }
}