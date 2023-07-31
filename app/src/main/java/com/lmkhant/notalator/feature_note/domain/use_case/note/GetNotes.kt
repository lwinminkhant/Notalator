package com.lmkhant.notalator.feature_note.domain.use_case.note

import com.lmkhant.notalator.feature_note.data.repository.NoteRepositoryImpl
import com.lmkhant.notalator.feature_note.domain.model.Note
import kotlinx.coroutines.flow.Flow

class GetNotes(
    private val repository: NoteRepositoryImpl
) {

    operator fun invoke(
    ): Flow<List<Note>> {
        return repository.getNotes()
    }
}