package com.sharkgulf.checkandinstallapk.model

import android.os.Build
import android.os.Environment
import android.os.Handler
import android.os.Message
import android.util.Log
import com.sharkgulf.checkandinstallapk.inteface.IFileModel
import java.io.File
import java.io.FileOutputStream
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread
import android.text.TextUtils
import com.sharkgulf.checkandinstallapk.utils.FileUtil

class FileModel {
    var apkPath: String = ""
    var version: String = "1.1"
    private val DOWN_START = 1
    private val DOWN_UPDATE = 2
    private val DOWN_OVER = 3
    private val DOWN_ERRER = 4
    private var progress=0

    private var ifielModel:IFileModel?=null
    //  请求链接
    private val url = "https://download.dgstaticresources.net/fusion/android/app-c6-release.apk"
    var hadler: Handler = object : Handler() {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            when (msg?.what){
                DOWN_START ->{
                    ifielModel?.let { it.start() }
            }
                DOWN_UPDATE ->{
                    ifielModel?.let { it.update(progress) }
            }
                DOWN_OVER ->{
                    ifielModel?.let { it.returnApkPath(apkPath) }
            }
                DOWN_ERRER ->{
                    ifielModel?.let { it.fail() }
            }
            }
        }
    }
    constructor(ifielModel:IFileModel){
        this.ifielModel=ifielModel
        apkPath = FileUtil.getStorePath()
    }

    public fun downApk() {
        thread(start = true) {
            try {
                if(!TextUtils.isEmpty(apkPath)){
                    hadler.sendEmptyMessage(DOWN_START)
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
                        hadler.sendEmptyMessage(DOWN_UPDATE)
                        // 下载完成
                        if (numread < 0) {
                            apkPath=pathFile.absolutePath
                            hadler.sendEmptyMessage(DOWN_OVER)
                            break;
                        }
                        fos.write(buffer, 0, numread);
                    }
                    fos.close();
                    isStream.close()
                }

            } catch (e: Exception) {
                hadler.sendEmptyMessage(DOWN_ERRER)
                Log.e("TAG", e.message)
            }
        }


    }

}