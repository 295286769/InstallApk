package com.huangshang.bluetoothlib.model

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.huangshang.bluetoothlib.inteface.UserInfoDao

class UserRepository {
    var userInfoDao: UserInfoDao?=null
    var data:MutableLiveData<UserInfo>?=null
    constructor(context: Context){
        userInfoDao=BloothDataBase.getInteface(context)?.getUserInfoDao()
        data=MutableLiveData<UserInfo>()
    }
    fun getUserInfo():LiveData<UserInfo>?{
        var userInfo=userInfoDao?.queryUser("123")
        data?.value=userInfo
        return data
    }
}