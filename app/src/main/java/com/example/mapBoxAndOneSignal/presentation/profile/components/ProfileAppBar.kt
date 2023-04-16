package com.example.mapBoxAndOneSignal.presentation.profile.components

import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileAppBar(
    modifier: Modifier = Modifier
) {
    SmallTopAppBar(
        modifier = modifier.statusBarsPadding(),
        title = { Text(text = "Chat app by Saim") }
    )
}