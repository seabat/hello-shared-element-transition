package dev.seabat.android.hellosharedelementtransition.ui.compose

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
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
fun CoffeeList(
    coffeeList: List<Coffee>,
    onShowDetails: (Coffee) -> Unit,
    modifier: Modifier = Modifier,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    LazyColumn(
        contentPadding = PaddingValues(
            horizontal = 20.dp,
            vertical = 10.dp
        ),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // Iterating through the coffee list
        items(coffeeList) { coffee ->
            // Using the SharedTransitionScope
            with(sharedTransitionScope) {
                Row(
                    modifier = modifier
                        .clickable {
                            onShowDetails(coffee)
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = coffee.image),
                        contentDescription = null,
                        modifier = Modifier
                            // I explain what is this below
                            .sharedBounds(
                                rememberSharedContentState(key = coffee.id),
                                animatedVisibilityScope = animatedVisibilityScope
                            )
                            .size(100.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Column {
                        Text(
                            text = coffee.name,
                            style = MaterialTheme.typography.titleLarge
                        )

                        Text(
                            text = coffee.description,
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
            }
        }
    }
}