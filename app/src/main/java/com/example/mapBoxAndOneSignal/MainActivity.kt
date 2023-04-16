package com.example.mapBoxAndOneSignal

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.core.view.WindowCompat
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.mapBoxAndOneSignal.core.AppKeyboardFocusManager
import com.example.mapBoxAndOneSignal.core.Constants
import com.example.mapBoxAndOneSignal.domain.model.UserStatus
import com.example.mapBoxAndOneSignal.presentation.bottomnavigation.BottomNavItem
import com.example.mapBoxAndOneSignal.presentation.bottomnavigation.BottomNavigation
import com.example.mapBoxAndOneSignal.presentation.bottomnavigation.NavGraph
import com.example.mapBoxAndOneSignal.presentation.commonComponents.ChatSnackBar
import com.example.mapBoxAndOneSignal.ui.theme.ChatWithMeTheme
import com.example.mapBoxAndOneSignal.MainViewModel
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.onesignal.OSSubscriptionObserver
import com.onesignal.OSSubscriptionStateChanges
import com.onesignal.OneSignal
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity(), OSSubscriptionObserver {
    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel = defaultViewModelProviderFactory.create(MainViewModel::class.java)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        // Logging set to help debug issues, remove before releasing your app.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)

        // OneSignal Initialization
        OneSignal.initWithContext(this)
        OneSignal.setAppId(Constants.ONESIGNAL_APP_ID)

        // OneSignal Enable Notification
        OneSignal.addSubscriptionObserver(this)
        OneSignal.disablePush(false)

        setContent {
            AppKeyboardFocusManager()
            ChatWithMeTheme {
                MainScreenView()
            }
        }
    }

    override fun onOSSubscriptionChanged(p0: OSSubscriptionStateChanges?) {
        if (p0!!.from.isSubscribed &&
            !p0.to.isSubscribed
        ) {
            println("Notifications Disabled!")
        }
        if (!p0.from.isSubscribed &&
            p0.to.isSubscribed
        ) {
            println("Notifications Enabled!")
        }
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        mainViewModel.setUserStatusToFirebase(UserStatus.ONLINE)
        super.onResume()
    }

    override fun onPause() {
        mainViewModel.setUserStatusToFirebase(UserStatus.OFFLINE)
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        mainViewModel.setUserStatusToFirebase(UserStatus.OFFLINE)
        super.onDestroy()
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(
    ExperimentalComposeUiApi::class, ExperimentalAnimationApi::class,
    ExperimentalMaterial3Api::class, ExperimentalPagerApi::class
)
@Composable
fun MainScreenView() {
    val keyboardController = LocalSoftwareKeyboardController.current
    val snackbarHostState = remember { SnackbarHostState() }
    val bottomBarState = rememberSaveable { (mutableStateOf(false)) }
    val navController = rememberAnimatedNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
        modifier = Modifier.navigationBarsPadding(),
        snackbarHost = {
            SnackbarHost(snackbarHostState) { data ->
                ChatSnackBar(
                    snackbarData = data
                )
            }
        },
        bottomBar = {
            bottomBarState.value =
                currentRoute != BottomNavItem.SignIn.fullRoute &&
                        currentRoute != BottomNavItem.SignUp.fullRoute &&
                        currentRoute != BottomNavItem.Chat.fullRoute
            BottomNavigation(
                navController = navController,
                bottomBarState = bottomBarState.value,
                snackbarHostState
            )
        },
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
//            HorizontalPager(
//                count = items.
////                state = pagerState
//            ) {
            NavGraph(
                navController = navController,
                snackbarHostState = snackbarHostState,
                keyboardController = keyboardController!!
            )
//            }
        }
    }
}