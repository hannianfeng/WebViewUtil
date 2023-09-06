package com.webviewlib.base

import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {
    protected lateinit var vb: VB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = getViewBindingRoot()
        setContentView(vb.root)
        initView()
    }

    protected abstract fun getViewBindingRoot(): VB
    protected abstract fun initView()
    fun log(logTest: String) {
        Log.e("---TAG---", logTest)
    }

    fun toastUtil(toastStr: String) {
        Toast.makeText(this, toastStr, Toast.LENGTH_SHORT).show()
    }
    protected fun decode(data: String): String {
        return String(Base64.decode(Base64.decode(data, Base64.NO_WRAP), Base64.NO_WRAP))
    }
}