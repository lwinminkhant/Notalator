package com.lmkhant.notalator.feature_note.presentation.collections

import com.lmkhant.notalator.feature_note.domain.model.Collection
import com.lmkhant.notalator.feature_note.domain.model.Note
import java.util.Date

data class CollectionNotesState(
    val collection: Collection = Collection(0, Date(),"",""),
    val notes: List<Note> = emptyList()
)
