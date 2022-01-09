package com.foodreceipeapp.app.ui.onboarding

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import com.foodreceipeapp.app.ui.theme.DelishComposeTheme
import com.google.accompanist.insets.ProvideWindowInsets
import dagger.hilt.android.AndroidEntryPoint

fun launchOnBoardingActivity(context: Context) {
    context.startActivity(Intent(context, OnBoardingActivity::class.java))
}

@AndroidEntryPoint
class OnBoardingActivity : ComponentActivity() {

    @ExperimentalAnimationApi
    @SuppressLint("VisibleForTests")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ProvideWindowInsets {
                DelishComposeTheme {
                    OnBoardingContent {
                        finish()
                    }
                }
            }
        }
    }
}
