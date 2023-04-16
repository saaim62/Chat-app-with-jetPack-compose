package com.example.mapBoxAndOneSignal.domain.usecase.userListScreen

import com.example.mapBoxAndOneSignal.domain.repository.UserListScreenRepository

class LoadPendingFriendRequestListFromFirebase(
    private val userListScreenRepository: UserListScreenRepository
) {
    suspend operator fun invoke() =
        userListScreenRepository.loadPendingFriendRequestListFromFirebase()
}