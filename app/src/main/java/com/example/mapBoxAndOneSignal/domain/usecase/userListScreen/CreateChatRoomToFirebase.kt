package com.example.mapBoxAndOneSignal.domain.usecase.userListScreen

import com.example.mapBoxAndOneSignal.domain.repository.UserListScreenRepository

class CreateChatRoomToFirebase(
    private val userListScreenRepository: UserListScreenRepository
) {
    suspend operator fun invoke(acceptorUUID: String) =
        userListScreenRepository.createChatRoomToFirebase(acceptorUUID)
}