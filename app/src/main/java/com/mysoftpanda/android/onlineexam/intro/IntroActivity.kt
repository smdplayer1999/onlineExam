package com.mysoftpanda.android.onlineexam.intro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mysoftpanda.android.onlineexam.BasicActivity
import com.mysoftpanda.android.onlineexam.R
import com.mysoftpanda.android.onlineexam.data.local.LocalStorage
import kotlinx.android.synthetic.main.activity_intro.*

class IntroActivity : AppCompatActivity() {

private lateinit var adapter: IntoPagerAdapter
    private val data = arrayListOf(
        IntoData(
            "Everything is possible",
            R.drawable.payment_statistics,
            R.drawable.path1,
            "Monitor your payments statistically save your time."
        ),
        IntoData(
            "Availability",
            R.drawable.pillow,
            R.drawable.path2,
            "Save your time. Everything important in one application."
        ),
        IntoData(
            "Now Shopping easy with us",
            R.drawable.shoppingcart,
            R.drawable.path3,
            "Buy everything online simple and safe "
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        if (LocalStorage.instance.isFirst) {
            loadViews()
        } else {
            startActivity(Intent(this, BasicActivity::class.java))
            finish()
        }
    }

    fun loadViews() {
        adapter = IntoPagerAdapter(
            data,
            this
        )
        pager.adapter = adapter
        indicator.setViewPager2(pager)

        button_dalee.setOnClickListener {
            LocalStorage.instance.isFirst = false
            startActivity(Intent(this, BasicActivity::class.java))
            finish()

        }
    }
}