package com.example.abartry.RetrofitStuffs

import com.example.abartry.data.ApiResponse
import com.example.abartry.data.RequestParameters
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface MyApiService {
    @POST("nazmul/subscription/otp/request")
    fun requestOtp(@Body requestParameters: RequestParameters): Call<ApiResponse>
}
