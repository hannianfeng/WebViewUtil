package com.webviewlib.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding> : Fragment() {
    protected lateinit var vb: ViewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vb = getViewBindingRoot(container)
        initView()
        return vb.root
    }

    protected abstract fun getViewBindingRoot(container: ViewGroup?): VB
    protected abstract fun initView()
    fun log(logTest: String) {
        Log.e("---TAG---", logTest)
    }
}