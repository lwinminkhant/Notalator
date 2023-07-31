package com.lmkhant.notalator.feature_note.domain.use_case.collection

import com.lmkhant.notalator.feature_note.data.repository.CollectionRepositoryImpl
import com.lmkhant.notalator.feature_note.domain.model.Collection
import com.lmkhant.notalator.feature_note.domain.util.CollectionOrder
import com.lmkhant.notalator.feature_note.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetCollections(
    private val repository: CollectionRepositoryImpl
) {

    operator fun invoke(
        collectionOrder: CollectionOrder = CollectionOrder.Date(OrderType.Descending)
    ): Flow<List<Collection>> {
        return repository.getAllCollection().map { doc ->
            when(collectionOrder.orderType) {
                is OrderType.Ascending -> {
                    when(collectionOrder) {
                        is CollectionOrder.Description -> doc.sortedBy { it.collectionTitle.lowercase() }
                        is CollectionOrder.Date -> doc.sortedBy { it.collectionUpdatedOn }
                        //is DocOrder.Color -> doc.sortedBy { it.color }
                    }
                }
                is OrderType.Descending -> {
                    when(collectionOrder) {
                        is CollectionOrder.Description -> doc.sortedByDescending { it.collectionTitle.lowercase() }
                        is CollectionOrder.Date -> doc.sortedByDescending { it.collectionUpdatedOn }
                        //is DocOrder.Color -> doc.sortedByDescending { it.color }
                    }
                }
            }
        }
    }
}