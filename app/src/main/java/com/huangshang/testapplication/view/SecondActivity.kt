package com.huangshang.testapplication.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.huangshang.testapplication.R
import com.huangshang.testapplication.databinding.ActivitySecondBinding

class SecondActivity: AppCompatActivity() {
    var databing:ActivitySecondBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databing=DataBindingUtil.setContentView(this, R.layout.activity_second)

    }
}