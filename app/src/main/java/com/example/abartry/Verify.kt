package com.example.abartry

import android.util.Log
import com.example.abartry.RetrofitStuffs.MyApiService
import com.example.abartry.RetrofitStuffs.ServiceBuilder
import com.example.abartry.data.otpVerify.OtpVerifyRespone
import com.example.abartry.data.otpVerify.VerifyParameters
import com.example.abartry.data.subscription.StatusResponse
import com.example.abartry.data.subscription.VerifyParametersStatus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

val verifyParameters = VerifyParameters(
    appId = "APP_118838",
    password = "cab1c32cdbe7b1489ec6048e33296a43",
    referenceNo = "",
    otp = ""
)
fun verify(otp: String) {
    verifyParameters.otp = otp
    Log.d("MyActivity", "${verifyParameters}")
    val destinationService = ServiceBuilder.buildService(MyApiService::class.java)
    val requestCall = destinationService.verifyOtp(verifyParameters)

    requestCall.enqueue(object : Callback<OtpVerifyRespone> {
        override fun onResponse(
            call: Call<OtpVerifyRespone>,
            response: Response<OtpVerifyRespone>
        ) {
            if (response.isSuccessful) {
                val apiResponse = response.body()
                Log.d("MyActivity", "OTP verified successfully: $apiResponse")
            } else {
                // Handle unsuccessful response
                Log.e("MyActivity", "Failed to verify OTP: ${response.errorBody()?.string()}")
            }
        }
        override fun onFailure(call: Call<OtpVerifyRespone>, t: Throwable) {
            // Handle failure
            Log.e("MyActivity", "Network error: ${t.message}")
        }
    })
}

fun verifyStatus() {
    val verifyParametersStatus = VerifyParametersStatus(
        appId = "APP_118838",
        password = "cab1c32cdbe7b1489ec6048e33296a43",
        mobile = "8801629864228"
    )
    val destinationService = ServiceBuilder.buildService(MyApiService::class.java)
    val requestCall = destinationService.verifySubscription(verifyParametersStatus)

    requestCall.enqueue(object : Callback<StatusResponse> {
        override fun onResponse(call: Call<StatusResponse>, response: Response<StatusResponse>) {
            if (response.isSuccessful) {
                val apiResponse = response.body()
                Log.d("MyActivity", "Subscription Status verified successfully: $apiResponse")
            } else {
                // Handle unsuccessful response
                Log.e("MyActivity", "Failed to verify Subscription Status: ${response.errorBody()?.string()}")
            }
        }
        override fun onFailure(call: Call<StatusResponse>, t: Throwable) {
            // Handle failure
            Log.e("MyActivity", "Network error: ${t.message}")
        }
    })
}