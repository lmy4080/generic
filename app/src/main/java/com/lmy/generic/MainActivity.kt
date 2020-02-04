package com.lmy.generic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.lmy.generic.requests.ServiceGenerator
import com.lmy.generic.requests.responses.ApiResponse
import com.lmy.generic.requests.responses.SingResponse
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setListeners()
    }

    private fun setListeners() {
        btn_search.setOnClickListener {
            val singerName = et_singer.text.toString()

            CoroutineScope(Dispatchers.IO).launch {
                ServiceGenerator.singApi.getSings(singerName).enqueue(object : Callback<List<SingResponse>> {
                    override fun onFailure(call: Call<List<SingResponse>>?, t: Throwable?) {
                        var apiResponse = ApiResponse<Throwable>().create(t!!)
                        Log.d("Response", (apiResponse as ApiResponse.ApiErrorResponse).errorMessage)
                    }
                    override fun onResponse(call: Call<List<SingResponse>>, response: Response<List<SingResponse>>) {
                        var apiResponse = ApiResponse<List<SingResponse>>().create(response)
                        Log.d("Response", (apiResponse as ApiResponse.ApiSuccessResponse).body.toString())
                    }
                })
            }
        }
    }
}
