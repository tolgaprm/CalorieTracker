package com.plcoding.onboarding_domain.use_case

import com.plcoding.core.util.UiText
import com.plcoding.onboarding_domain.R

class ValidateNutrients {

    operator fun invoke(
        carbsRatioText: String,
        proteinRatioText: String,
        fatRatioText: String,
    ): Result {
        val carbRatio = carbsRatioText.toIntOrNull()
        val proteinRatio = proteinRatioText.toIntOrNull()
        val fatRatio = fatRatioText.toIntOrNull()

        if (carbRatio == null || proteinRatio == null || fatRatio == null) {
            return Result.Error(
                message = UiText.StringResource(R.string.error_invalid_values)
            )
        }

        if (carbRatio + proteinRatio + fatRatio != 100) {
            return Result.Error(
                message = UiText.StringResource(R.string.error_not_100_percent)
            )
        }

        return Result.Success(
            carbsRatio = carbRatio / 100f,
            proteinRatio = proteinRatio / 100f,
            fatRatio = fatRatio / 100f
        )
    }

    sealed class Result {
        data class Success(
            val carbsRatio: Float,
            val proteinRatio: Float,
            val fatRatio: Float,
        ) : Result()

        data class Error(val message: UiText) : Result()
    }
}