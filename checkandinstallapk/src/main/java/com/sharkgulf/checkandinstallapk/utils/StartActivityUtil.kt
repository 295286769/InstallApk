package com.sharkgulf.checkandinstallapk.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.core.content.FileProvider
import com.sharkgulf.checkandinstallapk.server.UpdateApkService
import java.io.File

/**
 * 启动页面工具类
 */
class StartActivityUtil {
    companion object{
        /**
         * 后台启动下载apkservice
         */
        fun startUpdateApkService(context: Context?){
            var intent:Intent=Intent(context, UpdateApkService::class.java)
            intent.putExtra("url","url")
            context?.startService(intent);
//            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//                context?.startForegroundService(intent)
//            } else {
//                context?.startService(intent);
//            }
        }
        /**
         * 安装apk
         */
         fun installAPK(context: Context,path: String){
             val apkFile = File(path)
             if (!apkFile.exists()) {
                 return
             }
             val intent = Intent(Intent.ACTION_VIEW)
            //由于没有在Activity环境下启动Activity,所以设置下面的标签
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
             //      安装完成后，启动app（源码中少了这句话）

             if (null != apkFile) {
                 try {
                     //兼容7.0
                     if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//参数1 上下文, 参数2 Provider主机地址 和配置文件中保持一致   参数3  共享的文件
                         val contentUri = FileProvider.getUriForFile(
                             context,
                             Util.getAppProcessName() + ".fileprovider",
                             apkFile
                         )
                         //添加这一句表示对目标应用临时授权该Uri所代表的文件
                         intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                         intent.setDataAndType(contentUri, "application/vnd.android.package-archive")
                         //兼容8.0
                         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                             val hasInstallPermission = context.packageManager.canRequestPackageInstalls()
                             if (!hasInstallPermission) {
                                 startInstallPermissionSettingActivity(context)
                                 return
                             }
                         }
                     } else {
                         intent.setDataAndType(
                             Uri.fromFile(apkFile),
                             "application/vnd.android.package-archive"
                         )
                         intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                     }
                     if (context.packageManager.queryIntentActivities(intent, 0).size > 0) {
                         context.startActivity(intent)
                     }
                 } catch (e: Throwable) {
                     e.printStackTrace()
                 }

             }
         }
        fun startInstallPermissionSettingActivity(context: Context) {
            //注意这个是8.0新API
            val packageURI = Uri.parse("package:${context.packageName}")
            val intent = Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, packageURI)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }

}