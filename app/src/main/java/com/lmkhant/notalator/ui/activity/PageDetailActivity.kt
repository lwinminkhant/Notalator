package com.lmkhant.notalator.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.compose.AppTheme
import com.lmkhant.notalator.feature_note.viewmodel.NoteViewModel
import com.lmkhant.notalator.ui.library.note.NoteSection
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PageDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val collectionId = intent.getIntExtra("collectionId",0)
                    val viewModel : NoteViewModel = hiltViewModel()

                    viewModel.getPagesBy(collectionId)
                    NoteSection(notes = viewModel.notesByPage.value)
                }
            }
        }
    }
}