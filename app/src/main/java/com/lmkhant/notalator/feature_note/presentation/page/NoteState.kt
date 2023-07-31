package com.lmkhant.notalator.feature_note.presentation.page

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.lmkhant.notalator.feature_note.domain.model.Note

data class NoteState(
    val note: Note,
    val isPlaying: MutableState<Boolean> = mutableStateOf(false)
)