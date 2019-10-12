package com.huangshang.common.utils

import android.app.ActivityManager
import android.content.Context
import androidx.fragment.app.FragmentActivity
import com.huangshang.common.application.CommonApplication
import okhttp3.OkHttpClient
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*



class StringUtil {
    companion object{
        var yyyy_MM_dd="yyyy-MM-dd"
    var HH_mm="HH:mm"
    var HH_mm_ss="HH:mm:ss"
    var yyyy_MM_dd_HH_mm_ss="yyyy-MM-dd HH:mm:ss"
    var yyyyMMdd="yyyy年MM月dd日"
    var HHmm="HH时mm分"
    var HHmmss="HH时mm分ss秒"
    var yyyyMMdd_HHmmss="yyyy年MM月dd日 HH时mm分ss秒"
    /**
     * 获取当前应用程序的包名
     * @return 返回包名
     */
    fun getAppProcessName(context: Context?):String{
        //当前应用pid
        var pid = android.os.Process.myPid();
        //任务管理类
        var manager =  context?.getSystemService(FragmentActivity.ACTIVITY_SERVICE) as ActivityManager;
        //遍历所有应用
        var infos = manager?.getRunningAppProcesses();
        infos?.let {
            for (  item in it) {
                if (item.pid == pid)//得到当前应用
                    return item.processName;//返回包名
            }
        }

        return "";
    }
    /**
     *   date转String
    formatType格式为yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
    data Date类型的时间
     */

    fun   dateToString(data: Date?, formatType:String): String {
        return  data?.let { SimpleDateFormat(formatType).format(data) }?:""
    }

    /**
     * long 转String
     *  //currentTime要转换的long类型的时间
    formatType要转换的string类型的时间格式
     */

    @Throws(ParseException ::class )
    fun  longToString( currentTime:Long,formatType:String) :String {
        var strTime: String =try {
            var  date :Date?= longToDate(currentTime, formatType) // long类型转成Date类型
            dateToString(date, formatType) // date类型转成String
        } catch (e: Exception) {
            ""
        }
        return strTime
    }

    /**String转Date
     *   // strTime要转换的string类型的时间，formatType要转换的格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日
    // HH时mm分ss秒，
    // strTime的时间格式必须要与formatType的时间格式相同
     */

    @Throws(ParseException::class)
    fun  stringToDate( strTime:String,  formatType:String):Date? {
        var date: Date? =  try {
            var formatter =  SimpleDateFormat(formatType);
            formatter.parse(strTime);
        } catch (e: Exception) {
            null
        }
        return date
    }

    /**
     * long转Date
     */
    @Throws(ParseException::class)
    fun  longToDate( currentTime:Long,  formatType:String):Date? {
        var date: Date? = try {
            var dateOld =  Date(currentTime); // 根据long类型的毫秒数生命一个date类型的时间
            var sDateTime = dateToString(dateOld, formatType); // 把date类型的时间转换为string
            stringToDate(sDateTime, formatType); // 把String类型转换为Date类型
        } catch (e: Exception) {
            null
        }
        return date
    }

    /**
     * String转long
     */
    @Throws(ParseException::class)
    fun  stringToLong( strTime:String,  formatType:String):Long {
        var currentTime:Long = try {
            var date = stringToDate(strTime, formatType); // String类型转成date类型
            date?.let { dateToLong(date) }?:0
        } catch (e: Exception) {
            0
        }
        return currentTime
    }
    // date要转换的date类型的时间
    fun dateToLong(date: Date?): Long {
        return date?.time?:0
    }
}
}