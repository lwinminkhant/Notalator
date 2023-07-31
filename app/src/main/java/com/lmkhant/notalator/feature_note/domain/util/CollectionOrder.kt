package com.lmkhant.notalator.feature_note.domain.util

sealed class CollectionOrder(val orderType: OrderType) {
    class Description(orderType: OrderType): CollectionOrder(orderType)
    class Date(orderType: OrderType): CollectionOrder(orderType)


    fun copy(orderType: OrderType): CollectionOrder {
        return when(this) {
            is Description -> Description(orderType)
            is Date -> Date(orderType)
        }
    }
}
