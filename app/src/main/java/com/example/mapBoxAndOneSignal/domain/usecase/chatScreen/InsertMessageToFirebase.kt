package com.example.mapBoxAndOneSignal.domain.usecase.chatScreen

import com.example.mapBoxAndOneSignal.domain.repository.ChatScreenRepository

class InsertMessageToFirebase(
    private val chatScreenRepository: ChatScreenRepository
) {
    suspend operator fun invoke(
        chatRoomUUID: String,
        messageContent: String,
        registerUUID: String,
        oneSignalUserId: String
    ) = chatScreenRepository.insertMessageToFirebase(
        chatRoomUUID,
        messageContent,
        registerUUID,
        oneSignalUserId
    )
}