package com.huangshang.testapplication.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.huangshang.testapplication.R
import com.huangshang.testapplication.databinding.ActivityMainBinding
import com.sharkgulf.checkandinstallapk.activity.BaseActivity
import com.sharkgulf.checkandinstallapk.inteface.DialogButtonLeftInterface
import com.sharkgulf.checkandinstallapk.inteface.DialogButtonRightInterface
import com.sharkgulf.checkandinstallapk.model.PermissionSuccessEvent
import com.sharkgulf.checkandinstallapk.utils.PermissionUtil
import com.sharkgulf.checkandinstallapk.utils.StartActivityUtil
import com.sharkgulf.checkandinstallapk.utils.ToasterManager
import com.sharkgulf.checkandinstallapk.widget.DialogUtil
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe


class MainActivity : BaseActivity() {

    var mainDatabing:ActivityMainBinding?=null
    var dialoghuider:DialogUtil?=null
    val permissions = arrayOf(
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
        android.Manifest
            .permission.READ_CONTACTS,
        android.Manifest.permission.CALL_PHONE
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainDatabing=DataBindingUtil.setContentView(this,R.layout.activity_main)
        EventBus.getDefault().register(this)
        dialoghuider=DialogUtil.Companion.DialogBuider(this).setTitle("更新提示").setContentText("您有新版本是否更新")
            .setLeftBtnText("取消",object :DialogButtonLeftInterface(){

                override fun onComfireClick() {
                }
            }).setRightBtnText("确定",object:DialogButtonRightInterface(){
                override fun onComfireClick() {
                    onCheckpPrmissions()
                }

            }).buider()
        btn_install.setOnClickListener {
            dialoghuider?.let { it.showDialog() }
        }
    }
//    /**
//     * 接收后天下载apk完成后的操作
//     */
//    public fun onEventMainThrend(permissionSuccessEvent: PermissionSuccessEvent){
//        //安装apk
//        permissionSuccessEvent.let {  StartActivityUtil.installAPK(this,permissionSuccessEvent.apkPath)}
//    }

    /**
     * 调用权限
     */
     fun onCheckpPrmissions() {

        PermissionUtil.requestPermisions(this, PermissionUtil.PERMISSIONREQUESTCODE, permissions, object : PermissionUtil.RequestPermissionListener {

            override fun onRequestPermissionSuccess() {//权限通过
                permissionSuccess()
            }

            override fun onRequestPermissionFail(grantResults: IntArray) {
                ToasterManager.showToast("请同意相关权限")
            }
        })

    }
    /**
     * 权限全部选择成功
     */
     fun permissionSuccess() {
        /**
         * 启动service下载apk
         */
        StartActivityUtil.startUpdateApkService(this)
    }
    /**
         * 接收后天下载apk完成后的操作
         */
    @Subscribe
     fun onEventMainThrend(permissionSuccessEvent: PermissionSuccessEvent){
        //安装apk
        permissionSuccessEvent.let {  StartActivityUtil.installAPK(this,permissionSuccessEvent.apkPath)}
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==StartActivityUtil.ACTIVITYREQUSETCODE){//权限设置页面返回

        }else if (requestCode==StartActivityUtil.PERMISSIONREQUSETCODE){//允许未知来源权限
                    StartActivityUtil.startInstallPermissionSettingActivity(this)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

}
