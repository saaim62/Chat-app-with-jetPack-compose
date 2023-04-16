package com.example.mapBoxAndOneSignal.domain.usecase.userListScreen

import com.example.mapBoxAndOneSignal.domain.repository.UserListScreenRepository

class RejectPendingFriendRequestToFirebase(
    private val userListScreenRepository: UserListScreenRepository
) {
    suspend operator fun invoke(registerUUID: String) =
        userListScreenRepository.rejectPendingFriendRequestToFirebase(registerUUID)
}