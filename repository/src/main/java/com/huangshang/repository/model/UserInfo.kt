package com.huangshang.bluetoothlib.model

import androidx.room.*
import com.huangshang.repository.model.StudentInfo
import java.util.*

@Entity(tableName = "UserInfo")
class UserInfo {
    @PrimaryKey(autoGenerate = true)
     var id: Int = 0
    @ColumnInfo
     var userId=""
    @ColumnInfo
     var userName=""
//    @TypeConverters(DateConverter::class)
//    var date:Date?=null
//    @TypeConverters(StudentInfoConverter::class)
//    var studentInfos:MutableList<StudentInfo>?=null
    @Embedded(prefix = "fj")
    var studentInfo: StudentInfo?=null
    @Embedded(prefix = "sh")
    var test_studentInfo:StudentInfo?=null



}