package com.sophos.documentmanager.ui.viewmodel

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sophos.documentmanager.domain.GetAttachmentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageShowViewModel @Inject constructor(
    private val getAttachmentUseCase: GetAttachmentUseCase
) : ViewModel(){

    val _currentImage = MutableLiveData<String>()
    val currentImage: LiveData<String> = _currentImage
    val defaultImage: String = "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"
    val _state = MutableLiveData<Boolean>()
    val state: LiveData<Boolean> = _state
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        _currentImage.value = defaultImage
    }
    fun getAttachment(idAttachment:String){
        _isLoading.value = true
        viewModelScope.launch {
            val attachment = getAttachmentUseCase(idAttachment)
            if(attachment != null){
                try {
                    val imageDecoded = decodeBase64(attachment.attachment)
                    _currentImage.value = attachment.attachment
                }catch (e: java.lang.Exception){
                    _currentImage.value = defaultImage
                }
                _state.value = true
            }
            _isLoading.value = false
        }
    }
    fun decodeBase64(imageBase64:String ): Bitmap
    {
        val decodeString = Base64.decode(imageBase64, Base64.DEFAULT)
        val decodedByte = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.size)
        return decodedByte
    }
    fun defaultImageBuild() : Bitmap{
        return BitmapFactory.decodeByteArray(Base64.decode(defaultImage,Base64.DEFAULT),0,Base64.decode(defaultImage,Base64.DEFAULT).size)
    }
    fun changeImageState(){
        _state.value = false
    }
}