package com.example.mapBoxAndOneSignal.presentation.chat.chatrow

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.mapBoxAndOneSignal.presentation.chat.chatrow.chatbubble.ChatBubbleConstraints
import com.example.mapBoxAndOneSignal.presentation.chat.chatrow.chatbubble.TextMessageInsideBubble
import com.example.mapBoxAndOneSignal.ui.theme.spacing

@Composable
fun ReceivedMessageRow(
    text: String,
    messageTime: String,
) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(
                start = MaterialTheme.spacing.small,
                end = MaterialTheme.spacing.extraLarge,
                top = MaterialTheme.spacing.extraSmall,
                bottom = MaterialTheme.spacing.extraSmall
            )
    ) {
        ChatBubbleConstraints(
            modifier = Modifier
                .clip(RoundedCornerShape(bottomEnd = 16.dp, topEnd = 16.dp, bottomStart = 16.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .clickable { },
            content = {
                TextMessageInsideBubble(
                    modifier = Modifier.padding(
                        start = MaterialTheme.spacing.extraSmall,
                        top = MaterialTheme.spacing.extraSmall,
                        end = MaterialTheme.spacing.small,
                        bottom = MaterialTheme.spacing.extraSmall
                    ),
                    text = text,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.bodyLarge,
                    messageStat = {
                        Text(
                            modifier = Modifier.padding(end = MaterialTheme.spacing.small),
                            text = messageTime,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                )
            }
        )
    }
}