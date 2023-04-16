package com.example.mapBoxAndOneSignal.domain.usecase.userListScreen

import com.example.mapBoxAndOneSignal.domain.repository.UserListScreenRepository

class SearchUserFromFirebase(
    private val userListScreenRepository: UserListScreenRepository
) {
    suspend operator fun invoke(userEmail: String) =
        userListScreenRepository.searchUserFromFirebase(userEmail)
}