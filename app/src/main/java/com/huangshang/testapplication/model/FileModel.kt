package com.huangshang.testapplication.model

import android.os.Environment
import android.os.Handler
import android.os.Message
import android.util.Log
import androidx.core.app.ActivityCompat
import com.huangshang.testapplication.inteface.IFileModel
import java.io.File
import java.io.FileOutputStream
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLConnection
import kotlin.concurrent.thread

class FileModel{
    var apkPath:String=""
    var version:String="1.1"
    //  请求链接
    private val url = "https://download.dgstaticresources.net/fusion/android/app-c6-release.apk"
    var hadler:Handler=object :Handler(){
    override fun handleMessage(msg: Message?) {
        super.handleMessage(msg)


    }
}
     public fun downApk(ifielModel:IFileModel) {
        thread(start = true){
            try {
                if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                     apkPath=Environment.getExternalStorageDirectory().toString()+ "/testApk"
                    var dir:File= File(apkPath)
                    if (!dir.exists()){
                        dir.mkdir();
                    }
                    val conn = URL(url).openConnection() as HttpURLConnection
                    conn.connect()
                    var isStream = conn.inputStream
                    var length = conn.contentLength
                    var pathFile=File(apkPath,version)
                    var fos = FileOutputStream(pathFile)
                    var count = 0
                    var buffer = ByteArray(1024)
                    var mIsCancel=false

                    while (!mIsCancel){
                        var numread:Int = isStream.read(buffer)
                        count += numread

                        // 下载完成
                        if (numread < 0){
                            break;
                        }
                        fos.write(buffer, 0, numread);
                    }
                    fos.close();
                    isStream.close()
                    var file= File(apkPath,version)
                    if (file.exists()){
                        var apkPath:String=file.toString()
                        ifielModel.returnApkPath(apkPath)
                    }

//                    hadler.sendEmptyMessage(0)
                }

            } catch (e: Exception) {
                Log.e("TAG",e.message)
            }
        }



    }




}