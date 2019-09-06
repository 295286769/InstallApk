package com.huangshang.testapplication.view.activity

import android.os.Bundle
import com.huangshang.testapplication.presenter.BasePresenter
import com.huangshang.testapplication.presenter.IPresenter
import com.sharkgulf.checkandinstallapk.activity.BaseActivity

class HomeActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var presenter: IPresenter = BasePresenter()
        getLifecycle().addObserver(presenter)

    }
}