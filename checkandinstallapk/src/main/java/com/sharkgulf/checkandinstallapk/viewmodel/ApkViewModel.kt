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
    override fun returnApkPath(path: String) {
        if (TextUtils.isEmpty(path)) {
            callBack?.fail()
            return
        }
        callBack?.suceess(path)
    }
    override fun update(pregress: Int) {
        callBack?.update(pregress)
    }
    override fun start() {
        callBack?.start()
    }
    override fun fail() {
        callBack?.fail()
    }

    constructor(callBack:CallBackApk){
        this.callBack=callBack
        fileModel= FileModel(this)
    }

    fun updateApk(url:String?){
        fileModel?.downApk(url)
    }

}