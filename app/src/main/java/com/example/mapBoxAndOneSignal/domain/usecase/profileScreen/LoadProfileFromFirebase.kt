package com.example.mapBoxAndOneSignal.domain.usecase.profileScreen

import com.example.mapBoxAndOneSignal.domain.repository.ProfileScreenRepository

class LoadProfileFromFirebase(
    private val profileScreenRepository: ProfileScreenRepository
) {
    suspend operator fun invoke() = profileScreenRepository.loadProfileFromFirebase()
}