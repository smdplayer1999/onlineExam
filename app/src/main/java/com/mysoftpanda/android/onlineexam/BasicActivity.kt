package com.mysoftpanda.android.onlineexam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mysoftpanda.android.onlineexam.auth.LoginFragment
import com.mysoftpanda.android.onlineexam.contact.ContactFragment
import com.mysoftpanda.android.onlineexam.data.local.LocalStorage

class BasicActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic)
        startFragment()
    }

    private fun startFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        LocalStorage.instance.added == "-1"
        if (LocalStorage.instance.remember == "1") {
            transaction.replace(R.id.layoutFragment, ContactFragment()).commit ()
        } else {
            transaction.replace(R.id.layoutFragment, LoginFragment()).commit ()
        }
    }
}