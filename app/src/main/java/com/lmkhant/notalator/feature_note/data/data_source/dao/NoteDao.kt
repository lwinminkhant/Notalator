package com.lmkhant.notalator.feature_note.data.data_source.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.lmkhant.notalator.feature_note.domain.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: Note)

    @Query("SELECT * FROM note WHERE noteId =:id")
    fun load(id: Int): Flow<Note>?

    @Query("SELECT * FROM note WHERE noteId = :id")
    suspend fun getById(id: Int): Note?

    @Update
    fun update(note: Note)

    @Delete
    fun delete(note: Note)

    @Query("select * from note")
    fun getAll(): Flow<List<Note>>

    @Query("select * from note where collectionId= :collectionId")
    fun getPagesBy(collectionId: Int): Flow<List<Note>>

    @Query("select * from note where favourite = 1")
    fun getFavourite(): Flow<List<Note>>


}