package com.example.mapBoxAndOneSignal.domain.usecase.profileScreen

import android.net.Uri
import com.example.mapBoxAndOneSignal.domain.repository.ProfileScreenRepository

class UploadPictureToFirebase(
    private val profileScreenRepository: ProfileScreenRepository
) {
    suspend operator fun invoke(url: Uri) = profileScreenRepository.uploadPictureToFirebase(url)
}