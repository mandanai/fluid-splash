package dev.chayaphon.fluid.fluidsplash.presentation.navigation

import kotlinx.serialization.Serializable

/** Type-safe navigation destinations of the demo app. */
sealed interface Route {
    @Serializable
    data object Start : Route

    @Serializable
    data object Introduce : Route

    @Serializable
    data object Cool : Route

    @Serializable
    data object Loading : Route

    @Serializable
    data object Metaball : Route
}
