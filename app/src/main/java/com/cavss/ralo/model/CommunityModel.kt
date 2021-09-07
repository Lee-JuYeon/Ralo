package com.cavss.ralo.model

data class CommunityModel(
    val description : String,
    val time : String,
    val postUID : String,
    val user : UserModel
)