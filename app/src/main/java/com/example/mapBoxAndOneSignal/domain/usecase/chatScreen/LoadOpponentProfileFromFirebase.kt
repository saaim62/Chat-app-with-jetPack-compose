package com.example.mapBoxAndOneSignal.domain.usecase.chatScreen

import com.example.mapBoxAndOneSignal.domain.repository.ChatScreenRepository

class LoadOpponentProfileFromFirebase(
    private val chatScreenRepository: ChatScreenRepository
) {
    suspend operator fun invoke(opponentUUID: String) =
        chatScreenRepository.loadOpponentProfileFromFirebase(opponentUUID)
}