package com.sophos.documentmanager.data.network

import com.sophos.documentmanager.data.model.AttachmentRegistrationModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class AttachmentService @Inject constructor(private val api:AttachmentApiClient) {
    suspend fun getAttachments(email:String) : AttachmentRegistrationModel?{
        return withContext(Dispatchers.IO){
            val response : Response<AttachmentRegistrationModel> = api.getAttachments(email)
            response.body()
        }
    }
    suspend fun getAttachment(idAttachment:String) : AttachmentRegistrationModel?{
        return withContext(Dispatchers.IO){
            val response : Response<AttachmentRegistrationModel> = api.getAttachment(idAttachment)
            response.body()
        }
    }
}