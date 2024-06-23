package dev.seabat.android.hellosharedelementtransition

import androidx.annotation.DrawableRes
import kotlin.random.Random

data class Coffee(
    val id: Int = Random.nextInt(),
    val name: String = "",
    val description: String = "",
    @DrawableRes val image: Int
)