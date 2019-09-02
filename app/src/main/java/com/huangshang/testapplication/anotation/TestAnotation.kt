package com.huangshang.testapplication.anotation

import java.lang.annotation.ElementType
import java.lang.annotation.RetentionPolicy
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
 annotation class TestAnotation(val name:String,val age:Int)  {

}