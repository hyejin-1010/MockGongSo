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

    @POST("/addDay/app")
    fun addDay(@Body addDay: AddDay): Call<Message>

    @POST("/tmake/app/")
    fun tmake(@Body tmake: TMake): Call<Message>

    @POST("/getTMandalChk/app/")
    fun getTMandalChk(@Body mandalChk: MandalChk): Call<TRe>

    @POST("/delSub/app/")
    fun delSub(@Body mandalChk: MandalChk): Call<Message>

    @POST("/addDiary/app/")
    fun addDiary(@Body addDiary: AddDiary): Call<Message>

    @POST("/getDiary/app/")
    fun getDiary(@Body mandalChk: MandalChk): Call<GetDiary>
}