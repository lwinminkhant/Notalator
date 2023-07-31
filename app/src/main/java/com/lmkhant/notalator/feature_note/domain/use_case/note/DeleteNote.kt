package com.lmkhant.notalator.feature_note.domain.use_case.note

import com.lmkhant.notalator.feature_note.data.repository.NoteRepositoryImpl
import com.lmkhant.notalator.feature_note.domain.model.Note

class DeleteNote(
    private val repository: NoteRepositoryImpl
) {

    suspend operator fun invoke(note: Note) {
        repository.deleteNote(note)
    }
}