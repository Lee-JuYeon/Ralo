package com.cavss.ralo.model

import android.graphics.drawable.Drawable

data class UserModel (
    val name : String,
    val age : Int,
    val selfie : Drawable?,
    val postUIDs : ArrayList<String?>,
    val uid : String )