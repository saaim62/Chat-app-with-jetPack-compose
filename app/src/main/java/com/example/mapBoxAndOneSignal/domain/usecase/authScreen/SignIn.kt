package com.example.mapBoxAndOneSignal.domain.usecase.authScreen

import com.example.mapBoxAndOneSignal.domain.repository.AuthScreenRepository

class SignIn(
    private val authScreenRepository: AuthScreenRepository
) {
    suspend operator fun invoke(email: String, password: String) =
        authScreenRepository.signIn(email, password)
}