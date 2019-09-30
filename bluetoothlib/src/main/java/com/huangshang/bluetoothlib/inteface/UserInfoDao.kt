package com.huangshang.bluetoothlib.inteface

import androidx.room.*
import com.huangshang.bluetoothlib.model.UserInfo

@Dao
interface UserInfoDao: BaseDao<UserInfo> {
    @Query("select * from UserInfo where userId = :userId or :fisrtName")
    fun queryUser(userId:String,fisrtName:String): UserInfo
    @Query("select * from UserInfo")
    fun queryUsers():MutableList<UserInfo>
    @Query("select * from UserInfo  order by userId Desc")
    fun queryUsersDesc():MutableList<UserInfo>
    @Query("select * from UserInfo where id between :start and :end")
    fun queryId(start:Long,end:Long):MutableList<UserInfo>
    @Query("select * from UserInfo where sex like :boy or ")
    fun querySex(boy:String,girl:String):MutableList<UserInfo>
}
