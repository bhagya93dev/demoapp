package com.example.myapplication.room

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey @NonNull @ColumnInfo(name = "first_name") val firstName: String,
    @ColumnInfo(name = "last_name") val lastName: String?,
    @ColumnInfo(name = "picture_medium") val pictureMedium: String?,
    @ColumnInfo(name = "picture_large") val pictureLarge: String?,
    @ColumnInfo(name = "city") var city: String?,
    @ColumnInfo(name = "state") val state: String?,
    @ColumnInfo(name = "country") val country: String?,
    @ColumnInfo(name = "accepted") val accepted: Boolean?,
)
