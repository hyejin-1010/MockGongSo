package com.emirim.hyejin.mokgongso.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIRequestManager {
    lateinit var retrofit: Retrofit
    lateinit var api: API

    companion object {
        val SERVER_URL = "http://iwin247.kr:3321"
        lateinit var apiManager: APIRequestManager

        fun getInstance(): APIRequestManager {
            apiManager = APIRequestManager()

            return apiManager
        }
    }

    fun requestServer(): API {
        retrofit = Retrofit.Builder().baseUrl(SERVER_URL).addConverterFactory(GsonConverterFactory.create()).build()

        return retrofit.create(API::class.java)
    }
}