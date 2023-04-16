package com.example.mapBoxAndOneSignal.domain.repository

import android.net.Uri
import com.example.mapBoxAndOneSignal.domain.model.User
import com.example.mapBoxAndOneSignal.domain.model.UserStatus
import com.example.mapBoxAndOneSignal.utils.Response
import kotlinx.coroutines.flow.Flow

interface ProfileScreenRepository {
    suspend fun signOut(): Flow<Response<Boolean>>
    suspend fun uploadPictureToFirebase(url: Uri): Flow<Response<String>>
    suspend fun createOrUpdateProfileToFirebase(user: User): Flow<Response<Boolean>>
    suspend fun loadProfileFromFirebase(): Flow<Response<User>>
    suspend fun setUserStatusToFirebase(userStatus: UserStatus): Flow<Response<Boolean>>
}