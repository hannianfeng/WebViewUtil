package com.webview.util

import com.webview.util.databinding.ActivityMainBinding
import com.webviewlib.H5WebView
import com.webviewlib.base.BaseActivity
import com.webviewlib.networkRequests.OkHttpUtil
import com.webviewlib.networkRequests.RetrofitUtil
import kotlinx.coroutines.*
import org.json.JSONArray
import org.json.JSONObject
import okhttp3.Callback as OKCall
import java.io.IOException

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getViewBindingRoot(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun initView() {
        GlobalScope.launch(Dispatchers.IO) {
//                RetrofitUtil().getData("https://www.stockpioneerapps.xyz/",
//                    "com.freetime.parttime.jobs",
//                    "rsydtjgh",
//                    object : Callback<ResponseBody?> {
//                        override fun onResponse(
//                            call: Call<ResponseBody?>,
//                            response: Response<ResponseBody?>
//                        ) {
//                            val string = response.body()?.string()
//                            Log.e("---TAG---", "RetrofitUtilRetrofitUtilRetrofitUtil: $string")
//                        }
//
//                        override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
//                            log("RetrofitUtil ${t.message}")
//                        }
//                    })
//                var jsonArray: JSONArray? = null
//            OkHttpUtil().getCall(
//                "https://www.stockpioneerapps.xyz/api/fkstrh",
//                "com.freetime.parttime.jobs",
//                object : OKCall {
//                    override fun onFailure(call: okhttp3.Call, e: IOException) {
//                        log("OkHttp $e")
//                    }
//
//                    override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
//                        val string = response.body!!.string()
//                        val jsonObject1 = JSONObject(string)
//                        var jsonArray = JSONArray(jsonObject1.optString("data"))
//                        log(string)
//                    }
//                }
//            )
//            val data = OkHttpUtil().getCall(
//                "https://www.stockpioneerapps.xyz/api/fkstrh",
//                "com.freetime.parttime.jobs"
//            )
//            val data1=RetrofitUtil().getData("https://www.stockpioneerapps.xyz/","com.freetime.parttime.jobs","jfgdaf")
//            withContext(Dispatchers.Main) {
//                val string = data.body!!.string()
//                val string1 = data1.body()!!.string()
//                log(string)
//                log(string1)
//                val jo=JSONObject(string)
//                val jsonArray=JSONArray(jo.optString("data"))
//                vb.h5web.setActivity(this@MainActivity, object : H5WebView.LW {
//                    override fun onProgressChanged(progress: Int) {
//
//                    }
//                })
//                vb.h5web.loadUrl(decode(jsonArray.get(1) as String))
//            }
        }
    }
}