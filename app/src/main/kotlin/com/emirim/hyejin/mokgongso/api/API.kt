package com.emirim.hyejin.mokgongso.api

import com.emirim.hyejin.mokgongso.model.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface API {
    @POST("/duplicatechk/")
    fun duplicatechk(@Body duplicatechk: Duplicatechk): Call<Message>

    @POST("/signup/")
    fun signup(@Body signUp: Signup): Call<Message>

    @POST("/signin/")
    fun signIn(@Body signIn: Signin): Call<SignInMessage>

    @POST("/make/app/")
    fun make(@Body make: Mandalart): Call<Message>

    @POST("/mandalChk/app/")
    fun mandalChk(@Body mandalChk: MandalChk): Call<MandalChkMessage>

    @POST("/getMandal/app/")
    fun getMandal(@Body mandalChk: MandalChk): Call<Re>

    @POST("/setLow/app/")
    fun setLow(@Body setLow: SetLow): Call<Message>
}