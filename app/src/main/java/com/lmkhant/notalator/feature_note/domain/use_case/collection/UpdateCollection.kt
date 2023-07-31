package com.lmkhant.notalator.feature_note.domain.use_case.collection

import com.lmkhant.notalator.feature_note.domain.model.Collection
import com.lmkhant.notalator.feature_note.domain.model.InvalidDocumentException
import com.lmkhant.notalator.feature_note.data.repository.CollectionRepositoryImpl

class UpdateCollection(
    private val repository: CollectionRepositoryImpl,
) {
    @Throws(InvalidDocumentException::class)
    suspend operator fun invoke(collection: Collection) {
        if (collection.collectionTitle.isBlank()) {
            throw InvalidDocumentException("The document can't be empty.")
        }
        repository.updateCollection(collection)
    }
}