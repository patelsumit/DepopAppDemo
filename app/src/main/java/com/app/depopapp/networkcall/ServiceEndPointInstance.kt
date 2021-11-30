package com.app.depopapp.networkcall

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
/*
    ServiceEndPointInstance Class is used to call web service it return instance with gson converter
* * */
class ServiceEndPointInstance {
    companion object {
        private const val baseUrl = "https://api.garage.me/api/v1/products/"
        fun getInstance(): Retrofit {
            return Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create()).build()
        }
    }
}