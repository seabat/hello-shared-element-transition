package dev.seabat.android.hellosharedelementtransition.ui.compose

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import dev.seabat.android.hellosharedelementtransition.Coffee
import dev.seabat.android.hellosharedelementtransition.R

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun CoffeeListDetail(modifier: Modifier = Modifier) {

    var selectedCoffee by remember {
        mutableStateOf<Coffee?>(null)
    }

    var showDetails by remember {
        mutableStateOf(false)
    }

    val coffeeList = remember {
        mutableStateListOf(
            Coffee(
                name = "Cappuccino",
                description = "A warm coffee with amazing latte art",
                image = R.drawable.cappuccino
            ),
            Coffee(
                name = "Latte",
                description = "A warm coffee",
                image = R.drawable.latte
            )
        )
    }

    SharedTransitionLayout(modifier) {
        AnimatedContent(
            targetState = showDetails,
            label = "transition"
        ) { targetState ->
            if (!targetState) {
                CoffeeList(
                    coffeeList = coffeeList,
                    onShowDetails = {
                        selectedCoffee = it
                        showDetails = true
                    },
                    animatedVisibilityScope = this@AnimatedContent,
                    sharedTransitionScope = this@SharedTransitionLayout
                )
            } else {
                // Checks if the coffee is not null
                selectedCoffee?.let { coffee ->
                    CoffeeDetail(
                        coffee = coffee,
                        onBack = {
                            showDetails = false
                        },
                        animatedVisibilityScope = this@AnimatedContent,
                        sharedTransitionScope = this@SharedTransitionLayout
                    )
                }
            }
        }
    }
}