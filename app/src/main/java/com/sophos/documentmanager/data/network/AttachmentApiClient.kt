package com.sophos.documentmanager.data.network

import com.sophos.documentmanager.data.model.AttachmentModel
import com.sophos.documentmanager.data.model.AttachmentRegistrationModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AttachmentApiClient {
    @GET("RS_Documentos")
    suspend fun getAttachments(@Query("correo") email:String): Response<AttachmentRegistrationModel>

    @GET("RS_Documentos")
    suspend fun getAttachment(@Query("idRegistro") idAttachment:String): Response<AttachmentRegistrationModel>

}