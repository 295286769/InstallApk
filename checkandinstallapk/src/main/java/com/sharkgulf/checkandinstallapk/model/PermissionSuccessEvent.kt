package com.sharkgulf.checkandinstallapk.model

class PermissionSuccessEvent(apkPath:String) {
    var apkPath=apkPath
    get() = field
    set(value) {
        field=value
    }
}