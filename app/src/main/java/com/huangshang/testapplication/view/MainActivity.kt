package com.huangshang.testapplication.view

import android.Manifest
import android.app.ActivityManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.widget.Toast
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import androidx.databinding.DataBindingUtil
import com.huangshang.testapplication.R
import com.huangshang.testapplication.databinding.ActivityMainBinding
import com.huangshang.testapplication.inteface.CallBackApk
import com.huangshang.testapplication.inteface.CheckSelfPermissionCall
import com.huangshang.testapplication.utils.Util
import com.huangshang.testapplication.viewmodel.ApkViewModel
import com.huangshang.testapplication.viewmodel.MyAccessibilityService
import java.io.File


class MainActivity : BaseActivity(),CallBackApk, CheckSelfPermissionCall {

    var mainDatabing:ActivityMainBinding?=null
    var apkViewModel:ApkViewModel?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainDatabing=DataBindingUtil.setContentView(this,R.layout.activity_main)
        apkViewModel=ApkViewModel(this,this)
        mainDatabing?.setApkViewModel(apkViewModel)
    }
    override fun onCheck() {
        onCheckPermission(
            Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission
                .ACCESS_FINE_LOCATION,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS,
            Manifest.permission.VIBRATE,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.REQUEST_INSTALL_PACKAGES,
            Manifest.permission.CAMERA)
    }

    override fun permissionSuccess() {
        super.permissionSuccess()
        apkViewModel?.saveApk()
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun suceess(path: String) {
        installAPK(path)
        jumpToSetting()
//        val apkFile = File(path)
//        if (!apkFile.exists()) {
//            return
//        }
//        val contentUri = FileProvider.getUriForFile(this, Util.getAppProcessName()+".fileprovider", apkFile)
//        val localIntent = Intent(Intent.ACTION_VIEW)
//        localIntent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
//        localIntent.setDataAndType(contentUri, "application/vnd.android.package-archive")
//        startActivity(localIntent)
//        var b = getPackageManager().canRequestPackageInstalls();
//        if (b) {
//            installAPK(path);//安装应用的逻辑(写自己的就可以)
//        } else {
//            //请求安装未知应用来源的权限
//            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.REQUEST_INSTALL_PACKAGES), 11);
//        }

    }

    override fun fail() {
        Toast.makeText(this, "请选择安装包！", Toast.LENGTH_SHORT).show()
    }




}
