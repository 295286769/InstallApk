package com.huangshang.testapplication

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.huangshang.testapplication.utils.AppUtil

class MyAplication :Application(){
    override fun onCreate() {
        super.onCreate()
        if (AppUtil.getIsdebug()) {           // These two lines must be written before init, otherwise these configurations will be invalid in the init process
            ARouter.openLog();     // Print log
            ARouter.openDebug();   // Turn on debugging mode (If you are running in InstantRun mode, you must turn on debug mode! Online version needs to be closed, otherwise there is a security risk)
        }
        ARouter.init(this); // As early as possible, it is recommended to initialize in the Application
    }
}