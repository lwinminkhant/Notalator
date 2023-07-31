package com.lmkhant.notalator.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.compose.AppTheme
import com.lmkhant.notalator.ui.library.note.EditPageScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditPageActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val documentId = intent.getIntExtra("collectionId",0)
                    EditPageScreen(documentId)
                }
            }
        }
    }
}
