package com.example.mylexicon.ui.main

import android.animation.ObjectAnimator
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.animation.AnticipateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.mylexicon.R

class MainActivity : AppCompatActivity() {

    private var splashScreen: SplashScreen? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            splashScreen = installSplashScreen()
        }

        setContentView(R.layout.activity_main)

        splashScreen?.let { splashScreen ->
            splashScreen.setOnExitAnimationListener() { splashProvider ->
                ObjectAnimator.ofFloat(
                    splashProvider.view,
                    View.TRANSLATION_X,
                    0f,
                    splashProvider.view.width.toFloat()
                ).apply {
                    interpolator = AnticipateInterpolator()
                    duration = 500
                    doOnEnd {
                        splashProvider.remove()
                    }
                    start()
                }
            }
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, MainFragment.newInstance(), "")
            .commit()
    }
}