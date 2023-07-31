package com.lmkhant.notalator.feature_note.data.repository

import com.lmkhant.notalator.feature_note.data.data_source.dao.CollectionDao
import com.lmkhant.notalator.feature_note.domain.model.Collection
import com.lmkhant.notalator.feature_note.domain.model.CollectionWithNotes
import com.lmkhant.notalator.feature_note.domain.repository.CollectionRepository
import kotlinx.coroutines.flow.Flow

class CollectionRepositoryImpl(private val collectionDao: CollectionDao):
    CollectionRepository {

    override fun getAllCollectionWithNotes(): Flow<List<CollectionWithNotes>> {
        return collectionDao.getAllDocumentWithPages()
    }

    override fun getAllCollection(): Flow<List<Collection>> {
        return collectionDao.getAllDocument()
    }

    override suspend fun getCollectionById(id: Int): Collection?{
        return collectionDao.getById(id)
    }

    override suspend fun insertCollection(collection: Collection) {
        collectionDao.insert(collection)
    }

    override suspend fun deleteCollection(collection: Collection) {
        collectionDao.delete(collection)
    }

    override suspend fun updateCollection(collection: Collection) {
        collectionDao.update(collection)
    }


}