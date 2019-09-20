package com.sharkgulf.checkandinstallapk.utils

 class StringUtil {
     companion object{
         /**
          *  因为kotlin运行时还是被翻译成java字节码的,所以和.java中调用kotlin一样,在databinding中引用kotlin的对象声明和伴生对象也需要加上@JvmField和@JvmStatic
         注解才能将他们暴露为静态,参考官方文档_Object Expressions and Declarations
         这里只需要在方法上加上@JvmStatic注解,编译成功
          */
         @JvmStatic
        fun isEmpty(str: CharSequence?): Boolean {
            return str == null || str.length == 0
        }
    }

}