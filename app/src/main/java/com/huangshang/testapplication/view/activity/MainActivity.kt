package com.huangshang.testapplication.view.activity

import android.Manifest
import android.os.Bundle
import android.os.Build
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import com.huangshang.checkandinstallapk.inteface.CheckSelfPermissionCall
import com.huangshang.testapplication.R
import com.huangshang.testapplication.databinding.ActivityMainBinding
import com.huangshang.testapplication.inteface.CallBackApk
import com.huangshang.testapplication.model.StudentBean
import com.huangshang.testapplication.model.UserInfoBean
import com.huangshang.testapplication.view.BaseActivity
import com.sharkgulf.checkandinstallapk.widget.DialogUtil
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity(),CallBackApk, CheckSelfPermissionCall {

    var mainDatabing:ActivityMainBinding?=null
    var huider:DialogUtil?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainDatabing=DataBindingUtil.setContentView(this,R.layout.activity_main)
//        apkViewModel=ApkViewModel(this,this)
//        mainDatabing?.setApkViewModel(apkViewModel)
        var student:StudentBean= StudentBean()
        student.mapSex?.put("aaa","皇上")
        student.sex?.set("男")
        student.listSex?.add(UserInfoBean("太监",12))
        mainDatabing?.setStudentBean(student)
        mainDatabing?.setKey("aaa")
        mainDatabing?.setIndex(0)
        huider=DialogUtil.Companion.DialogBuider(this).setTitle("更新提示").setContentText("您有新版本是否更新").setLeftBtnText("取消").setRightBtnText("确定").buider()
        huider?.setCheckSelfPermissionCall(this)
        btn_install.setOnClickListener(object :View.OnClickListener{
            override fun onClick(p0: View?) {
                huider?.showDialog()
            }

        })

    }

    /**
     * 调用权限
     */
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

    /**
     * 权限全部选择成功
     */
    override fun permissionSuccess() {
        super.permissionSuccess()
        huider?.updateApk()
    }

    /**
     * 下载成功
     */
    @RequiresApi(Build.VERSION_CODES.O)
    override fun suceess(path: String) {
        installAPK(path)
        jumpToSetting()
    }

    /**
     * 下载失败
     */
    override fun fail() {
        Toast.makeText(this, "请选择安装包！", Toast.LENGTH_SHORT).show()
    }




}
