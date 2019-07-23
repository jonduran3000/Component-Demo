package com.rightpoint.experiments.demo.component

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.contentFrame, SelectionFragment())
            .commit()
    }

    fun goToScreen(screen: Screen) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.contentFrame, screen.newInstance())
            .addToBackStack(screen.screenName)
            .commit()
    }
}
