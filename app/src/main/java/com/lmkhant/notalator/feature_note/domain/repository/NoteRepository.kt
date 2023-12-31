package com.lmkhant.notalator.feature_note.domain.repository

import com.lmkhant.notalator.feature_note.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun getNotes(): Flow<List<Note>>
    fun getNotesBy(collectionId: Int): Flow<List<Note>>

    fun getFavouriteNotes(): Flow<List<Note>>

    suspend fun getNoteById(id: Int): Note?

    suspend fun insertNote(note: Note)

    suspend fun deleteNote(note: Note)

    suspend fun updateNote(note: Note)
}