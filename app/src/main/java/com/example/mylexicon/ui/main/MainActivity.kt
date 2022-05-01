package com.example.mylexicon.ui.main

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.AnticipateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.mylexicon.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val splashScreen = installSplashScreen()
        setContentView(R.layout.activity_main)

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

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, MainFragment.newInstance(), "")
            .commit()
    }
}