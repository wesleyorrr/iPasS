package com.freelanceror.ipaas.network

import com.freelanceror.ipaas.model.NasaPicture
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://api.nasa.gov/"

interface NasaApi {
    @GET("planetary/apod")
    suspend fun getPictures(
        @Query("api_key") apiKey: String,
        @Query("count") count: Int = 10
    ): List<NasaPicture>
}

