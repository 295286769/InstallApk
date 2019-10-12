package com.huangshang.common.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit

class RetrofitControll {
    private var retrofit:Retrofit?=null
    private constructor(){
        var httpClient:OkHttpClient?=OkHttpClient.Builder().build()
        retrofit=Retrofit.Builder().baseUrl("").client(httpClient).build()
    }

    companion object{

        private var retrofitControll:RetrofitControll?=null
        fun inintRetrofit():RetrofitControll?{
            if(retrofitControll==null){
                synchronized(RetrofitControll::class.java){
                    if(retrofitControll==null){
                        retrofitControll=RetrofitControll()
                    }
                }
            }
            return retrofitControll
        }

    }
}