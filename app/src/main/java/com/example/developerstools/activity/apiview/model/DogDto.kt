package com.example.developerstools.activity.apiview.model

import com.google.gson.annotations.SerializedName

data class DogDto(
    @SerializedName("breed") val brand: String,
    @SerializedName("name") val name: String
)
