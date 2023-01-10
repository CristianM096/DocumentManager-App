package com.sophos.documentmanager.domain

import com.sophos.documentmanager.data.model.AttachmentModel
import com.sophos.documentmanager.data.model.AttachmentRegistrationModel
import com.sophos.documentmanager.data.repository.AttachmentRepository
import javax.inject.Inject

class GetAttachmentsUseCase @Inject constructor(private val repository: AttachmentRepository){
    suspend operator fun invoke(email:String): List<AttachmentModel>?{
        val attachments: List<AttachmentModel>? = repository.getAttachments(email)
        return attachments
    }
}