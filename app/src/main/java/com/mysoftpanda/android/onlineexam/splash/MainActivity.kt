package com.mysoftpanda.android.onlineexam.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.mysoftpanda.android.onlineexam.R
import com.mysoftpanda.android.onlineexam.intro.IntroActivity
import com.mysoftpanda.android.onlineexam.utils.animateSlide
import com.mysoftpanda.android.onlineexam.utils.extensions.animateView
import com.mysoftpanda.android.onlineexam.utils.extensions.didClickButton
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        didClickButton(logo_smd,this)
        Executors.newSingleThreadExecutor().execute {
            Thread.sleep(1000)
            startActivity(Intent(this,IntroActivity::class.java))
            finish()


        }
    }


}