package com.example.newsappkmp.articles

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.newsappkmp.NewsAccent
import com.example.newsappkmp.NewsInk
import com.example.newsappkmp.NewsMutedInk
import com.example.newsappkmp.domain.model.Article
import kotlinx.coroutines.delay

@Composable
internal fun AnimatedArticleCard(
    article: Article,
    index: Int,
    onToggleFavorite: (Article) -> Unit,
) {
    var visible by remember(article.id) { mutableStateOf(false) }

    LaunchedEffect(article.id) {
        delay(index.coerceAtMost(6) * 70L)
        visible = true
    }

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(tween(420)) +
            slideInVertically(tween(520, easing = FastOutSlowInEasing)) { it / 4 } +
            scaleIn(tween(520), initialScale = 0.96f),
    ) {
        ArticleCard(article, onToggleFavorite)
    }
}

@Composable
private fun ArticleCard(
    article: Article,
    onToggleFavorite: (Article) -> Unit,
) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.elevatedCardColors(containerColor = Color.White),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 5.dp),
    ) {
        Box {
            AsyncImage(
                model = article.imageUrl,
                contentDescription = article.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(190.dp)
                    .background(Color(0xFFE4E7EC)),
            )

            Surface(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(12.dp),
                shape = CircleShape,
                color = Color.White.copy(alpha = 0.94f),
                shadowElevation = 3.dp,
            ) {
                IconButton(
                    onClick = { onToggleFavorite(article) },
                    modifier = Modifier.semantics {
                        contentDescription = if (article.isFavorite) {
                            "Remove from favorites"
                        } else {
                            "Add to favorites"
                        }
                    },
                ) {
                    Text(
                        text = if (article.isFavorite) "♥" else "♡",
                        color = NewsAccent,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        }

        Column(modifier = Modifier.padding(18.dp)) {
            Text(
                text = "LATEST STORY",
                color = NewsAccent,
                fontSize = 11.sp,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = 1.3.sp,
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = article.title,
                color = NewsInk,
                fontSize = 21.sp,
                lineHeight = 26.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(Modifier.height(10.dp))
            Text(
                text = article.description,
                color = NewsMutedInk,
                fontSize = 14.sp,
                lineHeight = 21.sp,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(Modifier.height(16.dp))
            HorizontalDivider(color = Color(0xFFEAECF0))
            Spacer(Modifier.height(13.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    modifier = Modifier.size(34.dp),
                    shape = CircleShape,
                    color = NewsInk,
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            text = article.author.take(1),
                            color = Color.White,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                }
                Spacer(Modifier.width(10.dp))
                Column {
                    Text(
                        text = article.author,
                        color = NewsInk,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Text(
                        text = "News correspondent",
                        color = NewsMutedInk,
                        fontSize = 11.sp,
                    )
                }
            }
        }
    }
}
