package com.lmkhant.notalator.feature_note.domain.use_case.collection

import com.lmkhant.notalator.feature_note.data.repository.CollectionRepositoryImpl
import com.lmkhant.notalator.feature_note.domain.model.CollectionWithNotes
import kotlinx.coroutines.flow.Flow

class GetAllCollectionWithNotes(
    private val repository: CollectionRepositoryImpl
) {
    operator fun invoke(): Flow<List<CollectionWithNotes>> {
        return repository.getAllCollectionWithNotes()
    }
}