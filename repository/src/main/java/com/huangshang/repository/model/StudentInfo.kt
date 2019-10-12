package com.huangshang.repository.model

import androidx.annotation.ColorLong
import androidx.room.ColumnInfo
import androidx.room.Entity

class StudentInfo {
    @ColumnInfo(name="name")
    var name:String=""
    @ColumnInfo(name="sex")
    var sex:String=""
}