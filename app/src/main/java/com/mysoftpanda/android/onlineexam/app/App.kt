package com.mysoftpanda.android.onlineexam.app

import android.app.Application
import com.mysoftpanda.android.onlineexam.data.local.LocalStorage

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        LocalStorage.init(this)
        instance = this
    }

    companion object{
        lateinit var instance : App
    }
}