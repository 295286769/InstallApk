package com.sharkgulf.checkandinstallapk.utils

import android.app.ActivityManager
import android.content.Context
import androidx.fragment.app.FragmentActivity

class Util {
    companion object{
        /**
         * 获取当前应用程序的包名
         * @return 返回包名
         */
        fun getAppProcessName(context: Context):String{
            //当前应用pid
            var pid = android.os.Process.myPid();
            //任务管理类
            var manager =  context.getSystemService(FragmentActivity.ACTIVITY_SERVICE) as ActivityManager;
            //遍历所有应用
            var infos = manager.getRunningAppProcesses();
            for (  item in infos) {
                if (item.pid == pid)//得到当前应用
                    return item.processName;//返回包名
            }
            return "";
        }
    }
}