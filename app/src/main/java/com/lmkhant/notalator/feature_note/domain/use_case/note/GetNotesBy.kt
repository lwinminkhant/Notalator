package com.lmkhant.notalator.feature_note.domain.use_case.note

import com.lmkhant.notalator.feature_note.data.repository.NoteRepositoryImpl
import com.lmkhant.notalator.feature_note.domain.model.Note
import kotlinx.coroutines.flow.Flow

class GetNotesBy(
    private val repository: NoteRepositoryImpl
) {

    operator fun invoke(documentId: Int
    ): Flow<List<Note>> {
        return repository.getNotesBy(documentId)
    }
}