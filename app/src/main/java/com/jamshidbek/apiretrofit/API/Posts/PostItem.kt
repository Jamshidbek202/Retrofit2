package com.jamshidbek.apiretrofit.API.Posts

data class PostItem(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
)