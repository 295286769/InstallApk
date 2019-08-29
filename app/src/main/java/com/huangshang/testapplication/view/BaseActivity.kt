package com.huangshang.testapplication.view

import android.Manifest
import android.app.ActivityManager
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.FragmentActivity
import com.huangshang.testapplication.constant.PermisionRequstCode
import com.huangshang.testapplication.utils.Util
import java.io.File
import androidx.core.content.ContextCompat.startActivity



open class BaseActivity:FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    open fun permissionSuccess(){

    }
    open fun onCheckPermission(vararg permissions: String) {
        //检查权限（NEED_PERMISSION）是否被授权 PackageManager.PERMISSION_GRANTED表示同意授权
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) {
            //用户已经拒绝过一次，再次弹出权限申请对话框需要给用户一个解释
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission
                    .WRITE_EXTERNAL_STORAGE)) {
                Toast.makeText(this, "请开通相关权限，否则无法正常使用本应用！", Toast.LENGTH_SHORT).show();
            }
            //申请权限
            ActivityCompat.requestPermissions(this, permissions, PermisionRequstCode.permisionRequstCode);

        } else {
            permissionSuccess()
            Toast.makeText(this, "授权成功！", Toast.LENGTH_SHORT).show();
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode==PermisionRequstCode.permisionRequstCode){
            for (i in permissions.indices){
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {//选择了“始终允许”
                    if(i==permissions.size-1){
                        permissionSuccess()
                    }
                }
            }

        }else if (requestCode==11){
            if(grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){


            }
        }
    }
    open fun installAPK(path: String) {

        val apkFile = File(path)
        if (!apkFile.exists()) {
            return
        }
        val intent = Intent(Intent.ACTION_VIEW)
        //      安装完成后，启动app（源码中少了这句话）

        if (null != apkFile) {
            try {
                //兼容7.0
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//参数1 上下文, 参数2 Provider主机地址 和配置文件中保持一致   参数3  共享的文件
                    val contentUri = FileProvider.getUriForFile(
                        this,
                        Util.getAppProcessName() + ".fileprovider",
                        apkFile
                    )
                    //添加这一句表示对目标应用临时授权该Uri所代表的文件
                    intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
                    intent.setDataAndType(contentUri, "application/vnd.android.package-archive")
                    //兼容8.0
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        val hasInstallPermission = packageManager.canRequestPackageInstalls()
                        if (!hasInstallPermission) {
                            startInstallPermissionSettingActivity()
                            return
                        }
                    }
                } else {
                    intent.setDataAndType(
                        Uri.fromFile(apkFile),
                        "application/vnd.android.package-archive"
                    )
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                }
                if (packageManager.queryIntentActivities(intent, 0).size > 0) {
                    startActivity(intent)
                }
            } catch (e: Throwable) {
                e.printStackTrace()
            }

        }
    }
    private fun startInstallPermissionSettingActivity() {
        //注意这个是8.0新API
        val packageURI = Uri.parse("package:$packageName")
        val intent = Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, packageURI)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }
    /**
     * 跳转到系统设置：开启辅助服务
     */
    open fun jumpToSetting() {
        try {
            val intent = Intent()
            intent.putExtra("action", "action_start_accessibility_setting")
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        } catch (e: Throwable) {//若出现异常，则说明该手机设置被厂商篡改了,需要适配
            try {
                val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            } catch (e2: Throwable) {
                Log.e("TAG", "jumpToSetting: " + e2.message)
            }

        }

    }

}