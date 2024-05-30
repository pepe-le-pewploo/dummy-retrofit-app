package com.example.abartry.RetrofitStuffs

import com.example.abartry.data.ApiResponse
import com.example.abartry.data.OtpVerifyRespone
import com.example.abartry.data.RequestParameters
import com.example.abartry.data.VerifyParameters
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface MyApiService {
    @POST("subscription/otp/request")
    fun requestOtp(@Body requestParameters: RequestParameters): Call<ApiResponse>

    @POST("subscription/otp/verify")
    fun verifyOtp(@Body verifyParameters: VerifyParameters): Call<OtpVerifyRespone>
}
