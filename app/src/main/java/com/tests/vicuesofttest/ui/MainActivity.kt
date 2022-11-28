package com.tests.vicuesofttest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tests.vicuesofttest.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, PlayerFragment())
            .commit()
    }
}