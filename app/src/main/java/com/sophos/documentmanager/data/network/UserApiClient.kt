package com.sophos.documentmanager.data.network

import com.sophos.documentmanager.data.model.UserModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApiClient {
    @GET("RS_Usuarios")
    suspend fun getLoginUser(@Query("idUsuario") email:String, @Query("clave") password:String):Response<UserModel>

}