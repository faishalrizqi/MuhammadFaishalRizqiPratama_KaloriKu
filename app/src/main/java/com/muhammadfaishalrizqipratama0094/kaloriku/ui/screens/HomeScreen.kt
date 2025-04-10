package com.muhammadfaishalrizqipratama0094.kaloriku.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.muhammadfaishalrizqipratama0094.kaloriku.R
import com.muhammadfaishalrizqipratama0094.kaloriku.model.ActivityLevel
import com.muhammadfaishalrizqipratama0094.kaloriku.model.DataKalori
import com.muhammadfaishalrizqipratama0094.kaloriku.model.calculateCalories
import com.muhammadfaishalrizqipratama0094.kaloriku.navigation.Screen
import com.muhammadfaishalrizqipratama0094.kaloriku.ui.components.ActivityLevelSelector
import com.muhammadfaishalrizqipratama0094.kaloriku.ui.components.GenderSelector
import com.muhammadfaishalrizqipratama0094.kaloriku.ui.components.NumberInputField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    var weight by rememberSaveable { mutableStateOf("") }
    var height by rememberSaveable { mutableStateOf("") }
    var age by rememberSaveable { mutableStateOf("") }
    var isMale by rememberSaveable { mutableStateOf(true) }
    var activityLevel by rememberSaveable { mutableStateOf(ActivityLevel.MODERATE) }

    var weightError by rememberSaveable { mutableStateOf(false) }
    var heightError by rememberSaveable { mutableStateOf(false) }
    var ageError by rememberSaveable { mutableStateOf(false) }

    fun validateWeight(): Boolean {
        weightError = weight.isEmpty() ||
                weight.toDoubleOrNull() == null ||
                weight.toDouble() < 30 ||
                weight.toDouble() > 300
        return !weightError
    }

    fun validateHeight(): Boolean {
        heightError = height.isEmpty() ||
                height.toDoubleOrNull() == null ||
                height.toDouble() < 100 ||
                height.toDouble() > 250
        return !heightError
    }

    fun validateAge(): Boolean {
        ageError = age.isEmpty() ||
                age.toIntOrNull() == null ||
                age.toInt() < 15 ||
                age.toInt() > 100
        return !ageError
    }

    fun validateAll(): Boolean {
        return validateWeight() && validateHeight() && validateAge()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.app_name)) }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.calorie),
                contentDescription = "Calorie Icon",
                modifier = Modifier
                    .size(120.dp)
                    .padding(bottom = 16.dp)
            )

            NumberInputField(
                value = weight,
                onValueChange = {
                    weight = it
                    if (weightError) validateWeight()
                },
                label = stringResource(R.string.weight_label),
                isError = weightError,
                errorMessage = if (weightError) stringResource(R.string.error_invalid_weight) else null
            )

            NumberInputField(
                value = height,
                onValueChange = {
                    height = it
                    if (heightError) validateHeight()
                },
                label = stringResource(R.string.height_label),
                isError = heightError,
                errorMessage = if (heightError) stringResource(R.string.error_invalid_height) else null
            )

            NumberInputField(
                value = age,
                onValueChange = {
                    age = it
                    if (ageError) validateAge()
                },
                label = stringResource(R.string.age_label),
                isError = ageError,
                errorMessage = if (ageError) stringResource(R.string.error_invalid_age) else null,
                imeAction = ImeAction.Done
            )

            GenderSelector(
                isMale = isMale,
                onGenderSelected = { isMale = it }
            )

            ActivityLevelSelector(
                selectedActivity = activityLevel,
                onActivitySelected = { activityLevel = it }
            )

            Button(
                onClick = {
                    if (validateAll()) {
                        val calorieData = DataKalori(
                            weight = weight.toDouble(),
                            height = height.toDouble(),
                            age = age.toInt(),
                            isMale = isMale,
                            activityLevel = activityLevel
                        )
                        val calories = calculateCalories(calorieData)
                        navController.navigate(Screen.Result.createRoute(calories))
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp)
            ) {
                Text(stringResource(R.string.calculate))
            }
        }
    }
}