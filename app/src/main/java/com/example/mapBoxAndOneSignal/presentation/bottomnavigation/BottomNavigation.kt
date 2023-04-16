package com.example.mapBoxAndOneSignal.presentation.bottomnavigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.mapBoxAndOneSignal.core.SnackbarController
import com.example.mapBoxAndOneSignal.presentation.bottomnavigation.components.CustomNavItem
import com.example.mapBoxAndOneSignal.presentation.userlist.UserListViewModel
import com.example.mapBoxAndOneSignal.presentation.userlist.components.AlertDialogChat
import com.example.mapBoxAndOneSignal.ui.theme.spacing

@Composable
fun BottomNavigation(
    navController: NavController,
    bottomBarState: Boolean,
    snackbarHostState: SnackbarHostState,
) {

    val userListViewModel: UserListViewModel = hiltViewModel()

    val toastMessage = userListViewModel.toastMessage.value
    LaunchedEffect(key1 = toastMessage) {
        if (toastMessage != "") {
            SnackbarController(this).showSnackbar(snackbarHostState, toastMessage, "Close")
        }
    }
    AnimatedVisibility(
        visible = bottomBarState,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),
    ) {
        BottomAppBar(
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            CustomNavItem(
                onClick = {
                    if (currentRoute == BottomNavItem.MapBox.fullRoute) {
                        navController.popBackStack()
                        navController.navigate(BottomNavItem.UserList.fullRoute)
                    } else {
                        navController.popBackStack()
                        navController.navigate(BottomNavItem.MapBox.fullRoute)
                    }
                },
                iconSelected = {
                    if (currentRoute == BottomNavItem.MapBox.fullRoute) {
                        Icon(
                            imageVector = BottomNavItem.UserList.icon,
                            contentDescription = BottomNavItem.UserList.title,
                        )
                    } else {
                        Icon(
                            imageVector = BottomNavItem.MapBox.icon,
                            contentDescription = BottomNavItem.MapBox.title,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )

            Spacer(Modifier.weight(10f, true))
            CustomNavItem(
                onClick = {
                    if (currentRoute == BottomNavItem.MapBox.screen_route) {
                        navController.popBackStack()
                        navController.navigate(BottomNavItem.UI.fullRoute)
                    } else if (currentRoute == BottomNavItem.UI.screen_route) {
                        navController.popBackStack()
                        navController.navigate(BottomNavItem.UserList.fullRoute)
                    }
                },
                iconSelected = {
                    if (currentRoute == BottomNavItem.MapBox.screen_route) {
                        Icon(
                            imageVector = BottomNavItem.UI.icon,
                            contentDescription = BottomNavItem.UI.title,
                        )
                    } else if (currentRoute == BottomNavItem.UI.screen_route) {
                        Icon(
                            imageVector = BottomNavItem.UserList.icon,
                            contentDescription = BottomNavItem.UserList.title,
                        )
                    }
                }
            )


            Spacer(Modifier.weight(1f, true))
            if (currentRoute == BottomNavItem.UserList.screen_route) {
                var showAlertDialog by remember {
                    mutableStateOf(false)
                }
                if (showAlertDialog) {
                    AlertDialogChat(
                        onDismiss = { showAlertDialog = !showAlertDialog },
                        onConfirm = {
                            showAlertDialog = !showAlertDialog
                            userListViewModel.createFriendshipRegisterToFirebase(it)
                        })
                }
                ExtendedFloatingActionButton(
                    modifier = Modifier.padding(end = MaterialTheme.spacing.small),
                    onClick = {
                        showAlertDialog = !showAlertDialog
                    },
                    elevation = FloatingActionButtonDefaults.elevation(
                        defaultElevation = 0.dp
                    )
                ) {
                    Text(text = "Add New Message")
                    Icon(imageVector = Icons.Filled.Add, contentDescription = null)
                }
            }
        }
    }
}