package com.lmkhant.notalator.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModelTextFieldSheet(
    title: String = "",
    text: String,
    onDismiss: () -> Unit,
    onSubmit: (text: String) -> Unit
) {
    val textFieldValue = remember { mutableStateOf(TextFieldValue(text)) }
    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        windowInsets = BottomSheetDefaults.windowInsets,
        scrimColor = Color.Transparent
    ) {

        Column(Modifier.padding(20.dp)) {
            Row(
                Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = {
                    onDismiss()
                }) {
                    Text("Cancel")
                }
                Button(onClick = {
                    onSubmit(textFieldValue.value.text)
                    onDismiss()
                }) {
                    Text("Save")
                }
            }
            OutlinedTextField(modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 0.dp, 0.dp, 40.dp),
                label = { Text(text = title) },
                value = textFieldValue.value, onValueChange = { textFieldValue.value = it })
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModelTextFieldSheetDelete(
    title: String = "Ask a Question",
    text: String,
    onDelete: () -> Unit,
    onDismiss: () -> Unit,
    onSubmit: (text: String) -> Unit
) {
    val textFieldValue = remember { mutableStateOf(TextFieldValue(text)) }
    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        windowInsets = BottomSheetDefaults.windowInsets,
        scrimColor = Color.Transparent
    ) {
        Column(Modifier.padding(20.dp)) {
            Row(
                Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = {
                    onDelete()
                    onDismiss()
                }) {
                    Text("Delete")
                }
                Button(onClick = {
                    onSubmit(textFieldValue.value.text)
                    onDismiss()
                }) {
                    Text("Save")
                }
            }
            OutlinedTextField(modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 0.dp, 0.dp, 40.dp),
                label = { Text(text = title) },
                value = textFieldValue.value, onValueChange = { textFieldValue.value = it })
        }
    }
}