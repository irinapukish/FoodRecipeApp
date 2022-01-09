package com.foodreceipeapp.app.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewTreeLifecycleOwner
import com.foodreceipeapp.app.R
import dagger.hilt.android.AndroidEntryPoint

fun launchMainActivity(context: Context) {
    context.startActivity(Intent(context, MainActivity::class.java))
}

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ViewTreeLifecycleOwner.set(window.decorView, this)
    }
}
