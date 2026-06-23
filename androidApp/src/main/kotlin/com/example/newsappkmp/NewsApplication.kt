package com.example.newsappkmp

import android.app.Application
import com.example.newsappkmp.data.local.createAndroidDatabase
import com.example.newsappkmp.di.initKoin

class NewsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin(createAndroidDatabase(this))
    }
}
