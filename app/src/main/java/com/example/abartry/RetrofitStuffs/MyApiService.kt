package com.example.abartry.RetrofitStuffs

import com.example.abartry.data.ApiResponse
import retrofit2.Call
import retrofit2.http.POST

interface MyApiService {
    @POST("subscription/otp/request")
    fun requestOtp(): Call<ApiResponse>
}
