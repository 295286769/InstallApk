package com.huangshang.testapplication

import android.app.Application
import android.content.Context

class MyAppAplication:Application() {

    companion object{
        lateinit  var  mContext:Context
        fun getContext():Context{
            return mContext
        }
    }
    override fun onCreate() {
        super.onCreate()
        mContext=this
    }
}