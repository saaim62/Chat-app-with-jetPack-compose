package com.example.mapBoxAndOneSignal.domain.usecase.authScreen

import com.example.mapBoxAndOneSignal.domain.repository.AuthScreenRepository

class IsUserAuthenticatedInFirebase(
    private val authScreenRepository: AuthScreenRepository
) {
    operator fun invoke() = authScreenRepository.isUserAuthenticatedInFirebase()
}