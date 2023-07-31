package com.lmkhant.notalator.feature_note.data.data_source


import androidx.room.Database
import androidx.room.RoomDatabase
import com.lmkhant.notalator.feature_note.data.data_source.dao.CollectionDao
import com.lmkhant.notalator.feature_note.data.data_source.dao.CollectionNoteDao
import com.lmkhant.notalator.feature_note.data.data_source.dao.NoteDao
import com.lmkhant.notalator.feature_note.domain.model.Collection
import com.lmkhant.notalator.feature_note.domain.model.CollectionNotesCrossRef
import com.lmkhant.notalator.feature_note.domain.model.Note

@Database(entities = [Collection::class, CollectionNotesCrossRef::class, Note::class], version = 3, exportSchema = false)
abstract class NotalatorDatabase : RoomDatabase() {
    abstract val collectionDao: CollectionDao
    abstract val noteDao: NoteDao
    abstract val collectionNoteDao: CollectionNoteDao

    companion object {
        const val DATABASE_NAME = "notalator_db"
    }

}