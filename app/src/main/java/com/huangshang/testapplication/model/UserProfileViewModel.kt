package com.huangshang.testapplication.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserProfileViewModel :ViewModel() {
    var name=MutableLiveData<String>()
    fun setUserName(name:String?){
        this.name.setValue(name)
    }
   fun getUserName():MutableLiveData<String>?{
       return name
   }
}