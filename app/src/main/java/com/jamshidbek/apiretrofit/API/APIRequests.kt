package com.jamshidbek.apiretrofit.API

import com.jamshidbek.apiretrofit.API.Comments.Comments
import com.jamshidbek.apiretrofit.API.Posts.Post
import com.jamshidbek.apiretrofit.API.User.User
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface APIRequests {

    @GET("users")
    fun getUsers() : Call<User>

    @GET("posts")
    fun getPosts(@Query("userId") userId : String) : Call<Post>

    @GET("comments")
    fun getComments(@Query("postId") postId : String) : Call<Comments>

    companion object {
        fun GetResponse(): APIRequests {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(APIRequests::class.java)
        }
    }
}