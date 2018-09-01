package com.emirim.hyejin.mokgongso.api

import com.emirim.hyejin.mokgongso.model.Mandalart
import com.emirim.hyejin.mokgongso.model.Duplicatechk
import com.emirim.hyejin.mokgongso.model.Message
import com.emirim.hyejin.mokgongso.model.Signin
import com.emirim.hyejin.mokgongso.model.Signup
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface API {
    @POST("/duplicatechk/")
    fun duplicatechk(@Body duplicatechk: Duplicatechk): Call<Message>

    @POST("/signup/")
    fun signup(@Body signUp: Signup): Call<Message>

    @POST("/signin/")
    fun signIn(@Body signIn: Signin): Call<Message>

    @POST("/make/app/")
    fun mandalart(@Body mandalart: Mandalart): Call<Message>
}