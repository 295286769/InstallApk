package com.sharkgulf.checkandinstallapk.server

import android.app.Service
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.IBinder
import android.provider.Settings
import androidx.core.content.FileProvider
import com.huangshang.checkandinstallapk.inteface.CheckSelfPermissionCall
import com.huangshang.testapplication.inteface.CallBackApk
import com.sharkgulf.checkandinstallapk.utils.Util
import com.sharkgulf.checkandinstallapk.viewmodel.ApkViewModel
import java.io.File



/**
 * 后天下载apk service
 */
class UpdateApkService :Service(),CallBackApk, CheckSelfPermissionCall {

    /**
     * 下载成功
     */
    override fun suceess(path: String) {
        installAPK(path)
    }

    /**
     * 下载失败
     */
    override fun fail() {
    }

    override fun onCheck() {
    }

    var url:String?=""
    var apkViewModel:ApkViewModel?=null
    override fun onCreate() {
        super.onCreate()
        apkViewModel= ApkViewModel(this,this)
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
     * 安装apk
     */
    open fun installAPK(path: String) {

        val apkFile = File(path)
        if (!apkFile.exists()) {
            return
        }
        val intent = Intent(Intent.ACTION_VIEW)
        //      安装完成后，启动app（源码中少了这句话）

        if (null != apkFile) {
            try {
                //兼容7.0
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//参数1 上下文, 参数2 Provider主机地址 和配置文件中保持一致   参数3  共享的文件
                    val contentUri = FileProvider.getUriForFile(
                        this,
                        Util.getAppProcessName() + ".fileprovider",
                        apkFile
                    )
                    //添加这一句表示对目标应用临时授权该Uri所代表的文件
                    intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
                    intent.setDataAndType(contentUri, "application/vnd.android.package-archive")
                    //兼容8.0
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        val hasInstallPermission = packageManager.canRequestPackageInstalls()
                        if (!hasInstallPermission) {
                            startInstallPermissionSettingActivity()
                            return
                        }
                    }
                } else {
                    intent.setDataAndType(
                        Uri.fromFile(apkFile),
                        "application/vnd.android.package-archive"
                    )
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                }
                if (packageManager.queryIntentActivities(intent, 0).size > 0) {
                    startActivity(intent)
                }
            } catch (e: Throwable) {
                e.printStackTrace()
            }

        }
    }
    private fun startInstallPermissionSettingActivity() {
        //注意这个是8.0新API
        val packageURI = Uri.parse("package:$packageName")
        val intent = Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, packageURI)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }
}