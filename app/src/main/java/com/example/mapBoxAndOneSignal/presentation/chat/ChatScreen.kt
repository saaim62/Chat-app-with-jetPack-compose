package com.example.mapBoxAndOneSignal.presentation.chat

import android.widget.Toast
import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.mapBoxAndOneSignal.core.SnackbarController
import com.example.mapBoxAndOneSignal.domain.model.MessageRegister
import com.example.mapBoxAndOneSignal.domain.model.MessageStatus
import com.example.mapBoxAndOneSignal.domain.model.User
import com.example.mapBoxAndOneSignal.presentation.bottomnavigation.BottomNavItem
import com.example.mapBoxAndOneSignal.presentation.chat.chatAppBar.ChatAppBar
import com.example.mapBoxAndOneSignal.presentation.chat.chatAppBar.ProfilePictureDialog
import com.example.mapBoxAndOneSignal.presentation.chat.chatInput.ChatInput
import com.example.mapBoxAndOneSignal.presentation.chat.chatrow.ReceivedMessageRow
import com.example.mapBoxAndOneSignal.presentation.chat.chatrow.SentMessageRow
import java.util.*

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ChatScreen(
    chatRoomUUID: String,
    opponentUUID: String,
    registerUUID: String,
    oneSignalUserId: String,
    chatViewModel: ChatScreenViewModel = hiltViewModel(),
    navController: NavHostController,
    keyboardController: SoftwareKeyboardController
) {

    chatViewModel.loadMessagesFromFirebase(chatRoomUUID, opponentUUID, registerUUID)
    ChatScreenContent(
        chatRoomUUID,
        opponentUUID,
        registerUUID,
        oneSignalUserId,
        chatViewModel,
        navController,
        keyboardController
    )

}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ChatScreenContent(
    chatRoomUUID: String,
    opponentUUID: String,
    registerUUID: String,
    oneSignalUserId: String,
    chatViewModel: ChatScreenViewModel,
    navController: NavHostController,
    keyboardController: SoftwareKeyboardController
) {
    val messages = chatViewModel.messages

    LaunchedEffect(key1 = Unit) {
        chatViewModel.loadOpponentProfileFromFirebase(opponentUUID)
    }
    var opponentProfileFromFirebase by remember {
        mutableStateOf(User())
    }
    opponentProfileFromFirebase = chatViewModel.opponentProfileFromFirebase.value
    val opponentName = opponentProfileFromFirebase.userName
    val opponentSurname = opponentProfileFromFirebase.userSurName
    val opponentPictureUrl = opponentProfileFromFirebase.userProfilePictureUrl
    val opponentStatus = opponentProfileFromFirebase.status

    var showDialog by remember {
        mutableStateOf(false)
    }
    if (showDialog) {
        ProfilePictureDialog(opponentPictureUrl) {
            showDialog = !showDialog
        }
    }

    val scrollState = rememberLazyListState(initialFirstVisibleItemIndex = messages.size)
    val messagesLoadedFirstTime = chatViewModel.messagesLoadedFirstTime.value
    val messageInserted = chatViewModel.messageInserted.value

    LaunchedEffect(key1 = messagesLoadedFirstTime, messages, messageInserted) {
        if (messages.isNotEmpty()) {
            scrollState.scrollToItem(
                index = messages.size - 1
            )
        }
    }

    val imePaddingValues = PaddingValues()
    val imeBottomPadding = imePaddingValues.calculateBottomPadding().value.toInt()
    LaunchedEffect(key1 = imeBottomPadding) {
        if (messages.isNotEmpty()) {
            scrollState.scrollToItem(
                index = messages.size - 1
            )
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .focusable()
            .wrapContentHeight()
            .imePadding()
            .pointerInput(Unit) {
                detectTapGestures(onTap = { keyboardController.hide() })
            }
    ) {
        val context = LocalContext.current

        ChatAppBar(
            title = "$opponentName $opponentSurname",
            description = opponentStatus.lowercase(),
            pictureUrl = opponentPictureUrl,
            onUserNameClick = {
                Toast.makeText(context, "User Profile Clicked", Toast.LENGTH_SHORT).show()
            }, onBackArrowClick = {
                navController.popBackStack()
                navController.navigate(BottomNavItem.UserList.fullRoute)
            }, onUserProfilePictureClick = {
                showDialog = true
            },
        )
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            state = scrollState
        ) {
            items(messages) { message: MessageRegister ->
                val sdf = remember {
                    java.text.SimpleDateFormat("hh:mm", Locale.ROOT)
                }

                when (message.isMessageFromOpponent){
                    true -> {
                        ReceivedMessageRow(
                            text = message.chatMessage.message,
                            messageTime = sdf.format(message.chatMessage.date),
                        )
                    }
                    false ->{
                        SentMessageRow(
                            text = message.chatMessage.message,
                            messageTime = sdf.format(message.chatMessage.date),
                            messageStatus = MessageStatus.valueOf(message.chatMessage.status)
                        )
                    }
                }
            }

        }
        ChatInput(
            onMessageChange = { messageContent ->
                chatViewModel.insertMessageToFirebase(
                    chatRoomUUID,
                    messageContent,
                    registerUUID,
                    oneSignalUserId
                )
            },
        )
    }
}