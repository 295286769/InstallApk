package com.huangshang.bluetoothlib.activity

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.huangshang.bluetoothlib.viewmodel.LoginViewModel
import com.huangshang.common.activity.CommonBaseActivity

class LoginActivity:CommonBaseActivity() {
    var  loginViewModel: LoginViewModel?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginViewModel=ViewModelProviders.of(this).get(LoginViewModel::class.java)
        loginViewModel?.getMutableLiveData()?.observe(this, object :Observer<String>{
            override fun onChanged(t: String?) {

            }

        })
    }
}