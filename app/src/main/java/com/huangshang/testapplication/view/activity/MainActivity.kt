package com.huangshang.testapplication.view.activity

import android.Manifest
import android.os.Bundle
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import com.huangshang.testapplication.R
import com.huangshang.testapplication.databinding.ActivityMainBinding
import com.huangshang.testapplication.inteface.CallBackApk
import com.huangshang.testapplication.inteface.CheckSelfPermissionCall
import com.huangshang.testapplication.model.StudentBean
import com.huangshang.testapplication.model.UserInfoBean
import com.huangshang.testapplication.view.BaseActivity
import com.huangshang.testapplication.viewmodel.ApkViewModel


class MainActivity : BaseActivity(),CallBackApk, CheckSelfPermissionCall {

    var mainDatabing:ActivityMainBinding?=null
    var apkViewModel:ApkViewModel?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainDatabing=DataBindingUtil.setContentView(this,R.layout.activity_main)
        apkViewModel=ApkViewModel(this,this)
        mainDatabing?.setApkViewModel(apkViewModel)
        var student:StudentBean= StudentBean()
        student.mapSex?.put("aaa","皇上")
        student.sex?.set("男")
        student.listSex?.add(UserInfoBean("太监",12))
        mainDatabing?.setStudentBean(student)
        mainDatabing?.setKey("aaa")
        mainDatabing?.setIndex(0)
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
