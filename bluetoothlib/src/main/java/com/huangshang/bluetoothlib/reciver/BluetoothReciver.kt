package com.huangshang.bluetoothlib.reciver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.bluetooth.BluetoothDevice



class BluetoothReciver:BroadcastReceiver() {
    override fun onReceive(p0: Context?, intent: Intent?) {
        val action = intent?.getAction()
        // When discovery finds a device
        if (BluetoothDevice.ACTION_FOUND == action) {
            // Get the BluetoothDevice object from the Intent
            val device = intent?.getParcelableExtra<BluetoothDevice >(BluetoothDevice.EXTRA_DEVICE)
            // Add the name and address to an array adapter to show in a ListView

        }
    }
}