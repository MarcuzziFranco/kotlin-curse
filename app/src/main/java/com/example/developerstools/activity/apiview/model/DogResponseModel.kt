package com.example.developerstools.activity.apiview.model

import com.google.gson.annotations.SerializedName

data class DogResponseModel(
    @SerializedName("status") var status: String,
    @SerializedName("message") var images: List<String>
)
