package com.lmkhant.notalator.feature_note.domain.use_case.note

import com.lmkhant.notalator.feature_note.data.repository.NoteRepositoryImpl
import com.lmkhant.notalator.feature_note.domain.model.InvalidDocumentException
import com.lmkhant.notalator.feature_note.domain.model.Note

class AddNote(
    private val repository: NoteRepositoryImpl,
) {
    @Throws(InvalidDocumentException::class)
    suspend operator fun invoke(note: Note) {
        repository.insertNote(note)
    }
}