package com.lmkhant.notalator.feature_note.domain.use_case.collectionnote

import com.lmkhant.notalator.feature_note.data.repository.CollectionNotesImpl
import com.lmkhant.notalator.feature_note.domain.model.CollectionWithNotes
import kotlinx.coroutines.flow.Flow

class GetCollectionNotes(
    private val repository: CollectionNotesImpl
) {

    operator fun invoke(id: Int): Flow<CollectionWithNotes> {
        return repository.getCollectionWithNotes(id)
    }
}