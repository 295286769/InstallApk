package com.sharkgulf.checkandinstallapk.activity

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.sharkgulf.checkandinstallapk.model.PermissionSuccessEvent
import com.sharkgulf.checkandinstallapk.utils.PermissionUtil
import com.sharkgulf.checkandinstallapk.utils.StartActivityUtil
import org.greenrobot.eventbus.EventBus

open class BaseActivity: FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        PermissionUtil.onRequestPermissionsResult(this, requestCode, permissions, grantResults)
    }
}