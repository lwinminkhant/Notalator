package com.lmkhant.notalator.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lmkhant.notalator.feature_note.domain.model.Note
import com.lmkhant.notalator.feature_note.presentation.page.NoteState
import com.lmkhant.notalator.feature_note.viewmodel.NoteViewModel
import com.lmkhant.notalator.feature_note.viewmodel.TextToSpeechViewModel

@Composable
fun HomeScreen() {
    val viewModel: NoteViewModel = hiltViewModel()
    val scrollState = rememberScrollState()
    viewModel.getAllNotes()
    viewModel.getFavouriteNotes()
    Column(
        modifier = Modifier
            .padding(20.dp)
            .verticalScroll(scrollState)
    ) {
        HomeAppBar()
        Text(text = "All Notes")
        AllNotes(viewModel.notes.value)
        Text(text = "Favourite Notes")
        FavouriteNotes(viewModel.favouriteNotes.value)
    }
}

@Composable
fun AllNotes(notes: List<Note>) {
    LazyVerticalGrid(
        modifier = Modifier.height(500.dp).heightIn(500.dp),
        columns = GridCells.Fixed(2),
    ) {
        items(notes.size) {
            PlayableNote(NoteState(notes[it]))
        }
    }
}

@Composable
fun FavouriteNotes(notes: List<Note>) {
    LazyVerticalGrid(
        modifier = Modifier.height(500.dp).heightIn(500.dp).padding(8.dp),
        columns = GridCells.Fixed(2),
    ) {
        items(notes.size) {
            PlayableNote(NoteState(notes[it]))
        }
    }
}

@Composable
fun PlayableNote(noteState: NoteState, ttsvm: TextToSpeechViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val page = noteState.note
    ttsvm.setup(context)
    Card(
        modifier = Modifier
            .height(150.dp)
            .padding(vertical = 8.dp, horizontal = 4.dp)
    ) {
        Box(modifier = Modifier.padding(12.dp)) {
            Text(text = page.noteContent, Modifier.fillMaxSize())
            Icon(imageVector = if (noteState.isPlaying.value) Icons.Default.Pause else Icons.Default.Headphones,
                contentDescription = "listen",
                Modifier
                    .align(Alignment.BottomEnd)
                    .clickable {
                        ttsvm.speakText(noteState)
                    })
        }
    }
}

@Composable
fun HomeAppBar() {
    Row(
        Modifier.padding(bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Notalator", fontSize = 22.sp,
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = "account",
            modifier = Modifier
                .padding(4.dp)
                .clickable {

                },
        )
    }
}