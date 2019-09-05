package com.sharkgulf.checkandinstallapk.inteface

interface IFileModel {
   public fun returnApkPath(path:String)
   public fun update(pregress: Int){}
   public fun start(){}
   public fun fail()
}