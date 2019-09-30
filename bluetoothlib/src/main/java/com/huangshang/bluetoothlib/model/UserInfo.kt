package com.huangshang.bluetoothlib.model

import androidx.room.*
import java.util.*

@Entity(tableName = "UserInfo",indices = [Index(value= [""])])
class UserInfo {
    @PrimaryKey(autoGenerate = true)
     var id: Int = 0
    @ColumnInfo
     var userId=""
    @ColumnInfo
     var userName=""
    @ColumnInfo
    var date:Date?=null
    @Embedded
    var studentInfo:StudentInfo?=null
    @Embedded(prefix = "test")
    var test_studentInfo:StudentInfo?=null


}