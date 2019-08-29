package com.huangshang.testapplication.viewmodel

import android.content.Intent
import android.net.Uri
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.huangshang.testapplication.inteface.CallBackApk
import com.huangshang.testapplication.inteface.CheckSelfPermissionCall
import com.huangshang.testapplication.inteface.IFileModel
import com.huangshang.testapplication.model.FileModel
import java.io.File

class ApkViewModel : IFileModel {
    var fileModel:FileModel?=null
    var callBack:CallBackApk?=null
    var check:CheckSelfPermissionCall?=null
    override fun returnApkPath(path: String) {
        if (TextUtils.isEmpty(path)) {
            callBack?.fail()
            return
        }
        callBack?.suceess(path)
    }

    constructor(callBack:CallBackApk,checkSelfPermissionCall: CheckSelfPermissionCall){
        this.callBack=callBack
        this.check=checkSelfPermissionCall
        fileModel= FileModel()
    }

    fun installApk(){

        fileModel?.downApk(this)
    }
    fun onClickBtn(v:View){
        check?.onCheck()

    }
   public fun saveApk(){
       installApk()
    }

}