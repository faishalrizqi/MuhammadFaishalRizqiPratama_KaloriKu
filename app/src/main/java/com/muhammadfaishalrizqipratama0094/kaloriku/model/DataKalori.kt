package com.muhammadfaishalrizqipratama0094.kaloriku.model

data class DataKalori(
    val weight: Double,
    val height: Double,
    val age: Int,
    val isMale: Boolean,
    val activityLevel: ActivityLevel
)

enum class ActivityLevel(val factor: Double) {
    SEDENTARY(1.2),
    LIGHT(1.375),
    MODERATE(1.55),
    ACTIVE(1.725),
    VERY_ACTIVE(1.9)
}

fun calculateCalories(data: DataKalori): Int {
    val bmr = if (data.isMale) {
        88.362 + (13.397 * data.weight) + (4.799 * data.height) - (5.677 * data.age)
    } else {
        447.593 + (9.247 * data.weight) + (3.098 * data.height) - (4.330 * data.age)
    }

    return (bmr * data.activityLevel.factor).toInt()
}