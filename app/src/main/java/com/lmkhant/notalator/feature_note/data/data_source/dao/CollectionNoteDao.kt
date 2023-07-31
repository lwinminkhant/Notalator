package com.lmkhant.notalator.feature_note.data.data_source.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.lmkhant.notalator.feature_note.domain.model.CollectionWithNotes
import kotlinx.coroutines.flow.Flow

@Dao
interface CollectionNoteDao {
    @Transaction
    @Query(
        "SELECT * FROM collection col INNER JOIN CollectionNotesCrossRef docpg " +
            "ON col.collectionId = docpg.collectionId WHERE col.collectionId = :collectionId"
    )
    fun getPages(collectionId: Int):Flow<CollectionWithNotes>
}