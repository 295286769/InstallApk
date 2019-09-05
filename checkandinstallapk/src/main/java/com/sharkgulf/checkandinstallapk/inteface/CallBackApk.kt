package com.huangshang.testapplication.inteface

import java.security.cert.CertPath

interface CallBackApk {
    fun suceess(path: String)
    fun update(pregress: Int){}
    fun fail()
    fun start(){}
}