package com.example.mapBoxAndOneSignal.presentation.chat.chatAppBar

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.mapBoxAndOneSignal.ui.theme.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatAppBar(
    modifier: Modifier = Modifier,
    title: String = "Title",
    description: String = "Description",
    pictureUrl: String? = null,
    onUserNameClick: (() -> Unit)? = null,
    onBackArrowClick: (() -> Unit)? = null,
    onUserProfilePictureClick: (() -> Unit)? = null,
) {
    SmallTopAppBar(
        modifier = modifier.statusBarsPadding(),
//        modifier = Modifier.align(Alignment.CenterStart),
        title = {
            Row {
                Surface(
                    modifier = modifier.size(50.dp),
                    shape = CircleShape,
                    color = Color.LightGray
                ) {
                    if (pictureUrl != null) {
                        Image(
                            painter = rememberAsyncImagePainter(pictureUrl),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = modifier
                                .fillMaxHeight()
                                .aspectRatio(1f)
                                .clickable { onUserProfilePictureClick?.invoke() })
                    } else {
                        Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = null,
                            modifier = modifier
                                .fillMaxHeight()
                                .aspectRatio(1f)
                                .clickable { onUserProfilePictureClick?.invoke() })
                    }
                }
                Column(
                    modifier = modifier
                        .padding(start = MaterialTheme.spacing.small)
                        .clickable {
                            onUserNameClick?.invoke()
                        },
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = description,
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        },
        navigationIcon = {
            IconButton(onClick = { onBackArrowClick?.invoke() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Localized description"
                )
            }
        },
    )
}