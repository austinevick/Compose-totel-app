package com.example.totelapp.widget

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.totelapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAppbar(title: String) {

    val navigator = LocalNavigator.current

    CenterAlignedTopAppBar(
        modifier = Modifier
            .graphicsLayer { compositingStrategy = CompositingStrategy.Offscreen }
            .drawWithContent(ContentDrawScope::drawContent),
        navigationIcon = {
            IconButton(onClick = { navigator?.pop() }) {
                Icon(
                    painter = painterResource(id = R.drawable.keyboard_backspace),
                    contentDescription = "Back"
                )
            }
        },
        title = { Text(text = title) },
        windowInsets = WindowInsets.systemBars.only(WindowInsetsSides.Horizontal)
    )
}
