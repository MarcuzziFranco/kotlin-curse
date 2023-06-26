package com.example.developerstools.activity.apiview.service

import com.example.developerstools.activity.apiview.model.DogDto
import com.example.developerstools.activity.apiview.model.DogResponseModel
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface IApiService {


    //@Headers("Accept:application/json")
    @GET
    suspend fun getDogByBreed(@Url url: String): Response<DogResponseModel>
    @GET("dog/{id}/algo")
    suspend fun getDogByBreedPath(@Path("id") idDog: String): Response<DogResponseModel>
    @GET("dog/{id}/algo")
    suspend fun getDogByBreedQuery(@Query("pet") pet:String,@Query("name") name:String): Response<DogResponseModel>
    @POST
    fun PostDog(@Body dogDto:DogDto): Call<*>

    @Multipart
    @POST
    fun PostDogMultipar(@Part image:MultipartBody.Part,@Part("example") example:String):Call<*>

}