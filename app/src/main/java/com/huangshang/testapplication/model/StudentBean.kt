package com.huangshang.testapplication.model

import androidx.databinding.*
import androidx.room.Entity

@Entity
class StudentBean {
    var sex:ObservableField<String>?=ObservableField<String>()
    var mapSex:ObservableMap<String,String>?=ObservableArrayMap<String,String>()
    var listSex:ObservableList<UserInfoBean>?=ObservableArrayList<UserInfoBean>()

}