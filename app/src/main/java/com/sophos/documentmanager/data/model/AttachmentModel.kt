package com.sophos.documentmanager.data.model

import com.google.gson.annotations.SerializedName

class AttachmentModel (
    @SerializedName("Ciudad") val city:String = "",
    @SerializedName("Fecha") val date:String = "",
    @SerializedName("TipoAdjunto") val attachmentType:String = "",
    @SerializedName("Nombre") val userName:String = "",
    @SerializedName("Apellido") val userSurname:String = "",
    @SerializedName("Identificacion") val identification:String = "",
    @SerializedName("IdRegistro") val idAttachment:String = "",
    @SerializedName("TipoId") val typeIdentification:String = "",
    @SerializedName("Correo") val email:String = "",
    @SerializedName("Adjunto") val attachment:String = "",
)