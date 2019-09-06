package com.sharkgulf.checkandinstallapk.utils

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Point
import android.os.Build
import android.util.DisplayMetrics
import android.view.WindowManager

class ScreenUtil {
    companion object{
        fun getScreenWidth(context: Context?): Int {
            return getScreenSize(context)[0]
        }

        fun getScreenHeight(context: Context?): Int {
            return getScreenSize(context)[1]
        }
        @SuppressLint("WrongConstant")
        private fun getScreenSize(context: Context?): IntArray {
            var windowManager: WindowManager?
            try {
                windowManager = context?.getSystemService("window") as WindowManager
            } catch (var6: Throwable) {
                windowManager = null
            }

            if (windowManager == null) {
                return intArrayOf(0, 0)
            } else {
                val display = windowManager.defaultDisplay
                if (Build.VERSION.SDK_INT < 13) {
                    val t1 = DisplayMetrics()
                    display.getMetrics(t1)
                    return intArrayOf(t1.widthPixels, t1.heightPixels)
                } else {
                    try {
                        val t = Point()
                        val method = display.javaClass.getMethod("getRealSize", *arrayOf<Class<*>>(Point::class.java))
                        method.isAccessible = true
                        method.invoke(display, *arrayOf<Any>(t))
                        return intArrayOf(t.x, t.y)
                    } catch (var5: Throwable) {
                        return intArrayOf(0, 0)
                    }

                }
            }
        }
        /**
         * @param context
         * @param dp
         * @return
         */
        fun dpToPx(context: Context?, dp: Float): Int {
            if (context == null) {
                return -1
            }
            val scale = context.resources.displayMetrics.density
            return (dp * scale + 0.5f).toInt()
        }

        /**
         * @param context
         * @param px
         * @return
         */
        fun pxToDp(context: Context?, px: Float): Int {
            if (context == null) {
                return -1
            }
            val scale = context.resources.displayMetrics.density
            return (px / scale + 0.5f).toInt()
        }
    }


}