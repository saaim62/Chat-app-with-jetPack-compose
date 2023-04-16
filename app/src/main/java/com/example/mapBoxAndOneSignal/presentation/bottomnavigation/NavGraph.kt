package com.example.mapBoxAndOneSignal.presentation.bottomnavigation

import MapBoxScreen
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.mapBoxAndOneSignal.presentation.chat.ChatScreen
import com.example.mapBoxAndOneSignal.presentation.designScreen.UIScreen
import com.example.mapBoxAndOneSignal.presentation.profile.ProfileScreen
import com.example.mapBoxAndOneSignal.presentation.userlist.Userlist
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.mapbox.maps.Style

@OptIn(ExperimentalComposeUiApi::class, ExperimentalAnimationApi::class)
@Composable
fun NavGraph(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    keyboardController: SoftwareKeyboardController
) {
    AnimatedNavHost(navController, startDestination = BottomNavItem.MapBox.fullRoute) {

        composable(
            BottomNavItem.MapBox.fullRoute,
            arguments = listOf(
                navArgument("MapBox") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            ),
            enterTransition = {
                when (initialState.destination.route) {
                    BottomNavItem.UserList.fullRoute ->
                        slideIntoContainer(
                            AnimatedContentScope.SlideDirection.Left,
                            animationSpec = tween(700)
                        )
                    else -> null
                }

            }, exitTransition = {
                when (targetState.destination.route) {
                    BottomNavItem.UserList.fullRoute ->
                        slideOutOfContainer(
                            AnimatedContentScope.SlideDirection.Right,
                            animationSpec = tween(700)
                        )
                    else -> null
                }
            }
        ) {
            MapBoxScreen(onMapReady = { mapView ->
                mapView.loadStyleJson(Style.MAPBOX_STREETS)
            })
        }

        composable(
            BottomNavItem.UI.fullRoute,
            arguments = listOf(
                navArgument("UI") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            ),
            enterTransition = {
                when (initialState.destination.route) {
                    BottomNavItem.MapBox.fullRoute ->
                        slideIntoContainer(
                            AnimatedContentScope.SlideDirection.Left,
                            animationSpec = tween(700)
                        )
                    else -> null
                }

            }, exitTransition = {
                when (targetState.destination.route) {
                    BottomNavItem.MapBox.fullRoute ->
                        slideOutOfContainer(
                            AnimatedContentScope.SlideDirection.Right,
                            animationSpec = tween(700)
                        )
                    else -> null
                }
            }
        ) {
            UIScreen()
        }

        composable(
            BottomNavItem.Profile.fullRoute,
            enterTransition = {
                when (initialState.destination.route) {
                    BottomNavItem.UserList.fullRoute ->
                        slideIntoContainer(
                            AnimatedContentScope.SlideDirection.Right,
                            animationSpec = tween(250, easing = LinearEasing)
                        )
                    else -> null
                }

            }, exitTransition = {
                when (targetState.destination.route) {
                    BottomNavItem.UserList.fullRoute ->
                        slideOutOfContainer(
                            AnimatedContentScope.SlideDirection.Left,
                            animationSpec = tween(250, easing = LinearEasing)
                        )
                    else -> null
                }
            }) {
            ProfileScreen(
                navController = navController,
                snackbarHostState = snackbarHostState,
                keyboardController = keyboardController
            )
        }
        composable(
            BottomNavItem.UserList.fullRoute,
            enterTransition = {
                when (initialState.destination.route) {
                    BottomNavItem.Profile.fullRoute ->
                        slideIntoContainer(
                            AnimatedContentScope.SlideDirection.Left,
                            animationSpec = tween(250, easing = LinearEasing)
                        )
                    else -> null
                }

            }, exitTransition = {
                when (targetState.destination.route) {
                    BottomNavItem.UserList.fullRoute ->
                        slideOutOfContainer(
                            AnimatedContentScope.SlideDirection.Right,
                            animationSpec = tween(250, easing = LinearEasing)
                        )
                    else -> null
                }
            }) {
            Userlist(
                navController = navController,
                snackbarHostState = snackbarHostState,
                keyboardController = keyboardController
            )
        }

        composable(
            BottomNavItem.Chat.fullRoute,
            arguments = listOf(
                navArgument("chatroomUUID") {
                    type = NavType.StringType
                }, navArgument("opponentUUID") {
                    type = NavType.StringType
                }, navArgument("registerUUID") {
                    type = NavType.StringType
                }, navArgument("oneSignalUserId") {
                    type = NavType.StringType
                }),
            enterTransition = {
                when (initialState.destination.route) {
//                    BottomNavItem.UserList.fullRoute -> slideIntoContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(700))
                    else -> null
                }

            }, exitTransition = {

                when (targetState.destination.route) {
//                    BottomNavItem.UserList.fullRoute -> slideOutOfContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(700))
                    else -> null
                }
            }) {

            val chatroomUUID = remember {
                it.arguments?.getString("chatroomUUID")
            }
            val opponentUUID = remember {
                it.arguments?.getString("opponentUUID")
            }
            val registerUUID = remember {
                it.arguments?.getString("registerUUID")
            }
            val oneSignalUserId = remember {
                it.arguments?.getString("oneSignalUserId")
            }

            ChatScreen(
                chatRoomUUID = chatroomUUID ?: "",
                opponentUUID = opponentUUID ?: "",
                registerUUID = registerUUID ?: "",
                oneSignalUserId = oneSignalUserId ?: "",
                navController = navController,
                keyboardController = keyboardController
            )

        }
    }
}