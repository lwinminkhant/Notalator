package com.lmkhant.notalator.feature_note.domain.repository

import com.lmkhant.notalator.feature_note.domain.model.CollectionWithNotes
import kotlinx.coroutines.flow.Flow

interface CollectionNotesRepository {
    fun  getCollectionWithNotes(documentId: Int): Flow<CollectionWithNotes>
}