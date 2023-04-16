package com.example.mapBoxAndOneSignal.presentation.profile.components

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.R
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest


@Composable
fun ChooseProfilePicFromGallery(
    profilePictureUrlForCheck: String,
    size: Dp = 100.dp,
    onSelect: (Uri?) -> Unit = {},
) {
    val context = LocalContext.current

    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    var bitmap by remember {
        mutableStateOf<Bitmap?>(null)
    }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
        onSelect(uri)
    }

    Box(
        modifier = Modifier
            .clip(CircleShape),
        contentAlignment = Alignment.Center
    ) {
        LaunchedEffect(key1 = imageUri) {
            if (imageUri != null) {
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
                    bitmap = MediaStore.Images
                        .Media.getBitmap(context.contentResolver, imageUri)
                } else {
                    val source = ImageDecoder
                        .createSource(context.contentResolver, imageUri!!)
                    bitmap = ImageDecoder.decodeBitmap(source)
                }
            }
        }
        if (bitmap != null) {
            Image(
                painter = rememberAsyncImagePainter(bitmap),
                contentDescription = null,
                modifier = Modifier
//                    .padding(MaterialTheme.spacing.medium)
                    .clickable { launcher.launch("image/*") }
                    .size(size),
//                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        } else {
            if (profilePictureUrlForCheck != "") {
                Image(painter = rememberAsyncImagePainter(profilePictureUrlForCheck),
                    contentDescription = null,
                    modifier = Modifier
//                        .padding(MaterialTheme.spacing.medium)
                        .clickable { launcher.launch("image/*") }
                        .size(size),
//                        .clip(CircleShape),
                    contentScale = ContentScale.Crop)
            } else {
                Image(painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(context).data(R.mipmap.sym_def_app_icon)
                        .build()
                ),
                    contentDescription = null,
                    modifier = Modifier
//                        .padding(MaterialTheme.spacing.medium)
                        .clickable { launcher.launch("image/*") }
                        .size(size),
//                        .clip(CircleShape)
//                        .border(2.dp, Color.Gray, CircleShape),
                    contentScale = ContentScale.Crop)
            }
        }
    }
}