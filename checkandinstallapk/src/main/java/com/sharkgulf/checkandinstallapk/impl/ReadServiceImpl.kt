package com.sharkgulf.checkandinstallapk.impl

import androidx.fragment.app.Fragment
import com.huangshang.componentservice.inteface.ReadBookService
import com.sharkgulf.checkandinstallapk.fragment.CheckApkFragment

class ReadServiceImpl:ReadBookService {
    override fun getCheckApkFragment(): Fragment {
        return CheckApkFragment()
    }
}