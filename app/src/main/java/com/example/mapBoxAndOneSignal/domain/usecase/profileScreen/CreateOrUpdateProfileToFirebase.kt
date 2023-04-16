package com.example.mapBoxAndOneSignal.domain.usecase.profileScreen

import com.example.mapBoxAndOneSignal.domain.model.User
import com.example.mapBoxAndOneSignal.domain.repository.ProfileScreenRepository

class CreateOrUpdateProfileToFirebase(
    private val profileScreenRepository: ProfileScreenRepository
) {
    suspend operator fun invoke(user: User) =
        profileScreenRepository.createOrUpdateProfileToFirebase(user)
}