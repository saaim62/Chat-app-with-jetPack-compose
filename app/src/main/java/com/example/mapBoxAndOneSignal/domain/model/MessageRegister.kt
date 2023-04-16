package com.example.mapBoxAndOneSignal.domain.model

data class MessageRegister(
    var chatMessage: ChatMessage,
    var isMessageFromOpponent: Boolean
)