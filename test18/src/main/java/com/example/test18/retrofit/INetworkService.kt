package com.example.test18.retrofit

import com.example.test18.model.UserListModel
import com.example.test18.model.UserModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface INetworkService {
    @GET("api/users")               // @GET("/api/users")
    // baseurl : https://reqres.in/    https://reqres.in
    //https://reqres.in/api/users?page=2
    //예) doGetUserList(2)에서 인자 2가 @Query("page")에 page 의 key 값
    // Query는 key
    fun doGetUserList(@Query("page") page: String): Call<UserListModel>

    @GET
    fun getAvatarImage(@Url url: String): Call<ResponseBody>

    //@GET("users/list?sort=desc")
    @GET("api/users/2")
    fun test1(): Call<UserModel>
}