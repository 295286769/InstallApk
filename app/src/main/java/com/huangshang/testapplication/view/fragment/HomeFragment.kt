package com.huangshang.testapplication.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.Nullable
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.huangshang.testapplication.R
import com.huangshang.testapplication.anotation.TestAnotation
import com.huangshang.testapplication.databinding.FragmentHomeBinding
import com.huangshang.testapplication.model.UserProfileViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*





class HomeFragment : BaseFragment() {
    var homeBinding:FragmentHomeBinding?=null
    @TestAnotation("dfd",20)
//    var btn_one:Button?=null
    var userModel:UserProfileViewModel?=null
    var userId:String?=""
    companion object {
        fun getBaseFragment(): Fragment {
            var fragment = HomeFragment()
            return fragment
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        userId=arguments?.getString("")
        userModel= ViewModelProviders.of(this).get(UserProfileViewModel::class.java)
        userModel?.setUserName(userId)
        userModel?.getUserName()?.observe(this,object :Observer<String>{
            override fun onChanged(t: String?) {
                btn_one.setText(t)
            }
        })
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         homeBinding=DataBindingUtil.inflate(inflater, R.layout.fragment_home,container,false)
        homeBinding?.setUserViewModel(userModel)
        homeBinding?.setLifecycleOwner(this )
        return homeBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userId=edt_text.getText().toString()
        var runnable= Runnable {  }
    }
}