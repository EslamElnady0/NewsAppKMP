package com.example.newsappkmp.articles

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsappkmp.NewsInk
import com.example.newsappkmp.NewsMutedInk

@Composable
internal fun LoadingFeed(contentPadding: PaddingValues) {
    val transition = rememberInfiniteTransition(label = "loading shimmer")
    val shimmerPosition by transition.animateFloat(
        initialValue = -500f,
        targetValue = 1_200f,
        animationSpec = infiniteRepeatable(
            animation = tween(1_250),
            repeatMode = RepeatMode.Restart,
        ),
        label = "shimmer position",
    )
    val shimmer = Brush.linearGradient(
        colors = listOf(
            Color(0xFFE4E7EC),
            Color(0xFFF8FAFC),
            Color(0xFFE4E7EC),
        ),
        start = Offset(shimmerPosition - 260f, shimmerPosition - 260f),
        end = Offset(shimmerPosition, shimmerPosition),
    )

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            start = 16.dp,
            top = contentPadding.calculateTopPadding() + 18.dp,
            end = 16.dp,
            bottom = 28.dp,
        ),
        verticalArrangement = Arrangement.spacedBy(18.dp),
    ) {
        items(3) {
            LoadingCard(shimmer)
        }
    }
}

@Composable
private fun LoadingCard(shimmer: Brush) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(22.dp))
            .padding(bottom = 20.dp),
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .height(190.dp)
                .background(shimmer, RoundedCornerShape(topStart = 22.dp, topEnd = 22.dp)),
        )
        Spacer(Modifier.height(18.dp))
        Box(
            Modifier
                .padding(horizontal = 18.dp)
                .fillMaxWidth(0.82f)
                .height(22.dp)
                .background(shimmer, RoundedCornerShape(8.dp)),
        )
        Spacer(Modifier.height(11.dp))
        Box(
            Modifier
                .padding(horizontal = 18.dp)
                .fillMaxWidth(0.58f)
                .height(14.dp)
                .background(shimmer, RoundedCornerShape(8.dp)),
        )
    }
}

@Composable
internal fun ErrorContent(
    message: String,
    contentPadding: PaddingValues,
    onRetry: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding)
            .padding(32.dp),
        contentAlignment = Alignment.Center,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Couldn’t load the news",
                color = NewsInk,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
            )
            Spacer(Modifier.height(8.dp))
            Text(text = message, color = NewsMutedInk)
            Spacer(Modifier.height(20.dp))
            Button(onClick = onRetry) {
                Text("Try again")
            }
        }
    }
}
