package com.lmkhant.notalator.feature_note.domain.use_case.collection

import com.lmkhant.notalator.feature_note.domain.model.Collection
import com.lmkhant.notalator.feature_note.data.repository.CollectionRepositoryImpl

class DeleteCollection(
    private val repository: CollectionRepositoryImpl
) {

    suspend operator fun invoke(collection: Collection) {
        repository.deleteCollection(collection)
    }
}