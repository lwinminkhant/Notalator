package com.lmkhant.notalator.feature_note.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lmkhant.notalator.feature_note.domain.model.Note
import com.lmkhant.notalator.feature_note.domain.use_case.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val noteUseCases: NoteUseCases) : ViewModel() {

    val notes: MutableState<List<Note>> = mutableStateOf(emptyList())
    val notesByPage: MutableState<List<Note>> = mutableStateOf(emptyList())
    val favouriteNotes: MutableState<List<Note>> = mutableStateOf(emptyList())

    fun insert(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        noteUseCases.addNote(note)
    }

    fun delete(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        noteUseCases.deleteNote(note)
    }

    fun update(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        noteUseCases.updateNote(note)
    }

    fun getPagesBy(documentId: Int) {
        noteUseCases.getNotesBy(documentId)
            .onEach {
                notesByPage.value = it
            }.launchIn(viewModelScope)
    }

    fun getAllNotes() {
        noteUseCases.getNotes()
            .onEach {
                notes.value = it
            }.launchIn(viewModelScope)
    }

    fun getFavouriteNotes() {
        noteUseCases.getFavouriteNotes()
            .onEach {
                favouriteNotes.value = it
            }.launchIn(viewModelScope)
    }
}