package com.example.mapBoxAndOneSignal.domain.usecase.profileScreen

import com.example.mapBoxAndOneSignal.domain.repository.ProfileScreenRepository

class SignOut(
    private val profileScreenRepository: ProfileScreenRepository
) {
    suspend operator fun invoke() = profileScreenRepository.signOut()
}