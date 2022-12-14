package com.compose.cocktaildakk_compose.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.compose.cocktaildakk_compose.ui.theme.Color_Default_Backgounrd

@Composable
fun ImageWithBackground(
    @DrawableRes backgroundDrawableResId: Int,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null,
    content: @Composable() () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color_Default_Backgounrd),
    ) {
        Image(
            modifier = modifier,
            painter = painterResource(backgroundDrawableResId),
            contentDescription = contentDescription,
            contentScale = contentScale,
            alpha = alpha,
            colorFilter = colorFilter,
        )
        content()
    }
}
