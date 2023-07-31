package com.lmkhant.notalator.ui.library.note

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.material.icons.filled.IosShare
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.lmkhant.notalator.R
import com.lmkhant.notalator.feature_note.domain.model.Note
import com.lmkhant.notalator.feature_note.presentation.page.NoteState
import com.lmkhant.notalator.feature_note.viewmodel.NoteViewModel
import com.lmkhant.notalator.feature_note.viewmodel.TextToSpeechViewModel


@Composable
fun NoteSection(notes : List<Note>) {
    val emptyListAnimation by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.empty_list))
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        if (notes.isEmpty()) {
            LottieAnimation(
                modifier = Modifier.align(Alignment.Center),
                composition = emptyListAnimation,
                iterations = LottieConstants.IterateForever,
            )
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(1),
                contentPadding = PaddingValues(
                    start = 12.dp, top = 16.dp, end = 12.dp, bottom = 12.dp
                )
            ) {
                items(notes.size) {
                    NoteWithOptions(NoteState(notes[it]))
                }
            }
        }
    }

}

@Composable
fun NoteSectionEdit(modifier: Modifier, documentId: Int) {
    val viewModel: NoteViewModel = hiltViewModel()
    viewModel.getPagesBy(documentId)

    val emptyListAnimation by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.empty_list))

    if (viewModel.notesByPage.value.isEmpty()) {
        LottieAnimation(
            modifier = modifier,
            composition = emptyListAnimation,
            iterations = LottieConstants.IterateForever,
        )
    } else {
        LazyVerticalGrid(
            modifier = modifier,
            columns = GridCells.Fixed(1),
            contentPadding = PaddingValues(
                start = 12.dp, top = 16.dp, end = 12.dp, bottom = 12.dp
            )
        ) {
            val pages = viewModel.notesByPage.value
            items(pages.size) { index ->
                val page = pages[index]
                PageTextField({
                    if (it.isNotEmpty())
                        viewModel.update(page.copy(noteContent = it))
                }, page.noteContent)
            }
        }
    }
}

@Composable
fun NoteWithOptions(noteState: NoteState, ttsvm: TextToSpeechViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val viewModel: NoteViewModel = hiltViewModel()
    val page = noteState.note
    ttsvm.setup(context)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Box(modifier = Modifier.padding(12.dp)) {
            Column {
                Text(text = page.noteContent)
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Icon(
                        imageVector = if (page.favourite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                        contentDescription = "favourite",
                        Modifier.clickable {
                            viewModel.update(
                                page.copy(
                                    noteId = page.noteId,
                                    favourite = !page.favourite
                                )
                            )
                        }
                    )
                    Icon(
                        imageVector = Icons.Default.IosShare,
                        contentDescription = "share",
                        Modifier.clickable {
                            val sendIntent: Intent = Intent().apply {
                                action = Intent.ACTION_SEND
                                putExtra(Intent.EXTRA_TEXT, page.noteContent)
                                type = "text/plain"
                            }
                            val shareIntent = Intent.createChooser(sendIntent, "Image chooser")
                            context.startActivity(shareIntent)
                        })
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "share",
                        Modifier.clickable {
                            viewModel.delete(page)
                        })
                    Icon(imageVector = if (noteState.isPlaying.value) Icons.Default.Pause else Icons.Default.Headphones,
                        contentDescription = "listen",
                        Modifier.clickable {
                            ttsvm.speakText(noteState)
                        })
                }
            }
        }
    }
}

