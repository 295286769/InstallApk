package com.sharkgulf.checkandinstallapk.server

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.huangshang.testapplication.inteface.CallBackApk
import com.sharkgulf.checkandinstallapk.model.NotificationModel
import com.sharkgulf.checkandinstallapk.model.PermissionSuccessEvent
import com.sharkgulf.checkandinstallapk.utils.StartActivityUtil
import com.sharkgulf.checkandinstallapk.viewmodel.ApkViewModel
import org.greenrobot.eventbus.EventBus


/**
 * 后天下载apk service
 */
class UpdateApkService :Service(),CallBackApk {


    private var notificationModel: NotificationModel? = null
    private var url:String?=""
    private var apkViewModel:ApkViewModel?=null

    override fun onCreate() {
        super.onCreate()
        apkViewModel= ApkViewModel(this)
        notificationModel=NotificationModel(this)
    }
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        url=intent?.getStringExtra("url")
        apkViewModel?.updateApk()
        return super.onStartCommand(intent, flags, startId)
    }
    /**
     * 跟新进度
     */
    override fun update(progress: Int) {
        notificationModel?.let { it.setProgress(progress) }
    }
    /**
     * 下载成功
     */
    override fun suceess(path: String) {
        notificationModel?.let { it.notificationCancel() }
        EventBus.getDefault().post(PermissionSuccessEvent(path))

//        installAPK(path)
    }

    /**
     * 下载开始通知栏弹通知
     */
    override fun start() {
        var id = "my_channel_01";
        var name = "我是渠道名字";
        notificationModel?.let { it.notifycation(id,name) }
    }
    /**
     * 下载失败
     */
    override fun fail() {
        notificationModel?.let { it.notificationCancel() }
    }

//    /**
//     * 安装apk
//     */
//     fun installAPK(path: String) {
//        StartActivityUtil.installAPK(this,path)
//    }
}