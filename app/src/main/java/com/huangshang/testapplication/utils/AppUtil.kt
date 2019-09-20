package com.huangshang.testapplication.utils

import com.huangshang.testapplication.BuildConfig


class AppUtil {
    companion object{
        fun getIsdebug():Boolean{
            if(BuildConfig.isDebug.equals("debug")){
                return true
            }
            return false
        }
    }
}