package dev.seabat.android.hellosharedelementtransition.ui.compose

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dev.seabat.android.hellosharedelementtransition.Coffee

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun CoffeeDetail(
    coffee: Coffee,
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .clickable(onClick = onBack),
        contentAlignment = Alignment.Center
    ) {
        with(sharedTransitionScope) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Image(
                    painter = painterResource(id = coffee.image),
                    contentDescription = coffee.description,
                    modifier = Modifier
                        .sharedBounds(
                            rememberSharedContentState(key = coffee.id),
                            animatedVisibilityScope = animatedVisibilityScope
                        )
                        .size(200.dp)
                        .clip(RoundedCornerShape(20.dp)),
                    contentScale = ContentScale.Crop
                )

                Text(
                    text = coffee.name,
                    style = MaterialTheme.typography.titleLarge
                )

                Text(text = coffee.description)
            }
        }
    }
}