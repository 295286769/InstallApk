package com.sharkgulf.checkandinstallapk.utils

import android.os.Environment
import com.sharkgulf.checkandinstallapk.aplication.UpdateAplication
import java.io.File

class FileUtil {
    companion object{
        fun getStorePath():String{
            var path=""
            var pathName="/apkPath/"
            var pathFile:File?=null
            if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){//有外部存储目录
                path=Environment.getExternalStorageDirectory().absolutePath+pathName
                pathFile= File(path)

            }else{
                path= UpdateAplication.getContext().getFilesDir().getAbsolutePath()+pathName
                pathFile= File(path)
            }
            if(!pathFile.exists()){
                pathFile.mkdirs()
            }
            return ""
        }
        fun createFile(path:String):File{
            return File(path)
        }
    }
}