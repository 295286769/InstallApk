package com.huangshang.bluetoothlib.inteface

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun onInser(t: T)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun onInsert(t: MutableList<T>)

    @Delete
    fun onDelete(t: T)
    @Delete
    fun onDeleteList(elements:MutableList<T>)

    @Delete
    fun onDeleteSome(vararg elements:T)

    @Update
    fun onUpdate(t:T)

    @Update
    fun onUpdate(t: MutableList<T>)
}