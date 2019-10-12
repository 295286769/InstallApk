package com.huangshang.bluetoothlib.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class StudentInfoConverter {
    companion object{
        @TypeConverter
        fun revertString(list: MutableList<StudentInfo>):String{
            //List 转为 Json数组
            return Gson().toJson(list,object :TypeToken<MutableList<StudentInfo>>(){}.type)
        }
        @TypeConverter
        fun revertList(string: String): MutableList<StudentInfo>{
            return Gson().fromJson(string,object :TypeToken<MutableList<StudentInfo>>(){}.type)
        }
    }
}