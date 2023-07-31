package com.lmkhant.notalator.ui.library

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LibraryScreen() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
    ) {
        CollectionSection()
    }

}

@Preview
@Composable
fun LibraryScreenPreview() {

}