package com.huangshang.testapplication.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import java.util.*

class UserInfoBean : BaseObservable {
    constructor():super(){

    }
    constructor(name:String,age:Int){
        this.name=name
        this.age=age
    }

    var name=""
   @Bindable get() {
        return field
    }
    set(value) {
        field=value
        notifyPropertyChanged(BR.age)
    }
      var age=0
    @Bindable get() {
        return field
    }
    set(value) {
        field=value
        notifyPropertyChanged(BR.age)
    }
}