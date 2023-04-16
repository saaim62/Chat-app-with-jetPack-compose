package com.example.mapBoxAndOneSignal.domain.repository

import com.example.mapBoxAndOneSignal.utils.Response
import kotlinx.coroutines.flow.Flow

interface AuthScreenRepository {
    fun isUserAuthenticatedInFirebase(): Flow<Response<Boolean>>
    suspend fun signIn(email: String, password: String): Flow<Response<Boolean>>
    suspend fun signUp(email: String, password: String): Flow<Response<Boolean>>
}