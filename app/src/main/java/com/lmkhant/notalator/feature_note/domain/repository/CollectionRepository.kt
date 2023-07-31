package com.lmkhant.notalator.feature_note.domain.repository

import com.lmkhant.notalator.feature_note.domain.model.Collection
import com.lmkhant.notalator.feature_note.domain.model.CollectionWithNotes
import kotlinx.coroutines.flow.Flow

interface CollectionRepository {
    fun  getAllCollectionWithNotes(): Flow<List<CollectionWithNotes>>

    fun  getAllCollection(): Flow<List<Collection>>

    suspend fun getCollectionById(id: Int): Collection?

    suspend fun insertCollection(collection: Collection)

    suspend fun deleteCollection(collection: Collection)

    suspend fun updateCollection(collection: Collection)
}