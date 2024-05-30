package com.example.abartry

import android.util.Log
import com.example.abartry.RetrofitStuffs.MyApiService
import com.example.abartry.RetrofitStuffs.ServiceBuilder
import com.example.abartry.data.ApiResponse
import com.example.abartry.data.RequestParameters
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun submit() {
    val requestParameters = RequestParameters(
        appId = "APP_118838",
        password = "cab1c32cdbe7b1489ec6048e33296a43",
        mobile = "8801629864228"
    )


    val destinationService = ServiceBuilder.buildService(MyApiService::class.java)
    val requestCall = destinationService.requestOtp(requestParameters)

    requestCall.enqueue(object : Callback<ApiResponse> {
        override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
            if (response.isSuccessful) {
                val apiResponse = response.body()
                if (apiResponse != null) {
                    verifyParameters.referenceNo = apiResponse.referenceNo
                }
                Log.d("MyActivity", "OTP sent successfully: $apiResponse")
            } else {
                // Handle unsuccessful response
                Log.e("MyActivity", "Failed to send OTP: ${response.errorBody()?.string()}")
            }
        }
        override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
            // Handle failure
            Log.e("MyActivity", "Network error: ${t.message}")
        }
    })
}