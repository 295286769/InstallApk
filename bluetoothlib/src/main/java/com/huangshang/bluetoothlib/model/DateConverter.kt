package com.huangshang.bluetoothlib.model

import androidx.room.TypeConverter
import com.huangshang.common.utils.StringUtil
import java.util.*


class DateConverter {
    companion object{
        @TypeConverter
        fun revertDate(value: Long):Date{
            return Date(value)
        }
        @TypeConverter
        fun revertLong(value: Date):Long{
            return value.time
        }
        @TypeConverter
        fun  revertString(value: Long,formatType:String):String{
            return StringUtil.longToString(value,formatType)
        }
    }

}