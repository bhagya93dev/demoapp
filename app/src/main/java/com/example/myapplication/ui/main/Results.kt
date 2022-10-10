package com.example.myapplication.ui.main

import com.google.gson.annotations.SerializedName


class Results(@SerializedName("results") val results: List<ResultList>)