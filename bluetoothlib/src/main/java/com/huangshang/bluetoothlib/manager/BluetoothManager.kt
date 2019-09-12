package com.huangshang.bluetoothlib.manager

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.Context
import androidx.core.app.ActivityCompat.startActivityForResult
import android.content.Intent
import android.content.IntentFilter
import android.os.Message
import android.widget.ArrayAdapter
import com.huangshang.bluetoothlib.reciver.BluetoothReciver
import java.io.IOException
import java.util.*


class BluetoothManager {
    var bluetoothAdapter:BluetoothAdapter?=null
    var mActivity: Activity?=null
    var bluetoothReciver:BluetoothReciver?=null
    companion object{
        //开启蓝牙
        val REQUEST_ENABLE_BT=100
    }

    constructor(activity: Activity){
        mActivity=activity
        initBluetoot()
    }
    fun initBluetoot(){
        bluetoothAdapter= BluetoothAdapter.getDefaultAdapter()
        bluetoothReciver=BluetoothReciver()


    }

    /**
     * 开启蓝牙
     */
    fun openBluetoot(){
        bluetoothAdapter?.let {
            if(it.isEnabled){
                val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                mActivity?.startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT)

            }
        }
    }
    /**
     * 将已配对的设备添加到列表中
      */

    fun getBluetoothDevices(){
        var listDevice= arrayListOf<BluetoothDevice>()
        bluetoothAdapter?.let {
            val pairedDevices =it.bondedDevices
           var arrayAdapter=ArrayAdapter<String>(mActivity,0)
            for (device in pairedDevices) {
                arrayAdapter.add(device.name + "\n" + device.address)
                listDevice.add(device)
            }
        }
    }

    /**
     * 扫描当前的蓝牙设备
     */
    fun searchBluetoothDevices(){
        bluetoothAdapter?.let {
            it.startDiscovery()
        }
    }

    /**
     * 停止搜索
     */
    fun cancel(){
        bluetoothAdapter?.let {
            it.cancelDiscovery()
        }
    }

    /**
     * 匹配建立连接
     */
    fun connectSocketToServiceRecord(bluetoothDevice:BluetoothDevice){
        cancel()
        Thread{
            // 蓝牙串口服务对应的UUID。如使用的是其它蓝牙服务，需更改下面的字符串
            val MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
            var bluetoothSocket:BluetoothSocket?=null
            try {
                bluetoothSocket=bluetoothDevice.createRfcommSocketToServiceRecord(MY_UUID)
                bluetoothSocket?.connect()
            } catch (e: Exception) {
                bluetoothSocket?.close();
                return@Thread
            }

        }.start()


    }

    /**
     * 数据传输线程
     */
    fun readWriteData(bluetoothSocket:BluetoothSocket) {

        bluetoothSocket?.let {
            var inputStream = it.inputStream
            var outputStream = it.outputStream
            val buffer = ByteArray(256)
            var bytes = 0
            while (true) {
                synchronized(this) {
                    try {
                        bytes = inputStream.read(buffer)

                        var msg = Message.obtain()
                        msg.what = 0
                    } catch (e: IOException) {
                    }
                }
            }
            outputStream.write(buffer);
        }
        fun register() {
            var intentFilter = IntentFilter(BluetoothDevice.ACTION_FOUND)
            mActivity?.registerReceiver(bluetoothReciver, intentFilter)
        }

        fun unRegister() {
            mActivity?.let {
                it?.unregisterReceiver(bluetoothReciver)
            }
        }
    }
}