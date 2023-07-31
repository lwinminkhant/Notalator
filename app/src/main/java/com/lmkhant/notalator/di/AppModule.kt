package com.lmkhant.notalator.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.lmkhant.notalator.feature_note.data.data_source.MIGRATION_1_TO_2
import com.lmkhant.notalator.feature_note.data.data_source.MIGRATION_2_TO_3
import com.lmkhant.notalator.feature_note.data.data_source.NotalatorDatabase
import com.lmkhant.notalator.feature_note.data.repository.CollectionNotesImpl
import com.lmkhant.notalator.feature_note.data.repository.CollectionRepositoryImpl
import com.lmkhant.notalator.feature_note.data.repository.NoteRepositoryImpl
import com.lmkhant.notalator.feature_note.domain.use_case.CollectionUseCases
import com.lmkhant.notalator.feature_note.domain.use_case.DocumentWithPagesUseCases
import com.lmkhant.notalator.feature_note.domain.use_case.NoteUseCases
import com.lmkhant.notalator.feature_note.domain.use_case.collection.AddCollection
import com.lmkhant.notalator.feature_note.domain.use_case.collection.DeleteCollection
import com.lmkhant.notalator.feature_note.domain.use_case.collection.GetAllCollectionWithNotes
import com.lmkhant.notalator.feature_note.domain.use_case.collection.GetCollection
import com.lmkhant.notalator.feature_note.domain.use_case.collection.GetCollections
import com.lmkhant.notalator.feature_note.domain.use_case.collection.UpdateCollection
import com.lmkhant.notalator.feature_note.domain.use_case.collectionnote.GetCollectionNotes
import com.lmkhant.notalator.feature_note.domain.use_case.note.AddNote
import com.lmkhant.notalator.feature_note.domain.use_case.note.DeleteNote
import com.lmkhant.notalator.feature_note.domain.use_case.note.GetFavouriteNotes
import com.lmkhant.notalator.feature_note.domain.use_case.note.GetNote
import com.lmkhant.notalator.feature_note.domain.use_case.note.GetNotes
import com.lmkhant.notalator.feature_note.domain.use_case.note.GetNotesBy
import com.lmkhant.notalator.feature_note.domain.use_case.note.UpdateNote
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDocumentDatabase(app: Application): NotalatorDatabase {
        return Room.databaseBuilder(
            app,
            NotalatorDatabase::class.java,
            NotalatorDatabase.DATABASE_NAME

        ).addMigrations(MIGRATION_1_TO_2)
            .addMigrations(MIGRATION_2_TO_3)
            .build()
    }

    @Provides
    @Singleton
    fun provideDocumentRepository(db: NotalatorDatabase): CollectionRepositoryImpl {
        return CollectionRepositoryImpl(db.collectionDao)
    }


    @Provides
    @Singleton
    fun provideDocumentUseCases(repository: CollectionRepositoryImpl): CollectionUseCases {
        return CollectionUseCases(
            getCollections = GetCollections(repository),
            getCollection = GetCollection(repository),
            deleteCollection = DeleteCollection(repository),
            updateCollection = UpdateCollection(repository),
            addCollection = AddCollection(repository),
            getAllCollectionWithNotes = GetAllCollectionWithNotes(repository)
        )
    }

    @Provides
    @Singleton
    fun provideContext(app: Application):Context{
        return app.applicationContext
    }

    @Provides
    @Singleton
    fun provideDocumentPagesRepository(db: NotalatorDatabase): CollectionNotesImpl {
        return CollectionNotesImpl(db.collectionNoteDao)
    }

    @Provides
    @Singleton
    fun provideDocumentWithPages(repository: CollectionNotesImpl): DocumentWithPagesUseCases {
        return DocumentWithPagesUseCases(
            documentWithPages = GetCollectionNotes(repository)
        )
    }

    @Provides
    @Singleton
    fun providePageRepository(db: NotalatorDatabase): NoteRepositoryImpl {
        return NoteRepositoryImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun providePagesUseCases(repository: NoteRepositoryImpl): NoteUseCases {
        return NoteUseCases(
            addNote = AddNote(repository),
            deleteNote = DeleteNote(repository),
            updateNote = UpdateNote(repository),
            getNote = GetNote(repository),
            getNotes = GetNotes(repository),
            getNotesBy = GetNotesBy(repository),
            getFavouriteNotes = GetFavouriteNotes(repository)
        )
    }

}