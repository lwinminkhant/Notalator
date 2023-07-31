package com.lmkhant.notalator.feature_note.data.repository

import com.lmkhant.notalator.feature_note.data.data_source.dao.CollectionNoteDao
import com.lmkhant.notalator.feature_note.domain.model.CollectionWithNotes
import com.lmkhant.notalator.feature_note.domain.repository.CollectionNotesRepository
import kotlinx.coroutines.flow.Flow

class CollectionNotesImpl(private val collectionWithNotesDao: CollectionNoteDao): CollectionNotesRepository {
    override fun getCollectionWithNotes(documentId: Int): Flow<CollectionWithNotes> {
        return collectionWithNotesDao.getPages(documentId)
    }
}