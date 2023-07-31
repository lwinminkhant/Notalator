package com.lmkhant.notalator.feature_note.domain.use_case

import com.lmkhant.notalator.feature_note.domain.use_case.note.AddNote
import com.lmkhant.notalator.feature_note.domain.use_case.note.DeleteNote
import com.lmkhant.notalator.feature_note.domain.use_case.note.GetFavouriteNotes
import com.lmkhant.notalator.feature_note.domain.use_case.note.GetNote
import com.lmkhant.notalator.feature_note.domain.use_case.note.GetNotes
import com.lmkhant.notalator.feature_note.domain.use_case.note.GetNotesBy
import com.lmkhant.notalator.feature_note.domain.use_case.note.UpdateNote

data class NoteUseCases (
    val getNote: GetNote,
    val getNotesBy: GetNotesBy,
    val getFavouriteNotes: GetFavouriteNotes,
    val addNote: AddNote,
    val deleteNote: DeleteNote,
    val updateNote: UpdateNote,
    val getNotes: GetNotes
)