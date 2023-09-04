package com.webviewlib

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class Test :AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("---TAG---", "onCreate: " )
    }
}