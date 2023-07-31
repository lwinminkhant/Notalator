package com.lmkhant.notalator.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun PageTextField(update:(String)->Unit, text: String) {
    val textFieldValue = remember{
        mutableStateOf(TextFieldValue(text))
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Box(modifier = Modifier.padding(12.dp)) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = textFieldValue.value,
                placeholder = { Text(text = "Aa") },
                onValueChange = {
                    textFieldValue.value = it
                    update(it.text)
                })
        }
    }
}