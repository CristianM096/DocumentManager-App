package com.sophos.documentmanager.domain

import com.sophos.documentmanager.data.model.AttachmentModel
import com.sophos.documentmanager.data.model.AttachmentRegistrationModel
import com.sophos.documentmanager.data.repository.AttachmentRepository
import javax.inject.Inject

class GetAttachmentUseCase @Inject constructor(private val repository: AttachmentRepository){
    suspend operator fun invoke(idAttachment:String): AttachmentModel?{
        val attachments: AttachmentModel? = repository.getAttachment(idAttachment)
        return attachments
    }
}