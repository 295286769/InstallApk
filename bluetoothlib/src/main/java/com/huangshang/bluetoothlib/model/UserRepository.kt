package com.huangshang.bluetoothlib.model

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.huangshang.bluetoothlib.inteface.UserInfoDao

class UserRepository {
    var userInfoDao: UserInfoDao?=null
    var data:LiveData<UserInfo>?=null
    constructor(context: Context){
        userInfoDao=BloothDataBase.getInteface(context)?.getUserInfoDao()
        data=MutableLiveData<UserInfo>()
    }
    fun getUserInfo():LiveData<UserInfo>?{


        data=userInfoDao?.queryUser("123","黄")
        return data
    }
}