package com.huangshang.testapplication.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.huangshang.testapplication.R
import com.huangshang.testapplication.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment() {
    companion object {
        fun getBaseFragment(): Fragment {
            var fragment = HomeFragment()
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var homeBinding:FragmentHomeBinding=DataBindingUtil.inflate(inflater, R.layout.fragment_home,container,false)
        return homeBinding.root
    }
}