package com.sharkgulf.checkandinstallapk.activity

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.sharkgulf.checkandinstallapk.model.PermissionSuccessEvent
import com.sharkgulf.checkandinstallapk.utils.PermissionUtil
import com.sharkgulf.checkandinstallapk.utils.StartActivityUtil

open class BaseActivity: FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        EventBus.getDefault().register(this)
    }

//    /**
//     * 接收后天下载apk完成后的操作
//     */
//    public fun onEventMainThrend(permissionSuccessEvent: PermissionSuccessEvent){
//        //安装apk
//        permissionSuccessEvent.let {  StartActivityUtil.installAPK(this,permissionSuccessEvent.apkPath)}
//    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        PermissionUtil.onRequestPermissionsResult(this, requestCode, permissions, grantResults)
    }
}