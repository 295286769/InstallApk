package com.huangshang.common.network

import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class HttpUtilRepertory {

    var iUserBiz:RettrofitInteface?=null
    var liveData:MutableLiveData<String>?=null
    companion object{
        var httpUtil:HttpUtilRepertory?=null
        fun getInteface():HttpUtilRepertory?{
            if(httpUtil==null){
                httpUtil= HttpUtilRepertory()
            }
            return httpUtil
        }

    }
    constructor(){
        liveData= MutableLiveData()
        var retrofit=Retrofit.Builder().baseUrl("")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        iUserBiz=retrofit.create(RettrofitInteface::class.java)
    }
    fun getToken(){
        iUserBiz?.getToken()?.enqueue(object :Callback<String>{
            override fun onFailure(call: Call<String>, t: Throwable) {
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
            }

        })
    }
    fun login(userName:String): MutableLiveData<String>?{

        iUserBiz?.login(userName)?.subscribeOn(Schedulers.io())?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object :Observer<String>{
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: String) {
                    liveData?.value=t
                }

                override fun onError(e: Throwable) {
                    liveData?.value=e.message.toString()
                }

            })
        return liveData
    }
}