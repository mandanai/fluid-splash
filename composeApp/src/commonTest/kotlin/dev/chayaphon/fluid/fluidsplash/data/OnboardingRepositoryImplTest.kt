package dev.chayaphon.fluid.fluidsplash.data

import dev.chayaphon.fluid.fluidsplash.data.repository.OnboardingRepositoryImpl
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class OnboardingRepositoryImplTest {

    @Test
    fun providesFourPages() = runTest {
        val pages = OnboardingRepositoryImpl().getPages().first()

        assertEquals(4, pages.size)
    }

    @Test
    fun eachPageShowsEitherASubtitleOrACallToAction() = runTest {
        val pages = OnboardingRepositoryImpl().getPages().first()

        pages.forEach { page ->
            assertEquals(
                expected = 1,
                actual = listOfNotNull(page.subtitle, page.callToAction).size,
                message = "Page \"${page.title}\" must have exactly one of subtitle / callToAction",
            )
        }
    }

    @Test
    fun onlyTheLastPageHasACallToAction() = runTest {
        val pages = OnboardingRepositoryImpl().getPages().first()

        assertNotNull(pages.last().callToAction)
        pages.dropLast(1).forEach { assertNull(it.callToAction) }
    }
}
