package com.xu.myapplication.service


import com.xu.myapplication.entity.UserInfo
import com.xu.myapplication.network.ApiResponse
import retrofit2.*
import retrofit2.http.*

interface UserService {


    @FormUrlEncoded
    @POST("user/login")
    fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ) : Call<ApiResponse<UserInfo>>


    @FormUrlEncoded
    @POST("user/register")
    fun register(
        @Field("username") username: String, @Field("password") pwd: String, @Field(
            "repassword"
        ) rpwd: String
    ): Call<ApiResponse<UserInfo>>

}