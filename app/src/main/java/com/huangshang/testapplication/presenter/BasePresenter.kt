package com.huangshang.testapplication.presenter

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner

class BasePresenter:IPresenter {
    override fun onCreate(owner: LifecycleOwner) {
    }

    override fun onDestroy(owner: LifecycleOwner) {
    }

    override fun onLifecycleChanged(owner: LifecycleOwner, event: Lifecycle.Event) {
    }

}