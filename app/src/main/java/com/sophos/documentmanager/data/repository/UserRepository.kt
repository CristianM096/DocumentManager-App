package com.sophos.documentmanager.data.repository

import com.sophos.documentmanager.data.model.UserModel
import com.sophos.documentmanager.data.network.UserService
import javax.inject.Inject

class UserRepository @Inject constructor(private val api:UserService){
    suspend fun getLoginUser(email:String, password:String):UserModel?{
        val response : UserModel? = api.getLoginUser(email, password)
        return response
    }
}