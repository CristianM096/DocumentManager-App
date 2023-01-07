package com.sophos.documentmanager.data.model

import com.google.gson.annotations.SerializedName

data class UserModel (
    @SerializedName("id") val id:String,
    @SerializedName("nombre") val name:String,
    @SerializedName("apellido") val surname:String,
    @SerializedName("acceso") val access:Boolean,
    @SerializedName("admin") val admin:Boolean,
)