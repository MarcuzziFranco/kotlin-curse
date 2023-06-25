package com.example.developerstools.activity.apiview.service

import com.example.developerstools.activity.apiview.model.DogResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface IApiService {
    @GET
  suspend fun getDogByBreed(@Url url: String): Response<DogResponseModel>
}