package com.lmkhant.notalator.feature_note.presentation.collections

import com.lmkhant.notalator.feature_note.domain.model.Collection
import com.lmkhant.notalator.feature_note.domain.util.CollectionOrder


sealed class CollectionEvent {
    data class Order(val collectionOrder: CollectionOrder): CollectionEvent()
    data class DeleteDocument(val collection: Collection): CollectionEvent()
    object RestoreDocument: CollectionEvent()
    object ToggleOrderSection: CollectionEvent()
}
