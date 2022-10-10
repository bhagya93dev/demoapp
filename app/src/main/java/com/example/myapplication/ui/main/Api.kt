package com.example.myapplication.ui.main

import retrofit2.Call
import retrofit2.http.GET


interface Api {
    @GET("api/?results=10")
    fun getsuperHeroes(): Call<Results>?

    companion object {
        const val BASE_URL = "https://randomuser.me/"
    }
}