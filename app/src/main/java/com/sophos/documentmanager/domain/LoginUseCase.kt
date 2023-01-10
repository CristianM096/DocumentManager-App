package com.sophos.documentmanager.domain

import com.sophos.documentmanager.data.model.UserModel
import com.sophos.documentmanager.data.repository.UserRepository
import javax.inject.Inject


class LoginUseCase @Inject constructor(private val repository: UserRepository){
    suspend operator fun invoke(email:String, password:String):UserModel?{
        val user:UserModel? = repository.getLoginUser(email,password)
        return user
    }
}