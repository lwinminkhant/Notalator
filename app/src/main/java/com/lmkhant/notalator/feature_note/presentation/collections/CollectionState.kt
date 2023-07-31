package com.lmkhant.notalator.feature_note.presentation.collections

import com.lmkhant.notalator.feature_note.domain.model.Collection
import com.lmkhant.notalator.feature_note.domain.util.CollectionOrder
import com.lmkhant.notalator.feature_note.domain.util.OrderType

data class CollectionState(
    //val documentWithPages: List<DocumentWithPages> = emptyList(),
    val collections: List<Collection> = emptyList(),
    val collectionOrder: CollectionOrder = CollectionOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)
