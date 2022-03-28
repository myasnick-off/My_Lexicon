package com.example.mylexicon.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mylexicon.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, MainFragment.newInstance(), "")
            .commit()
    }
}