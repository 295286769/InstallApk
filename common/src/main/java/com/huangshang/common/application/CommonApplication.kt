package com.huangshang.common.application

import android.app.Application
import android.content.Context

class CommonApplication:Application() {
    companion object{
        lateinit  var  mContext: Context
        fun getContext(): Context {
            return mContext
        }
    }
    override fun onCreate() {
        super.onCreate()
        mContext=this
    }
}