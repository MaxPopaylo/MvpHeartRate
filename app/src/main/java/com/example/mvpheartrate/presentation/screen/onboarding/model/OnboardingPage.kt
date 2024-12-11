package com.example.mvpheartrate.presentation.screen.onboarding.model

import androidx.annotation.DrawableRes
import com.example.mvpheartrate.R

sealed class OnboardingPage(
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String
) {
    data object First : OnboardingPage(
        image = R.drawable.onboarding_image_first,
        title = "Ваш трекер тиску",
        description = "Зазначайте, відстежуйте та аналізуйте свої показники артеріального тиску."
    )

    data object Second : OnboardingPage(
        image = R.drawable.onboarding_image_second,
        title = "Персоналізовані поради",
        description = "Програма надає дієві поради, які допоможуть вам підтримувати оптимальний рівень артеріального тиску та зменшити фактори ризику серцево-судинних захворювань."
    )

    data object Third : OnboardingPage(
        image = R.drawable.onboarding_image_third,
        title = "Нагадування",
        description = "Не відставайте від графіка контролю артеріального тиску та прийому ліків за допомогою спеціальних нагадувань."
    )
}