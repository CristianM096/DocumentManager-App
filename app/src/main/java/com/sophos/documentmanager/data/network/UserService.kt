package com.sophos.documentmanager.data.network

import com.sophos.documentmanager.data.model.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class UserService @Inject constructor(private val api:UserApiClient){
    suspend fun getLoginUser(email:String, password:String) : UserModel?{
        return withContext(Dispatchers.IO){
            val response : Response<UserModel> = api.getLoginUser(email,password)
            response.body()
        }
    }
}