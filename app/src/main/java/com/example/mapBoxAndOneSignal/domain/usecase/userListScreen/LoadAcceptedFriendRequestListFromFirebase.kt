package com.example.mapBoxAndOneSignal.domain.usecase.userListScreen

import com.example.mapBoxAndOneSignal.domain.repository.UserListScreenRepository

class LoadAcceptedFriendRequestListFromFirebase(
    private val userListScreenRepository: UserListScreenRepository
) {
    suspend operator fun invoke() =
        userListScreenRepository.loadAcceptedFriendRequestListFromFirebase()
}