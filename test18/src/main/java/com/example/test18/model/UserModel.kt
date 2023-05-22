package com.example.test18.model

import android.graphics.Bitmap
import com.google.gson.annotations.SerializedName

data class UserModel( //data class ()로 써야 주생성자 선언한 형식. {} 아님.
    var id: String,

    @SerializedName("first_name")
    var firstName: String,
    @SerializedName("last_name")
    var lastName: String,
    var avatar: String,
    var email : String,
    var avatarBitmap: Bitmap
)