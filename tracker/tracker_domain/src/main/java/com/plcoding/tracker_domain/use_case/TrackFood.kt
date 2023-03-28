package com.plcoding.tracker_domain.use_case

import com.plcoding.tracker_domain.model.MealType
import com.plcoding.tracker_domain.model.TrackableFood
import com.plcoding.tracker_domain.model.TrackedFood
import com.plcoding.tracker_domain.repository.TrackerRepository
import java.time.LocalDate

class TrackFood(
    private val repository: TrackerRepository,
) {
    suspend operator fun invoke(
        food: TrackableFood,
        amount: Int,
        mealType: MealType,
        date: LocalDate,
    ) {
        repository.insertTrackedFood(
            TrackedFood(
                name = food.name,
                carbs = ((food.carbsPer100g / 100f) * amount).toInt(),
                protein = ((food.proteinPer100g / 100f) * amount).toInt(),
                fat = ((food.fatPer100g / 100f) * amount).toInt(),
                calories = ((food.caloriesPer100g / 100f) * amount).toInt(),
                imageUrl = food.imageUrl,
                mealType = mealType,
                amount = amount,
                date = date
            )
        )
    }
}