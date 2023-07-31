package com.lmkhant.notalator.ui.library.note

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lmkhant.notalator.feature_note.domain.model.Note
import com.lmkhant.notalator.feature_note.viewmodel.NoteViewModel

@Composable
fun EditPageScreen(documentId: Int) {
    val viewModel: NoteViewModel = hiltViewModel()

    Column(
        Modifier.fillMaxSize(),
    ) {

        NoteSectionEdit(
            Modifier.weight(1f),
            documentId = documentId
        )

        Row(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            IconButton(onClick = {
                viewModel.insert(
                    Note(
                        null,
                        noteNumber = 0,
                        noteContent = "",
                        favourite = false,
                        collectionId = documentId
                    )
                )
            }) {
                Icon(
                    imageVector = Icons.Default.AddBox,
                    contentDescription = "new page",
                )
            }
        }

    }

}

@Composable
fun PageTextField(update: (String) -> Unit, text: String) {
    val textFieldValue = remember {
        mutableStateOf(TextFieldValue(text))
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Box(modifier = Modifier.padding(12.dp)) {
            TextField(
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

@Preview
@Composable
fun EditPageScreenPreview() {
    PageTextField(update = {}, text = "")
}