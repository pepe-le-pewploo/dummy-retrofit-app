package com.example.abartry

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.abartry.RetrofitStuffs.MyApiService
import com.example.abartry.RetrofitStuffs.ServiceBuilder
import com.example.abartry.data.ApiResponse
import com.example.abartry.ui.theme.AbarTryTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AbarTryTheme {
                HomeScreen();
            }
        }
    }
}

@Composable
fun HomeScreen() {
    var value by rememberSaveable {
        mutableStateOf("");
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(value = value, onValueChange = {value = it},
            colors = OutlinedTextFieldDefaults.colors(Color.Black)
            )
        Button(onClick = {
            submit()
        }) {
            Text(text = "Submit")
        }
    }
}

fun submit() {
    val destinationService = ServiceBuilder.buildService(MyApiService::class.java)
    val requestCall = destinationService.requestOtp()

    requestCall.enqueue(object : Callback<ApiResponse> {
        override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
            if (response.isSuccessful) {
                val apiResponse = response.body()
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AbarTryTheme {
        HomeScreen()
    }
}