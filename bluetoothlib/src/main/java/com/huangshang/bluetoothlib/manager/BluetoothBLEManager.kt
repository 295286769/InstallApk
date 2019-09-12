package com.huangshang.bluetoothlib.manager

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.content.Intent

class BluetoothBLEManager {
    var adapter:BluetoothAdapter?=null
    var mActivity:Activity?=null
    constructor(activity: Activity){
        mActivity=activity
        adapter=BluetoothAdapter.getDefaultAdapter()
    }
    fun isEnable(){
        adapter?.let {
            if(!it.isEnabled){
                val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                mActivity?.startActivityForResult(enableBtIntent, BluetoothManager.REQUEST_ENABLE_BT)
            }
        }
    }
}