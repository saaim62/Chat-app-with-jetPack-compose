package com.example.mapBoxAndOneSignal.domain.usecase.chatScreen

import com.example.mapBoxAndOneSignal.domain.repository.ChatScreenRepository

class BlockFriendToFirebase(
    private val chatScreenRepository: ChatScreenRepository
) {
    suspend operator fun invoke(registerUUID: String) =
        chatScreenRepository.blockFriendToFirebase(registerUUID)
}