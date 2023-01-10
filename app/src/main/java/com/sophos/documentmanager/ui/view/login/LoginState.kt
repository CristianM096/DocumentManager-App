package com.sophos.documentmanager.ui.view.login

import androidx.annotation.StringRes
import com.sophos.documentmanager.data.model.UserModel

data class LoginState (
    val email: String ="",
    val password: String = "",
    val user: UserModel? = null,
    val successLogin: Boolean = false,
    val displayProgressBar: Boolean = false,
    @StringRes val errorMessage: Int? = null
)