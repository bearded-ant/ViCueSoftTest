package com.tests.vicuesofttest.domain

import retrofit2.Call
import retrofit2.http.GET

interface VideoAndPosterService {
    @GET("backgrounds/?group=video&category_id=1")
    fun getAll(): Call<List<DataResponse>>
}