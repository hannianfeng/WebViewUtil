package com.webviewlib.networkRequests

import okhttp3.*

class OkHttpUtil {
    private fun getRequest(url: String, pkg: String): Request {
        val request = Request.Builder().apply {
            get()
            header("X-requested-with", pkg)
            url(url)
//            header("X-requested-with", "com.freetime.parttime.jobs")
//            url("https://www.stockpioneerapps.xyz/api/fshjgnd")
        }.build()
        return request
    }
    fun getCall(url: String, pkg: String,callback: Callback){
        Client().getClient().newCall(getRequest(url, pkg)).enqueue(callback)
    }
    fun getCall(url: String, pkg: String): Response {
        return Client().getClient().newCall(getRequest(url, pkg)).execute()
    }
    /*
    * object : Callback {
            override fun onFailure(call: Call, e: IOException) {
            }

            override fun onResponse(call: Call, response: Response) {
                Log.e("---TAG---", "end: ${response.code}")
                try {
                    val str = response.body!!.string()
                    val jsonObject = JSONObject(str).optString("data")
                    val jsonArray = JSONArray(jsonObject)
                    val url = decode(jsonArray[1].toString())//url
                } catch (e: IOException) {
                }
            }
        }
    * */
}