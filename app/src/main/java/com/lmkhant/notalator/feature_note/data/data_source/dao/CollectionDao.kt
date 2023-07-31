package com.lmkhant.notalator.feature_note.data.data_source.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.lmkhant.notalator.feature_note.domain.model.Collection
import com.lmkhant.notalator.feature_note.domain.model.CollectionWithNotes
import kotlinx.coroutines.flow.Flow

@Dao
interface CollectionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(collection : Collection)

    @Query("SELECT * FROM collection WHERE collectionId =:id")
    fun load(id: Int): Flow<Collection>?

    @Query("SELECT * FROM collection WHERE collectionId = :id")
    suspend fun getById(id: Int): Collection?

    @Update
    fun update(collection: Collection)

    @Delete
    fun delete(collection: Collection)

    @Transaction
    @Query("select * from collection")
    fun getAllDocumentWithPages(): Flow<List<CollectionWithNotes>>


    @Query("select * from collection")
    fun getAllDocument(): Flow<List<Collection>>
}