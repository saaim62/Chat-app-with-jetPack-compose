package com.example.mapBoxAndOneSignal.domain.usecase.chatScreen

import com.example.mapBoxAndOneSignal.domain.repository.ChatScreenRepository

class LoadMessageFromFirebase(
    private val chatScreenRepository: ChatScreenRepository
) {
    suspend operator fun invoke(
        chatRoomUUID: String,
        opponentUUID: String,
        registerUUID: String
    ) = chatScreenRepository.loadMessagesFromFirebase(chatRoomUUID, opponentUUID, registerUUID)
}