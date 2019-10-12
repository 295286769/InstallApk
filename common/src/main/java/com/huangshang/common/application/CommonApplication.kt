package com.huangshang.common.application

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex

open class CommonApplication:Application() {
    companion object{
        lateinit  var  mContext: Context
        fun getContext(): Context {
            return mContext
        }
    }
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
    override fun onCreate() {
        super.onCreate()
        mContext=this
    }
}