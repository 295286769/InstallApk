package com.huangshang.bluetoothlib.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.huangshang.common.network.HttpUtilRepertory
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class LoginViewModel : ViewModel {
    var mContext: Context? = null
    var httpUtil: HttpUtilRepertory? = null
    var liveData: MutableLiveData<String>? = null

    constructor(context: Context) : super() {
        mContext = context
        httpUtil = HttpUtilRepertory.getInteface()
        liveData = MutableLiveData<String>()
    }
    fun getMutableLiveData():MutableLiveData<String>?{

        return liveData
    }

    fun login() :MutableLiveData<String>? {
        liveData=httpUtil?.login("黄尚")
        return liveData
    }
}