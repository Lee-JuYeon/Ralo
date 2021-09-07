package com.cavss.ralo.model

data class ComplainModel (
    var expanded : Boolean,
    val timeStamp: String,
    val complain : String,
    val answer : String?,
    val user : UserModel)