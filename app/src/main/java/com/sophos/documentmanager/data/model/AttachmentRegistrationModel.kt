package com.sophos.documentmanager.data.model

import com.google.gson.annotations.SerializedName

class AttachmentRegistrationModel (
    @SerializedName("Items") val items: List<AttachmentModel> = emptyList(),
    @SerializedName("Count") val count: Int = 0,
    @SerializedName("ScannedCount") val scannedCount: Int = 0,
)