package com.app.features.login.data.repository

import kotlinx.coroutines.flow.Flow

interface LoginRepository {

    fun googleLogin() : Flow<Boolean>
}