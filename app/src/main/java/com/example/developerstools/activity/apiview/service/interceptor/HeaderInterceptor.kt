package com.example.developerstools.activity.apiview.service.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Accept:", "application/json")
            .addHeader("ApiKey:", "FrancoMarcuzzi")
            .build()

        return chain.proceed(request)
    }
}