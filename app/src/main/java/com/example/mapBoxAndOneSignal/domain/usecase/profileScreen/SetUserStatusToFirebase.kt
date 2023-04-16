package com.example.mapBoxAndOneSignal.domain.usecase.profileScreen

import com.example.mapBoxAndOneSignal.domain.model.UserStatus
import com.example.mapBoxAndOneSignal.domain.repository.ProfileScreenRepository

class SetUserStatusToFirebase(
    private val profileScreenRepository: ProfileScreenRepository
) {
    suspend operator fun invoke(userStatus: UserStatus) =
        profileScreenRepository.setUserStatusToFirebase(userStatus)
}