package com.huangshang.repository.inteface

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.PUT
import java.util.*

interface RettrofitInteface {
    @GET("")
    fun  getToken() : Call<String>
    @PUT("")
    fun login(@Field("userName") userName:String):Observable<String>
}