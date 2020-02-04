package com.lmy.generic.requests.responses

import android.util.Log
import retrofit2.Response
import java.io.IOException

/**
 * Generic class for handling responses from Retrofit
 * @param <T>
</T> */
open class ApiResponse<T> {

    fun create(error: Throwable): ApiResponse<T> {
        Log.e("Response", error.message)
        val errorMsg = if (error.message == "") error.message else "Unknown error\nCheck network connection"
        return ApiErrorResponse(errorMsg!!)
    }

    fun create(response: Response<T>): ApiResponse<T> {

        if (response.isSuccessful) {
            val body = response.body()

            return if (body == null || response.code() == 204) { // 204 is empty response
                ApiEmptyResponse()
            } else {
                ApiSuccessResponse(body)
            }
        } else {
            var errorMsg = ""
            try {
                errorMsg = response.errorBody()!!.string()
            } catch (e: IOException) {
                e.printStackTrace()
                errorMsg = response.message()
            }

            return ApiErrorResponse(errorMsg)
        }
    }

    /**
     * Generic success response from api
     * @param <T>
    </T> */
    class ApiSuccessResponse<T> internal constructor(var body: T) : ApiResponse<T>()

    /**
     * Generic Error response from API
     * @param <T>
    </T> */
    class ApiErrorResponse<T> internal constructor(var errorMessage: String) : ApiResponse<T>()


    /**
     * separate class for HTTP 204 resposes so that we can make ApiSuccessResponse's body non-null.
     */
    class ApiEmptyResponse<T> : ApiResponse<T>()

}


