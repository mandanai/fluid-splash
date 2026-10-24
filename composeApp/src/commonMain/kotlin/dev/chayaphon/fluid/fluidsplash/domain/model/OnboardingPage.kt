package dev.chayaphon.fluid.fluidsplash.domain.model

/**
 * One page of the onboarding showcase.
 *
 * A page shows either a [subtitle] or a [callToAction] button, never both. Both are nullable so
 * that rule is explicit instead of being inferred from an empty string.
 */
data class OnboardingPage(
    val title: String,
    val subtitle: String?,
    val callToAction: String?,
    val theme: PageTheme,
)

/** Visual theme of an [OnboardingPage]. Mapped to concrete artwork in the presentation layer. */
enum class PageTheme { Red, Yellow, Blue, Purple }
