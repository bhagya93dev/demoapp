package com.example.myapplication.ui.main

import com.google.gson.annotations.SerializedName

class ResultList(@SerializedName("gender") val gender: String,
@SerializedName("name") val name : Name,
                 @SerializedName("location") val  location:Location,
                 @SerializedName("picture") val picture : Picture

)

class Name(@SerializedName("title") val title:String,
           @SerializedName("first") val first:String,
           @SerializedName("last") val last:String)

class Location(@SerializedName("city") val city:String,
               @SerializedName("state") val state:String,
               @SerializedName("country") val country:String)

class Picture(@SerializedName("large") val large:String,
              @SerializedName("medium") val medium:String)