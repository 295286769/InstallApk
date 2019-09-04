package com.huangshang.testapplication.inteface

import java.security.cert.CertPath

interface CallBackApk {
    fun suceess(path: String)
    fun fail()
}