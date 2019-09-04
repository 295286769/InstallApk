package com.sharkgulf.checkandinstallapk.model

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.os.Environment
import android.os.Handler
import android.os.Message
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import com.sharkgulf.checkandinstallapk.R
import com.sharkgulf.checkandinstallapk.aplication.UpdateAplication
import com.sharkgulf.checkandinstallapk.inteface.IFileModel
import java.io.File
import java.io.FileOutputStream
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread
import android.content.Context
import com.sharkgulf.checkandinstallapk.utils.FileUtil
import com.sharkgulf.checkandinstallapk.utils.Util


class FileModel {
    private val NotificationID = 0x10000
    var apkPath: String = ""
    var version: String = "1.1"
    private val DOWN_UPDATE = 1

    private val DOWN_OVER = 2
    var progress = 0
    //  请求链接
    private val url = "https://download.dgstaticresources.net/fusion/android/app-c6-release.apk"
    private var mNotificationManager: NotificationManager? = null
    private var builder: NotificationCompat.Builder? = null
    var hadler: Handler = object : Handler() {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            when (msg?.what) {
                DOWN_UPDATE->{
                    builder?.setProgress(100,progress,false)
                    builder?.setContentText(progress.toString()+"%")
                    mNotificationManager?.notify(NotificationID, builder?.build());
                }
                DOWN_OVER->{
                    mNotificationManager?.cancel(NotificationID);
                }
            }

        }
    }

    public fun downApk(ifielModel: IFileModel) {
        thread(start = true) {
            try {
                notifycation()
//                if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                apkPath = FileUtil.getStorePath()

                val conn = URL(url).openConnection() as HttpURLConnection
                conn.connect()
                var isStream = conn.inputStream
                var length = conn.contentLength
                var pathFile = File(apkPath, version)
                var fos = FileOutputStream(pathFile)
                var count = 0
                var buffer = ByteArray(1024)
                var mIsCancel = false

                while (!mIsCancel) {
                    var numread: Int = isStream.read(buffer)
                    count += numread
                    progress = (count.toFloat() / length * 100).toInt()
                    // 下载完成
                    hadler.sendEmptyMessage(DOWN_UPDATE)
                    if (numread < 0) {
                        hadler.sendEmptyMessage(DOWN_OVER)
                        break;
                    }
                    fos.write(buffer, 0, numread);
                }
                fos.close();
                isStream.close()
                var file = File(apkPath, version)
                if (file.exists()) {
                    var apkPath: String = file.toString()
                    ifielModel.returnApkPath(apkPath)
                }

//                    hadler.sendEmptyMessage(0)
//                }

            } catch (e: Exception) {
                Log.e("TAG", e.message)
            }
        }


    }

    fun notifycation() {
        var id = "my_channel_01";
        var name = "我是渠道名字";
        var mNotificationManager =
            UpdateAplication.getContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        // 针对Android 8.0版本对于消息栏的限制，需要加入channel渠道这一概念
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {  //Android 8.0以上
            var mChannel = NotificationChannel(id, name, NotificationManager.IMPORTANCE_LOW)
            Log.i("DownAPKService", mChannel.toString())
            mNotificationManager.createNotificationChannel(mChannel);
            builder = NotificationCompat.Builder(UpdateAplication.getContext())
            builder?.setSmallIcon(R.drawable.nav_icon_back)
            builder?.setTicker("正在下载新版本")
            builder?.setContentTitle(Util.getAppProcessName())
            builder?.setContentText("正在下载,请稍后...")
            builder?.setNumber(0)
            builder?.setChannelId(id)
            builder?.setAutoCancel(true)
        } else {    //Android 8.0以下
            builder = NotificationCompat.Builder(UpdateAplication.getContext())
            builder?.setSmallIcon(R.drawable.nav_icon_back);
            builder?.setTicker("正在下载新版本");
            builder?.setContentTitle(Util.getAppProcessName());
            builder?.setContentText("正在下载,请稍后...");
            builder?.setNumber(0);
            builder?.setAutoCancel(true);
        }
        builder?.setProgress(100,0,false)

        mNotificationManager.notify(NotificationID, builder?.build());
    }


}