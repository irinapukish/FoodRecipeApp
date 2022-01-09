package com.foodreceipeapp.app.ui.launcher

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.foodreceipeapp.app.ui.main.launchMainActivity
import com.foodreceipeapp.app.ui.onboarding.launchOnBoardingActivity
import com.foodreceipeapp.app.ui.theme.DelishComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import com.foodreceipeapp.app.ui.launcher.LaunchDestination.MAIN_ACTIVITY
import com.foodreceipeapp.app.ui.launcher.LaunchDestination.ON_BOARDING
import com.foodreceipeapp.app.ui.util.checkAllMatched

@AndroidEntryPoint
class LauncherActivity : ComponentActivity() {

    @SuppressLint("VisibleForTests")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DelishComposeTheme {
                LauncherView(
                    onLauncherComplete = { destination ->
                        when (destination) {
                            MAIN_ACTIVITY -> launchMainActivity(context = this)
                            ON_BOARDING -> launchOnBoardingActivity(context = this)
                        }.checkAllMatched
                        finish()
                    }
                )
            }
        }
    }
}
