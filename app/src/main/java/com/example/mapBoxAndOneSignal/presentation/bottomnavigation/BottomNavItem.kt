package com.example.mapBoxAndOneSignal.presentation.bottomnavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.DesignServices
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    var title: String,
    var icon: ImageVector,
    var screen_route: String,
    var arguments: String
) {
    object UI : BottomNavItem(
        "UI",
        Icons.Filled.DesignServices,
        "UI",
        ""
    ) {
        val fullRoute = screen_route + arguments
    }

    object MapBox : BottomNavItem(
        "MapBox",
        Icons.Filled.Map,
        "MapBox",
        ""
    ) {
        val fullRoute = screen_route + arguments
    }

    object Profile : BottomNavItem(
        "Profile",
        Icons.Filled.Person,
        "profile",
        ""
    ) {
        val fullRoute = screen_route + arguments
    }

    object UserList : BottomNavItem(
        "Chat",
        Icons.Filled.Person,
        "userlist",
        ""
    ) {
        val fullRoute = screen_route + arguments
    }

    object Chat : BottomNavItem(
        "Chat",
        Icons.Filled.Chat,
        "chat",
        "/{chatroomUUID}" + "/{opponentUUID}" + "/{registerUUID}" + "/{oneSignalUserId}"
    ) {
        val fullRoute = screen_route + arguments
    }

}