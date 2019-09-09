package com.sharkgulf.checkandinstallapk.model

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.sharkgulf.checkandinstallapk.R
import java.lang.ref.WeakReference

class NotificationModel {
    private var mNotificationManager: NotificationManager? = null
    private var builder: NotificationCompat.Builder? = null
    private lateinit var mNtContext: WeakReference<Context>
    companion object{
        //下载apk通知id
        val NotificationID = 0x10000
    }
    constructor(context: Context){
        mNtContext= WeakReference<Context>(context)
        mNotificationManager = mNtContext.get()?.let { it.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager }
    }

    /**
     * id：渠道id
     * channelName：渠道名称
     */
    fun notifycation(id:String,channelName:String) {

        // 针对Android 8.0版本对于消息栏的限制，需要加入channel渠道这一概念
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {  //Android 8.0以上
            var mChannel = NotificationChannel(id, channelName, NotificationManager.IMPORTANCE_LOW)
            Log.i("DownAPKService", mChannel.toString())
            mNotificationManager?.createNotificationChannel(mChannel);
            builder = mNtContext.get()?.let { NotificationCompat.Builder(it,id)}
            builder?.apply {
                setSmallIcon(R.drawable.nav_icon_back)
                setLargeIcon(BitmapFactory.decodeResource(mNtContext.get()?.resources,R.drawable.nav_icon_back))
                setContentTitle(mNtContext.get()?.resources?.getString(R.string.checkapk_checkapp_name))
                setContentText("正在下载,请稍后...")
                setNumber(0)
                setChannelId(id)
                setAutoCancel(true)
            }
        } else {    //Android 8.0以下
            builder =mNtContext.get().let { NotificationCompat.Builder(it)}
            builder?.apply {
               setSmallIcon(R.drawable.nav_icon_back);
                setLargeIcon(BitmapFactory.decodeResource(mNtContext.get()?.resources,R.drawable.nav_icon_back))
                setContentTitle(mNtContext.get()?.resources?.getString(R.string.checkapk_checkapp_name));
                setContentText("正在下载,请稍后...");
                setNumber(0);
                setAutoCancel(true);
            }
        }
//        builder?.let {  it.setProgress(100,0,false)}
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            startForeground(NotificationID, builder?.build());
//        }
        notificationStart()
    }
    fun setProgress(progress: Int){
        builder?.apply {
            setProgress(100,progress,false)
//            setContentText(progress.toString()+"%")
        }
//        mNotificationManager?.let { it.notify(NotificationModel.NotificationID, builder?.build()) }
    }
    fun notificationStart(){
        mNotificationManager?.let { it.notify(NotificationID, builder?.build()) }
    }
    fun notificationCancel(){
        mNotificationManager?.let { it.cancel(NotificationID) }
    }

}