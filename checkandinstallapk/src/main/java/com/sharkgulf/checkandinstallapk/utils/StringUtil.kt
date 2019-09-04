package com.sharkgulf.checkandinstallapk.utils

 class StringUtil {
     companion object{
        fun isEmpty(str: CharSequence?): Boolean {
            return str == null || str.length == 0
        }
    }

}