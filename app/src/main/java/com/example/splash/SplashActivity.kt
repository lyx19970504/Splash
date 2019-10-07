package com.example.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        parallax_container.setUp(intArrayOf(
            R.layout.view_intro_1,
            R.layout.view_intro_2,
            R.layout.view_intro_3,
            R.layout.view_intro_4,
            R.layout.view_intro_5,
            R.layout.view_intro_6,
            R.layout.view_intro_7,
            R.layout.view_login
        ))
        parallax_container.setIv_man(iv_man)
        iv_man.setBackgroundResource(R.drawable.man_run)
    }

    fun enter(view: View) {
        startActivity(Intent(this,MainActivity::class.java))
    }
}
