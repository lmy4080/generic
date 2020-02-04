package com.lmy.generic.requests

import com.lmy.generic.requests.responses.SingApi
import com.lmy.generic.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceGenerator {
    private val retrofitBuilder = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var singApi = retrofitBuilder.create(SingApi::class.java)
}