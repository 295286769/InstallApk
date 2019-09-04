package com.sharkgulf.checkandinstallapk.viewmodel

import android.content.Intent
import android.net.Uri
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import com.huangshang.checkandinstallapk.inteface.CheckSelfPermissionCall
import com.huangshang.testapplication.inteface.CallBackApk
import com.sharkgulf.checkandinstallapk.inteface.IFileModel
import com.sharkgulf.checkandinstallapk.model.FileModel
import java.io.File

class ApkViewModel : IFileModel {
    var fileModel: FileModel?=null
    var callBack:CallBackApk?=null
    var check: CheckSelfPermissionCall?=null
    override fun returnApkPath(path: String) {
        if (TextUtils.isEmpty(path)) {
            callBack?.fail()
            return
        }
        callBack?.suceess(path)
    }
    constructor(){
        fileModel= FileModel()
    }

    constructor(callBack:CallBackApk,checkSelfPermissionCall: CheckSelfPermissionCall){
        this.callBack=callBack
        this.check=checkSelfPermissionCall
        fileModel= FileModel()
    }
    constructor(callBack:CallBackApk){
        this.callBack=callBack
        fileModel= FileModel()
    }

    fun installApk(){

        fileModel?.downApk(this)
    }
//    fun onClickBtn(v:View){
//
//
//    }
//    //点击确定开始下载
//    fun onClickComfir(v: View){
//        check?.onCheck()
//    }
    public fun updateApk(){
        installApk()
    }

}