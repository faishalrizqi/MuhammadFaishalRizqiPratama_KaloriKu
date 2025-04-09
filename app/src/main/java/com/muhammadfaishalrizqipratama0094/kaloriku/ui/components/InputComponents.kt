package com.muhammadfaishalrizqipratama0094.kaloriku.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.muhammadfaishalrizqipratama0094.kaloriku.model.ActivityLevel
import com.muhammadfaishalrizqipratama0094.kaloriku.R

@Composable
fun NumberInputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isError: Boolean = false,
    errorMessage: String? = null,
    imeAction: ImeAction = ImeAction.Next
) {
    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(label) },
            isError = isError,
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = imeAction
            ),
            modifier = Modifier.fillMaxWidth()
        )

        if (isError && errorMessage != null) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }
    }
}

@Composable
fun GenderSelector(
    isMale: Boolean,
    onGenderSelected: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth().padding(vertical = 8.dp)) {
        Text(
            text = stringResource(id = R.string.gender_label),
            style = MaterialTheme.typography.bodyLarge
        )

        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = isMale,
                onClick = { onGenderSelected(true) }
            )
            Text(
                text = stringResource(id = R.string.male),
                modifier = Modifier.padding(start = 8.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            RadioButton(
                selected = !isMale,
                onClick = { onGenderSelected(false) }
            )
            Text(
                text = stringResource(id = R.string.female),
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}

@Composable
fun ActivityLevelSelector(
    selectedActivity: ActivityLevel,
    onActivitySelected: (ActivityLevel) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth().padding(vertical = 8.dp)) {
        Text(
            text = stringResource(id = R.string.activity_level),
            style = MaterialTheme.typography.bodyLarge
        )

        val activityOptions = listOf(
            ActivityLevel.SEDENTARY to stringResource(R.string.sedentary),
            ActivityLevel.LIGHT to stringResource(R.string.light),
            ActivityLevel.MODERATE to stringResource(R.string.moderate),
            ActivityLevel.ACTIVE to stringResource(R.string.active),
            ActivityLevel.VERY_ACTIVE to stringResource(R.string.very_active)
        )

        activityOptions.forEach { (activityLevel, label) ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedActivity == activityLevel,
                    onClick = { onActivitySelected(activityLevel) }
                )
                Text(
                    text = label,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}