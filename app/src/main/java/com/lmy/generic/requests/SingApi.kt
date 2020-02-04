package com.lmy.generic.requests.responses

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface SingApi {

    @GET("/karaoke/singer/{singer}/kumyoung.json")
    fun getSings(
        @Path("singer") singer: String
    ): Call<List<SingResponse>>
}