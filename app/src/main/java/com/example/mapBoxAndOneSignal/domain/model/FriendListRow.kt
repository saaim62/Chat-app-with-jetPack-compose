package com.example.mapBoxAndOneSignal.domain.model

import com.example.mapBoxAndOneSignal.domain.model.ChatMessage

data class FriendListRow(
    val chatRoomUUID: String,
    val userEmail: String = "",
    val userUUID: String = "",
    val oneSignalUserId: String,
    val registerUUID: String = "",
    val userPictureUrl: String = "",
    val lastMessage: ChatMessage = ChatMessage()
)