package com.webviewlib

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.webviewlib.databinding.ActivityH5MainBinding

class H5MainActivity : BaseActivity<ActivityH5MainBinding>() {
    override fun getViewBindingRoot(): ActivityH5MainBinding {
        return ActivityH5MainBinding.inflate(layoutInflater)
    }

    override fun initView() {
        val url = intent.getStringExtra("url")
        vb.h5web.setActivity(this,object : H5WebView.LW{
            override fun onProgressChanged(progress: Int) {
                vb.h5Lottie.visibility= View.GONE
            }
        })
        if (url != null) {
            vb.h5web.loadUrl(url)
        }
    }
}