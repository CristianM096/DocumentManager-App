package com.sophos.documentmanager.data.repository

import com.sophos.documentmanager.data.model.AttachmentModel
import com.sophos.documentmanager.data.model.AttachmentRegistrationModel
import com.sophos.documentmanager.data.network.AttachmentService

import javax.inject.Inject

class AttachmentRepository @Inject constructor(private val api: AttachmentService) {
    suspend fun getAttachments(email : String) : List<AttachmentModel>?{
        val response : AttachmentRegistrationModel? = api.getAttachments(email)
        return response?.items
    }
    suspend fun getAttachment(idAttachment : String) : AttachmentModel?{
        val response : AttachmentRegistrationModel? = api.getAttachment(idAttachment)
        return response?.items?.firstOrNull()
    }
}