package com.huangshang.testapplication.service

import android.content.ComponentName
import android.content.Context
import android.os.IBinder
import android.util.Log
import com.shark.sharkbleserver.SharkBleServer

class BluetoothBLEService : SharkBleServer {
    constructor(context: Context):super(context){

    }

    override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {
        super.onServiceConnected(name, binder)
        try {
            start()
        } catch (e: Exception) {
            Log.e("BaseActivity", e.toString())
        }

    }
}